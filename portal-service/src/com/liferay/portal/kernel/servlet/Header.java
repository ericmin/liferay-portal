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

import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import javax.servlet.http.Cookie;

/**
 * @author Michael Young
 */
public class Header implements Serializable {

	public static final int COOKIE_TYPE = 4;

	public static final int DATE_TYPE = 2;

	public static final int INTEGER_TYPE = 1;

	public static final int STRING_TYPE = 3;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Header)) {
			return false;
		}

		Header header = (Header)obj;

		if (_type != header.getType()) {
			return false;
		}

		String string = toString();

		return string.equals(header.toString());
	}

	public Cookie getCookieValue() {
		return _cookieValue;
	}

	public long getDateValue() {
		return _dateValue;
	}

	public int getIntValue() {
		return _intValue;
	}

	public String getStringValue() {
		return _stringValue;
	}

	public int getType() {
		return _type;
	}

	public void setCookieValue(Cookie cookieValue) {
		_cookieValue = cookieValue;
	}

	public void setDateValue(long dateValue) {
		_dateValue = dateValue;
	}

	public void setIntValue(int intValue) {
		_intValue = intValue;
	}

	public void setStringValue(String stringValue) {
		_stringValue = stringValue;
	}

	public void setType(int type) {
		_type = type;
	}

	@Override
	public String toString() {
		if (_type == COOKIE_TYPE) {
			StringBundler sb = new StringBundler(17);

			sb.append("{comment=");
			sb.append(_cookieValue.getComment());
			sb.append(", domain=");
			sb.append(_cookieValue.getDomain());
			sb.append(", maxAge=");
			sb.append(_cookieValue.getMaxAge());
			sb.append(", name=");
			sb.append(_cookieValue.getName());
			sb.append(", path=");
			sb.append(_cookieValue.getPath());
			sb.append(", secure=");
			sb.append(_cookieValue.getSecure());
			sb.append(", value=");
			sb.append(_cookieValue.getValue());
			sb.append(", version=");
			sb.append(_cookieValue.getVersion());
			sb.append("}");

			return sb.toString();
		}
		else if (_type == DATE_TYPE) {
			return String.valueOf(_dateValue);
		}
		else if (_type == INTEGER_TYPE) {
			return String.valueOf(_intValue);
		}
		else {
			return _stringValue;
		}
	}

	private Cookie _cookieValue;
	private long _dateValue;
	private int _intValue;
	private String _stringValue;
	private int _type;

}