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

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;

import java.io.Serializable;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-1466.
 * </p>
 *
 * @author Rudy Hilado
 * @author Shuyang Zhou
 */
public class PortletSessionTracker
	implements HttpSessionListener, HttpSessionBindingListener, Serializable {

	public static void add(HttpSession session) {
		_instance._add(session);
	}

	public static HttpSessionBindingListener getInstance() {
		return _instance;
	}

	public static void invalidate(HttpSession session) {
		_instance._invalidate(session.getId());
	}

	public static void invalidate(String sessionId) {
		_instance._invalidate(sessionId);
	}

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		_invalidate(httpSessionEvent.getSession().getId());
	}

	public void valueBound(HttpSessionBindingEvent event) {
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		invalidate(event.getSession().getId());
	}

	private PortletSessionTracker() {
		_sessions = new ConcurrentHashMap<String, Set<HttpSession>>();

		PortletSessionListenerManager.addHttpSessionListener(this);
	}

	private void _add(HttpSession session) {
		String sessionId = session.getId();

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			sessionId = CompoundSessionIdSplitterUtil.parseSessionId(sessionId);
		}

		Set<HttpSession> sessions = _sessions.get(sessionId);

		if (sessions == null) {
			sessions = new ConcurrentHashSet<HttpSession>();

			Set<HttpSession> previousSessions = _sessions.putIfAbsent(
				sessionId, sessions);

			if (previousSessions != null) {
				sessions = previousSessions;
			}
		}

		sessions.add(session);
	}

	private void _invalidate(String sessionId) {
		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter()) {
			sessionId = CompoundSessionIdSplitterUtil.parseSessionId(sessionId);
		}

		Set<HttpSession> sessions = _sessions.remove(sessionId);

		if (sessions == null) {
			return;
		}

		for (HttpSession session : sessions) {
			try {
				session.invalidate();
			}
			catch (Exception e) {
			}
		}
	}

	private static PortletSessionTracker _instance =
		new PortletSessionTracker();

	private transient ConcurrentMap<String, Set<HttpSession>> _sessions;

}