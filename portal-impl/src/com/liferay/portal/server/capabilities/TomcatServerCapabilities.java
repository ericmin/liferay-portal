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

package com.liferay.portal.server.capabilities;

import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Igor Spasic
 */
public class TomcatServerCapabilities implements ServerCapabilities {

	public void determine(ServletContext servletContext) throws Exception {
		determineSupportsHotDeploy(servletContext);
	}

	public boolean isSupportsHotDeploy() {
		return _supportsHotDeploy;
	}

	protected void determineSupportsHotDeploy(ServletContext servletContext)
		throws Exception {

		// org.apache.catalina.core.ApplicationContextFacade

		Class<?> applicationContextFacadeClass = servletContext.getClass();

		Field contextField1 = ReflectionUtil.getDeclaredField(
			applicationContextFacadeClass, "context");

		Object contextValue1 = contextField1.get(servletContext);

		// org.apache.catalina.core.ApplicationContext

		Class<?> applicationContextClass = contextField1.getType();

		Field contextField2 = ReflectionUtil.getDeclaredField(
			applicationContextClass, "context");

		Object contextValue2 = contextField2.get(contextValue1);

		// org.apache.catalina.core.StandardContext

		Class<?> standardContextClass = contextField2.getType();

		// org.apache.catalina.core.ContainerBase

		Class<?> containerBaseClass = standardContextClass.getSuperclass();

		Field parentField = ReflectionUtil.getDeclaredField(
			containerBaseClass, "parent");

		Object parentValue = parentField.get(contextValue2);

		Field autoDeployField = ReflectionUtil.getDeclaredField(
			parentValue.getClass(), "autoDeploy");

		Boolean autoDeployValue = (Boolean)autoDeployField.get(parentValue);

		_supportsHotDeploy = autoDeployValue;
	}

	private boolean _supportsHotDeploy;

}