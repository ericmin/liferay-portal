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

import java.io.Serializable;

/**
 * @author Igor Spasic
 */
public final class NonSerializableObjectHandler implements Serializable {

	public static Object getValue(Object value) {
		if (value instanceof NonSerializableObjectHandler) {
			NonSerializableObjectHandler nonSerializableObjectHandler =
				(NonSerializableObjectHandler)value;

			value = nonSerializableObjectHandler.getValue();
		}

		return value;
	}

	public NonSerializableObjectHandler(Object value) {
		while (value instanceof NonSerializableObjectHandler) {
			NonSerializableObjectHandler nonSerializableObjectHandler =
				(NonSerializableObjectHandler)value;

			value = nonSerializableObjectHandler.getValue();
		}

		_value = value;
	}

	public Object getValue() {
		return _value;
	}

	private transient Object _value;

}