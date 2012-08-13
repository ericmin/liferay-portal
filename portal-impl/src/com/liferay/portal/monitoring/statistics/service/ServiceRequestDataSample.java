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

package com.liferay.portal.monitoring.statistics.service;

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.monitoring.MonitorNames;
import com.liferay.portal.monitoring.statistics.BaseDataSample;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Michael C. Han
 */
public class ServiceRequestDataSample extends BaseDataSample {

	public ServiceRequestDataSample(MethodInvocation methodInvocation) {
		setNamespace(MonitorNames.SERVICE);

		Method method = methodInvocation.getMethod();

		String className = method.getDeclaringClass().getName();
		String methodName = method.getName();
		Class<?>[] parameterTypes = method.getParameterTypes();

		_methodKey = new MethodKey(className, methodName, parameterTypes);

		setDescription(_methodKey.toString());
	}

	public MethodKey getMethodKey() {
		return _methodKey;
	}

	private MethodKey _methodKey;

}