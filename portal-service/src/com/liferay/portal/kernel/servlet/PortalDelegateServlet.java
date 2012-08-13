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
import com.liferay.portal.kernel.util.InstanceFactory;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

/**
 * <p>
 * See http://issues.liferay.com/browse/LEP-2297.
 * </p>
 *
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class PortalDelegateServlet extends HttpServlet {

	@Override
	public void destroy() {
		PortalDelegatorServlet.removeDelegate(_subContext);
	}

	@Override
	public void init(ServletConfig servletConfig) {
		String servletClass = servletConfig.getInitParameter("servlet-class");

		_subContext = servletConfig.getInitParameter("sub-context");

		if (_subContext == null) {
			_subContext = getServletName();
		}

		try {
			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			HttpServlet servlet = (HttpServlet)InstanceFactory.newInstance(
				contextClassLoader, servletClass);

			servlet.init(servletConfig);

			PortalDelegatorServlet.addDelegate(_subContext, servlet);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalDelegateServlet.class);

	private String _subContext;

}