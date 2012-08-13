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

package com.liferay.portlet;

import com.liferay.portal.kernel.util.Base64;

import java.io.Serializable;

import javax.portlet.Event;

import javax.xml.namespace.QName;

/**
 * @author Brian Wing Shun Chan
 */
public class EventImpl implements Event {

	public EventImpl(String name, QName qName, Serializable value) {
		_name = name;
		_qName = qName;
		_value = value;
	}

	public String getBase64Value() {
		if (_base64Value != null) {
			return _base64Value;
		}
		else {
			_base64Value = Base64.objectToString(_value);

			return _base64Value;
		}
	}

	public String getName() {
		return _name;
	}

	public QName getQName() {
		return _qName;
	}

	public Serializable getValue() {
		return _value;
	}

	private String _base64Value;
	private String _name;
	private QName _qName;
	private Serializable _value;

}