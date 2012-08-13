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

import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionMapping;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.MethodParameter;
import com.liferay.portal.kernel.util.MethodParametersResolverUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceActionConfig
	implements Comparable<JSONWebServiceActionConfig>,
	JSONWebServiceActionMapping {

	public JSONWebServiceActionConfig(
		String servletContextPath, Class<?> actionClass, Method actionMethod,
		String path, String method) {

		_servletContextPath = servletContextPath;
		_actionClass = actionClass;
		_actionMethod = actionMethod;
		_path = path;
		_method = method;

		_methodParameters =
			MethodParametersResolverUtil.resolveMethodParameters(actionMethod);

		_fullPath = _servletContextPath + _path;

		StringBundler sb = new StringBundler(_methodParameters.length * 2 + 4);

		sb.append(_fullPath);
		sb.append(CharPool.MINUS);
		sb.append(_methodParameters.length);

		for (MethodParameter methodParameter : _methodParameters) {
			sb.append(CharPool.MINUS);
			sb.append(methodParameter.getName());
		}

		_signature = sb.toString();
	}

	public int compareTo(
		JSONWebServiceActionConfig jsonWebServiceActionConfig) {

		return _signature.compareTo(jsonWebServiceActionConfig._signature);
	}

	public Class<?> getActionClass() {
		return _actionClass;
	}

	public Method getActionMethod() {
		return _actionMethod;
	}

	public String getFullPath() {
		return _fullPath;
	}

	public String getMethod() {
		return _method;
	}

	public MethodParameter[] getMethodParameters() {
		return _methodParameters;
	}

	public String getPath() {
		return _path;
	}

	public String getServletContextPath() {
		return _servletContextPath;
	}

	public String getSignature() {
		return _signature;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{actionClass=");
		sb.append(_actionClass);
		sb.append(", actionMethod=");
		sb.append(_actionMethod);
		sb.append(", fullPath=");
		sb.append(_fullPath);
		sb.append(", method=");
		sb.append(_method);
		sb.append(", methodParameters=");
		sb.append(_methodParameters);
		sb.append(", path=");
		sb.append(_path);
		sb.append(", servletContextPath=");
		sb.append(_servletContextPath);
		sb.append(", signature=");
		sb.append(_signature);
		sb.append("}");

		return sb.toString();
	}

	private Class<?> _actionClass;
	private Method _actionMethod;
	private String _fullPath;
	private String _method;
	private MethodParameter[] _methodParameters;
	private String _path;
	private String _servletContextPath;
	private String _signature;

}