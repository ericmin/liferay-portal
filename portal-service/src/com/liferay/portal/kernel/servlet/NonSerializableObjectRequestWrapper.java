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

package com.liferay.portal.kernel.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Igor Spasic
 */
public class NonSerializableObjectRequestWrapper extends
	HttpServletRequestWrapper {

	public NonSerializableObjectRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Object getAttribute(String name) {
		Object object = super.getAttribute(name);

		object = NonSerializableObjectHandler.getValue(object);

		return object;
	}

	@Override
	public void setAttribute(String name, Object object) {
		object = new NonSerializableObjectHandler(object);

		super.setAttribute(name, object);
	}

}