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

package com.liferay.portal.kernel.security.pacl.permission;

import java.lang.reflect.Method;

import java.security.BasicPermission;

/**
 * @author Raymond Augé
 */
public class PortalServicePermission extends BasicPermission {

	public PortalServicePermission(String name, Object object, Method method) {
		super(name);

		_object = object;
		_method = method;
	}

	public Method getMethod() {
		return _method;
	}

	public Object getObject() {
		return _object;
	}

	private Method _method;
	private Object _object;

}