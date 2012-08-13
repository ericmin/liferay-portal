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

package com.liferay.portal.service;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.Stack;

/**
 * @author Michael C. Han
 */
public class ServiceContextThreadLocal {

	public static ServiceContext getServiceContext() {
		Stack<ServiceContext> serviceContextStack =
			_serviceContextThreadLocal.get();

		if (serviceContextStack.isEmpty()) {
			return null;
		}

		return serviceContextStack.peek();
	}

	public static ServiceContext popServiceContext() {
		Stack<ServiceContext> serviceContextStack =
			_serviceContextThreadLocal.get();

		if (serviceContextStack.isEmpty()) {
			return null;
		}

		return serviceContextStack.pop();
	}

	public static void pushServiceContext(ServiceContext serviceContext) {
		Stack<ServiceContext> serviceContextStack =
			_serviceContextThreadLocal.get();

		serviceContextStack.push(serviceContext);
	}

	private static ThreadLocal<Stack<ServiceContext>>
		_serviceContextThreadLocal =
			new AutoResetThreadLocal<Stack<ServiceContext>>(
				ServiceContextThreadLocal.class + "._serviceContextThreadLocal",
				new Stack<ServiceContext>());

}