/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdHttpSession;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-2299.
 * </p>
 *
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class PortletSessionListenerManager implements HttpSessionListener {

	public static void addHttpSessionListener(
		HttpSessionListener httpSessionListener) {

		_httpSessionListeners.add(httpSessionListener);
	}

	public static void removeHttpSessionListener(
		HttpSessionListener httpSessionListener) {

		_httpSessionListeners.remove(httpSessionListener);
	}

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		httpSessionEvent = getHttpSessionEvent(httpSessionEvent);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			for (HttpSessionListener httpSessionListener :
					_httpSessionListeners) {

				Class<?> clazz = httpSessionListener.getClass();

				ClassLoader classLoader = clazz.getClassLoader();

				currentThread.setContextClassLoader(classLoader);

				httpSessionListener.sessionCreated(httpSessionEvent);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		httpSessionEvent = getHttpSessionEvent(httpSessionEvent);

		for (HttpSessionListener httpSessionListener : _httpSessionListeners) {
			httpSessionListener.sessionDestroyed(httpSessionEvent);
		}
	}

	protected HttpSessionEvent getHttpSessionEvent(
		HttpSessionEvent httpSessionEvent) {

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			CompoundSessionIdHttpSession compoundSessionIdHttpSession =
				new CompoundSessionIdHttpSession(httpSessionEvent.getSession());

			httpSessionEvent = new HttpSessionEvent(
				compoundSessionIdHttpSession);
		}

		return httpSessionEvent;
	}

	private static List<HttpSessionListener> _httpSessionListeners =
		new ArrayList<HttpSessionListener>();

}