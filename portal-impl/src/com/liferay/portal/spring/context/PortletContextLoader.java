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

package com.liferay.portal.spring.context;

import javax.servlet.ServletContext;

import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @author Brian Wing Shun Chan
 * @see    PortletApplicationContext
 * @see    PortletContextLoaderListener
 */
public class PortletContextLoader extends ContextLoader {

	public static final String PORTAL_CONFIG_LOCATION_PARAM =
		"portalContextConfigLocation";

	@Override
	protected void customizeContext(
		ServletContext servletContext,
		ConfigurableWebApplicationContext configurableWebApplicationContext) {

		String configLocation = servletContext.getInitParameter(
			PORTAL_CONFIG_LOCATION_PARAM);

		configurableWebApplicationContext.setConfigLocation(configLocation);

		configurableWebApplicationContext.addBeanFactoryPostProcessor(
			new PortletBeanFactoryPostProcessor());
	}

	@Override
	protected Class<?> determineContextClass(ServletContext servletContext) {
		return PortletApplicationContext.class;
	}

}