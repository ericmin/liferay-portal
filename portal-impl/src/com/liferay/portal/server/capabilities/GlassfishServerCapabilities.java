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

import com.liferay.portal.kernel.util.MapUtil;

import java.lang.reflect.Field;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Igor Spasic
 */
public class GlassfishServerCapabilities implements ServerCapabilities {

	public void determine(ServletContext servletContext) throws Exception {
		determineSupportsHotDeploy(servletContext);
	}

	public boolean isSupportsHotDeploy() {
		return _supportsHotDeploy;
	}

	protected void determineSupportsHotDeploy(ServletContext servletContext)
		throws Exception {

		// org.apache.catalina.core.ApplicationContext

		Class<?> servletContextClass = servletContext.getClass();

		Field contextField = servletContextClass.getDeclaredField("context");

		contextField.setAccessible(true);

		Object applicationContext = contextField.get(servletContext);

		// com.sun.enterprise.web.WebModule

		Class<?> applicationContextClass = applicationContext.getClass();

		contextField = applicationContextClass.getDeclaredField("context");

		contextField.setAccessible(true);

		Object webModule = contextField.get(applicationContext);

		// org.apache.catalina.core.ContainerBase

		Class<?> containerBaseClass = webModule.getClass();

		for (int i = 0; i < 3; i++) {
			containerBaseClass = containerBaseClass.getSuperclass();
		}

		// com.sun.enterprise.web.VirtualServer

		Field parentField = containerBaseClass.getDeclaredField("parent");

		parentField.setAccessible(true);

		Object virtualServer = parentField.get(webModule);

		// com.sun.enterprise.web.WebContainer

		Object webEngine = parentField.get(virtualServer);

		Class<?> webEngineClass = webEngine.getClass();

		Field webContainerField = webEngineClass.getDeclaredField(
			"webContainer");

		webContainerField.setAccessible(true);

		Object webContainer = webContainerField.get(webEngine);

		// org.glassfish.config.support.TranslatedConfigView

		Class<?> webContainerClass = webContainer.getClass();

		Field dasConfigField = webContainerClass.getDeclaredField("dasConfig");

		dasConfigField.setAccessible(true);

		Object dasConfigProxy = dasConfigField.get(webContainer);

		Class<?> proxyClass = dasConfigProxy.getClass().getSuperclass();

		Field hField = proxyClass.getDeclaredField("h");

		hField.setAccessible(true);

		Object translatedConfigView = hField.get(dasConfigProxy);

		// org.glassfish.config.support.GlassFishConfigBean

		Class<?> translatedConfigViewClass = translatedConfigView.getClass();

		Field masterViewField = translatedConfigViewClass.getDeclaredField(
			"masterView");

		masterViewField.setAccessible(true);

		Object masterView = masterViewField.get(translatedConfigView);

		// org.jvnet.hk2.config.Dom

		Class<?> domClass = masterView.getClass();

		for (int i = 0; i < 2; i++) {
			domClass = domClass.getSuperclass();
		}

		Field attributesField = domClass.getDeclaredField("attributes");

		attributesField.setAccessible(true);

		Map<String, String> attributes =
			(Map<String, String>)attributesField.get(masterView);

		boolean autoDeployEnabled = MapUtil.getBoolean(
			attributes, "autodeploy-enabled", true);

		_supportsHotDeploy = autoDeployEnabled;
	}

	private boolean _supportsHotDeploy;

}