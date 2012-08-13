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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.servlet.PluginContextListener;
import com.liferay.portal.security.lang.PortalSecurityManagerThreadLocal;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLPolicyManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Raymond Augé
 */
public class PACLRequestDispatcherWrapper implements RequestDispatcher {

	public PACLRequestDispatcherWrapper(
		ServletContext servletContext, RequestDispatcher requestDispatcher) {

		_servletContext = servletContext;
		_requestDispatcher = requestDispatcher;
	}

	public void forward(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		doDispatch(servletRequest, servletResponse, false);
	}

	public void include(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		doDispatch(servletRequest, servletResponse, true);
	}

	protected void doDispatch(
			ServletRequest servletRequest, ServletResponse servletResponse,
			boolean include)
		throws IOException, ServletException {

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		ClassLoader pluginClassLoader =
			(ClassLoader)_servletContext.getAttribute(
				PluginContextListener.PLUGIN_CLASS_LOADER);

		PACLPolicy paclPolicy =
			PortalSecurityManagerThreadLocal.getPACLPolicy();

		try {
			if (pluginClassLoader == null) {
				PortalSecurityManagerThreadLocal.setPACLPolicy(null);

				PACLClassLoaderUtil.setContextClassLoader(
					PACLClassLoaderUtil.getPortalClassLoader());
			}
			else {
				PACLPolicy pluginPACLPolicy = PACLPolicyManager.getPACLPolicy(
					pluginClassLoader);

				PortalSecurityManagerThreadLocal.setPACLPolicy(
					pluginPACLPolicy);

				PACLClassLoaderUtil.setContextClassLoader(pluginClassLoader);
			}

			if (include) {
				_requestDispatcher.include(servletRequest, servletResponse);
			}
			else {
				_requestDispatcher.forward(servletRequest, servletResponse);
			}
		}
		finally {
			PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);

			PortalSecurityManagerThreadLocal.setPACLPolicy(paclPolicy);
		}
	}

	private RequestDispatcher _requestDispatcher;
	private ServletContext _servletContext;

}