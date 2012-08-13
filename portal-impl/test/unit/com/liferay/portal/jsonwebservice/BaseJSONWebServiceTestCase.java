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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionsManagerUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.MethodParametersResolverUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.MethodParametersResolverImpl;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.powermock.api.mockito.PowerMockito;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Igor Spasic
 */
public abstract class BaseJSONWebServiceTestCase extends PowerMockito {

	protected static void initPortalServices() {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

		JSONWebServiceActionsManagerUtil jsonWebServiceActionsManagerUtil =
			new JSONWebServiceActionsManagerUtil();

		jsonWebServiceActionsManagerUtil.setJSONWebServiceActionsManager(
			new JSONWebServiceActionsManagerImpl());

		MethodParametersResolverUtil methodParametersResolverUtil =
			new MethodParametersResolverUtil();

		methodParametersResolverUtil.setMethodParametersResolver(
			new MethodParametersResolverImpl());
	}

	protected static void registerActionClass(Class<?> actionClass) {
		JSONWebServiceMappingResolver jsonWebServiceMappingResolver =
			new JSONWebServiceMappingResolver();

		Method[] methods = actionClass.getMethods();

		for (Method actionMethod : methods) {
			if (actionMethod.getDeclaringClass() != actionClass) {
				continue;
			}

			String path = jsonWebServiceMappingResolver.resolvePath(
				actionClass, actionMethod);
			String method = jsonWebServiceMappingResolver.resolveHttpMethod(
				actionMethod);

			JSONWebServiceActionsManagerUtil.registerJSONWebServiceAction(
				StringPool.BLANK, actionClass, actionMethod, path, method);
		}
	}

	protected MockHttpServletRequest createHttpRequest(String pathInfo) {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setMethod(HttpMethods.GET);
		mockHttpServletRequest.setPathInfo(pathInfo);

		return mockHttpServletRequest;
	}

	protected MockHttpServletRequest createHttpRequest(
		String pathInfo, String method) {

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setMethod(method);
		mockHttpServletRequest.setPathInfo(pathInfo);

		return mockHttpServletRequest;
	}

	protected JSONWebServiceAction lookupJSONWebServiceAction(
		HttpServletRequest httpServletRequest) {

		return JSONWebServiceActionsManagerUtil.getJSONWebServiceAction(
			httpServletRequest);
	}

	protected String toJSON(Object object) {
		if (object instanceof JSONSerializable) {
			return ((JSONSerializable)object).toJSONString();
		}

		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

		jsonSerializer.exclude("*.class");

		return jsonSerializer.serialize(object);
	}

}