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

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.CamelCaseUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceMappingResolver {

	public String resolveHttpMethod(Method method) {
		JSONWebService jsonWebServiceAnnotation = method.getAnnotation(
			JSONWebService.class);

		String httpMethod = null;

		if (jsonWebServiceAnnotation != null) {
			httpMethod = jsonWebServiceAnnotation.method().trim();
		}

		if ((httpMethod != null) && (httpMethod.length() != 0)) {
			return httpMethod;
		}

		String methodName = method.getName();

		String methodNamePrefix = _cutPrefix(methodName);

		return _prefixToHttpMethod(methodNamePrefix);
	}

	public String resolvePath(Class<?> clazz, Method method) {
		JSONWebService jsonWebServiceAnnotation = method.getAnnotation(
			JSONWebService.class);

		String path = null;

		if (jsonWebServiceAnnotation != null) {
			path = jsonWebServiceAnnotation.value().trim();
		}

		if ((path == null) || (path.length() == 0)) {
			path = CamelCaseUtil.fromCamelCase(method.getName());
		}

		if (!path.startsWith(StringPool.SLASH)) {
			path = StringPool.SLASH + path;

			String pathFromClass = null;

			jsonWebServiceAnnotation = clazz.getAnnotation(
				JSONWebService.class);

			if (jsonWebServiceAnnotation != null) {
				pathFromClass = jsonWebServiceAnnotation.value().trim();
			}

			if ((pathFromClass == null) || (pathFromClass.length() == 0)) {
				pathFromClass = _classNameToPath(clazz);
			}

			if (!pathFromClass.startsWith(StringPool.SLASH)) {
				pathFromClass = StringPool.SLASH + pathFromClass;
			}

			path = pathFromClass + path;

		}

		return path;
	}

	private String _classNameToPath(Class<?> clazz) {
		String className = clazz.getSimpleName();

		className = StringUtil.replace(className, "Impl", StringPool.BLANK);
		className = StringUtil.replace(className, "Service", StringPool.BLANK);

		return className.toLowerCase();
	}

	private String _cutPrefix(String methodName) {
		int i = 0;

		while (i < methodName.length()) {
			if (Character.isUpperCase(methodName.charAt(i))) {
				break;
			}

			i++;
		}

		return methodName.substring(0, i);
	}

	private String _prefixToHttpMethod(String prefix) {
		for (String postPrefix : _GET_PREFIXES) {
			if (prefix.equals(postPrefix)) {
				return HttpMethods.GET;
			}
		}

		return HttpMethods.POST;
	}

	private static final String[] _GET_PREFIXES = new String[] {
		"get", "has", "is",
	};

}