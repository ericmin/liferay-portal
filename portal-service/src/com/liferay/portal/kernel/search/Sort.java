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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Bruno Farache
 */
public class Sort implements Serializable {

	@Deprecated
	public static final int AUTO_TYPE = 2;

	public static final int CUSTOM_TYPE = 9;

	public static final int DOC_TYPE = 1;

	public static final int DOUBLE_TYPE = 7;

	public static final int FLOAT_TYPE = 5;

	public static final int INT_TYPE = 4;

	public static final int LONG_TYPE = 6;

	public static final int SCORE_TYPE = 0;

	public static final int STRING_TYPE = 3;

	public Sort() {
	}

	public Sort(String fieldName, boolean reverse) {
		this(fieldName, STRING_TYPE, reverse);
	}

	public Sort(String fieldName, int type, boolean reverse) {
		_fieldName = fieldName;
		_type = type;
		_reverse = reverse;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public int getType() {
		return _type;
	}

	public boolean isReverse() {
		return _reverse;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setReverse(boolean reverse) {
		_reverse = reverse;
	}

	public void setType(int type) {
		_type = type;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{fieldName=");
		sb.append(_fieldName);
		sb.append(", type=");
		sb.append(_type);
		sb.append(", reverse=");
		sb.append(_reverse);
		sb.append("}");

		return sb.toString();
	}

	private String _fieldName;
	private boolean _reverse;
	private int _type;

}