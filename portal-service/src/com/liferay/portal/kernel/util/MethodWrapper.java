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

package com.liferay.portal.kernel.util;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Arrays;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated
 */
public class MethodWrapper implements Serializable {

	public MethodWrapper(String className, String methodName) {
		this(className, methodName, new Object[0]);
	}

	public MethodWrapper(String className, String methodName, Object argument) {
		this(className, methodName, new Object[] {argument});
	}

	public MethodWrapper(
		String className, String methodName, Object[] arguments) {

		_className = className;
		_methodName = methodName;
		_arguments = arguments;
	}

	public MethodWrapper(Method method, Object[] arguments) {
		this(method.getDeclaringClass().getName(), method.getName(), arguments);

		_argumentClassNames = new String[arguments.length];

		Class<?>[] parameterTypes = method.getParameterTypes();

		for (int i = 0; i < parameterTypes.length; i++) {
			_argumentClassNames[i] = parameterTypes[i].getName();
		}
	}

	/**
	 * @deprecated Use <code>getArguments</code>.
	 */
	public Object[] getArgs() {
		return getArguments();
	}

	public String[] getArgumentClassNames() {
		return _argumentClassNames;
	}

	public Object[] getArguments() {
		Object[] arguments = new Object[_arguments.length];

		System.arraycopy(_arguments, 0, arguments, 0, _arguments.length);

		return arguments;
	}

	public String getClassName() {
		return _className;
	}

	public String getMethodName() {
		return _methodName;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{className=");
		sb.append(_className);
		sb.append(", methodName=");
		sb.append(_methodName);

		if (_argumentClassNames != null) {
			sb.append(", argumentClassNames=");
			sb.append(Arrays.toString(_argumentClassNames));
		}

		sb.append(", arguments=");
		sb.append(Arrays.toString(_arguments));
		sb.append("}");

		return sb.toString();
	}

	private String[] _argumentClassNames;
	private Object[] _arguments;
	private String _className;
	private String _methodName;

}