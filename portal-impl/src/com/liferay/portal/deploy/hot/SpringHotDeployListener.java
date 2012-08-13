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

package com.liferay.portal.deploy.hot;

import com.liferay.portal.kernel.deploy.hot.BaseHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.spring.context.PortletContextLoaderListener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

/**
 * @author Brian Wing Shun Chan
 */
public class SpringHotDeployListener extends BaseHotDeployListener {

	public void invokeDeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeDeploy(hotDeployEvent);
		}
		catch (Throwable t) {
			throwHotDeployException(
				hotDeployEvent, "Error initializing Spring for ", t);
		}
	}

	public void invokeUndeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		try {
			doInvokeUndeploy(hotDeployEvent);
		}
		catch (Throwable t) {
			throwHotDeployException(
				hotDeployEvent, "Error uninitializing Spring for ", t);
		}
	}

	protected void doInvokeDeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		ContextLoaderListener contextLoaderListener =
			new PortletContextLoaderListener();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			PACLClassLoaderUtil.setContextClassLoader(
				PACLClassLoaderUtil.getPortalClassLoader());

			contextLoaderListener.contextInitialized(
				new ServletContextEvent(servletContext));
		}
		finally {
			PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
		}

		_contextLoaderListeners.put(servletContextName, contextLoaderListener);
	}

	protected void doInvokeUndeploy(HotDeployEvent hotDeployEvent)
		throws Exception {

		ServletContext servletContext = hotDeployEvent.getServletContext();

		String servletContextName = servletContext.getServletContextName();

		ContextLoaderListener contextLoaderListener =
			_contextLoaderListeners.remove(servletContextName);

		if (contextLoaderListener == null) {
			return;
		}

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			PACLClassLoaderUtil.setContextClassLoader(
				PACLClassLoaderUtil.getPortalClassLoader());

			contextLoaderListener.contextDestroyed(
				new ServletContextEvent(servletContext));
		}
		finally {
			PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
		}
	}

	private static Map<String, ContextLoaderListener> _contextLoaderListeners =
		new HashMap<String, ContextLoaderListener>();

}