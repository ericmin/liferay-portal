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

package com.liferay.portal.kernel.jsonwebservice;

import java.lang.reflect.Method;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Spasic
 */
public interface JSONWebServiceActionsManager {

	public JSONWebServiceAction getJSONWebServiceAction(
		HttpServletRequest request);

	public JSONWebServiceAction getJSONWebServiceAction(
		HttpServletRequest request, String path, String method,
		Map<String, Object> parameters);

	public JSONWebServiceActionMapping getJSONWebServiceActionMapping(
		String signature);

	public List<JSONWebServiceActionMapping> getJSONWebServiceActionMappings(
		String servletContextPath);

	public void registerJSONWebServiceAction(
		String servletContextPath, Class<?> actionClass, Method actionMethod,
		String path, String method);

	public int unregisterJSONWebServiceActions(String servletContextPath);

}