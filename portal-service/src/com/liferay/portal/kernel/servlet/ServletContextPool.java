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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class ServletContextPool {

	public static void clear() {
		_instance._servletContexts.clear();
	}

	public static boolean containsKey(String servletContextName) {
		return _instance._containsKey(servletContextName);
	}

	public static ServletContext get(String servletContextName) {
		return _instance._get(servletContextName);
	}

	public static Set<String> keySet() {
		return _instance._keySet();
	}

	public static void put(
		String servletContextName, ServletContext servletContext) {

		_instance._put(servletContextName, servletContext);
	}

	public static ServletContext remove(String servletContextName) {
		return _instance._remove(servletContextName);
	}

	private ServletContextPool() {
		_servletContexts = new ConcurrentHashMap<String, ServletContext>();
	}

	private boolean _containsKey(String servletContextName) {
		if (servletContextName == null) {
			return false;
		}

		boolean value = _servletContexts.containsKey(servletContextName);

		if (_log.isDebugEnabled()) {
			_log.debug("Contains key " + servletContextName + " " + value);
		}

		return value;
	}

	private ServletContext _get(String servletContextName) {
		ServletContext servletContext = _servletContexts.get(
			servletContextName);

		if (_log.isDebugEnabled()) {
			_log.debug("Get " + servletContextName + " " + servletContext);
		}

		return servletContext;
	}

	private Set<String> _keySet() {
		return _servletContexts.keySet();
	}

	private void _put(
		String servletContextName, ServletContext servletContext) {

		if (_log.isDebugEnabled()) {
			_log.debug("Put " + servletContextName + " " + servletContext);
		}

		_servletContexts.put(servletContextName, servletContext);
	}

	private ServletContext _remove(String servletContextName) {
		ServletContext servletContext = _servletContexts.remove(
			servletContextName);

		if (_log.isDebugEnabled()) {
			_log.debug("Remove " + servletContextName + " " + servletContext);
		}

		return servletContext;
	}

	private static Log _log = LogFactoryUtil.getLog(ServletContextPool.class);

	private static ServletContextPool _instance = new ServletContextPool();

	private Map<String, ServletContext> _servletContexts;

}