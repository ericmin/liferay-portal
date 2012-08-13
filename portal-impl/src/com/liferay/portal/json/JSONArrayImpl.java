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

package com.liferay.portal.json;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Writer;

/**
 * @author Brian Wing Shun Chan
 */
public class JSONArrayImpl implements JSONArray {

	public JSONArrayImpl() {
		_jsonArray = new org.json.JSONArray();
	}

	public JSONArrayImpl(String json) throws JSONException {
		try {
			_jsonArray = new org.json.JSONArray(json);
		}
		catch (Exception e) {
			throw new JSONException(e);
		}
	}

	public JSONArrayImpl(org.json.JSONArray jsonArray) {
		_jsonArray = jsonArray;
	}

	public boolean getBoolean(int index) {
		return _jsonArray.optBoolean(index);
	}

	public double getDouble(int index) {
		return _jsonArray.optDouble(index);
	}

	public int getInt(int index) {
		return _jsonArray.optInt(index);
	}

	public org.json.JSONArray getJSONArray() {
		return _jsonArray;
	}

	public JSONArray getJSONArray(int index) {
		org.json.JSONArray jsonArray = _jsonArray.optJSONArray(index);

		if (jsonArray == null) {
			return null;
		}

		return new JSONArrayImpl(jsonArray);
	}

	public JSONObject getJSONObject(int index) {
		org.json.JSONObject jsonObj = _jsonArray.optJSONObject(index);

		if (jsonObj == null) {
			return null;
		}

		return new JSONObjectImpl(jsonObj);
	}

	public long getLong(int index) {
		return _jsonArray.optLong(index);
	}

	public String getString(int index) {
		return _jsonArray.optString(index);
	}

	public boolean isNull(int index) {
		return _jsonArray.isNull(index);
	}

	public String join(String separator) throws JSONException {
		try {
			return _jsonArray.join(separator);
		}
		catch (Exception e) {
			throw new JSONException(e);
		}
	}

	public int length() {
		return _jsonArray.length();
	}

	public JSONArray put(boolean value) {
		_jsonArray.put(value);

		return this;
	}

	public JSONArray put(double value) {
		try {
			_jsonArray.put(value);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return this;
	}

	public JSONArray put(int value) {
		_jsonArray.put(value);

		return this;
	}

	public JSONArray put(JSONArray value) {
		_jsonArray.put(((JSONArrayImpl)value).getJSONArray());

		return this;
	}

	public JSONArray put(JSONObject value) {
		_jsonArray.put(((JSONObjectImpl)value).getJSONObject());

		return this;
	}

	public JSONArray put(long value) {
		_jsonArray.put(value);

		return this;
	}

	public JSONArray put(String value) {
		_jsonArray.put(value);

		return this;
	}

	@Override
	public String toString() {
		return _jsonArray.toString();
	}

	public String toString(int indentFactor) throws JSONException {
		try {
			return _jsonArray.toString(indentFactor);
		}
		catch (Exception e) {
			throw new JSONException(e);
		}
	}

	public Writer write(Writer writer) throws JSONException {
		try {
			return _jsonArray.write(writer);
		}
		catch (Exception e) {
			throw new JSONException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JSONArrayImpl.class);

	private org.json.JSONArray _jsonArray;

}