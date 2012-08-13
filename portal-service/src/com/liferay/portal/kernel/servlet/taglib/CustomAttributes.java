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

package com.liferay.portal.kernel.servlet.taglib;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 */
public class CustomAttributes {

	public static CustomAttributes getInstance() {
		return new CustomAttributes();
	}

	public static CustomAttributes getInstance(String keyPrefix) {
		return new CustomAttributes(keyPrefix);
	}

	public CustomAttributes add(CustomAttributes customAttributes) {
		_sb.append(customAttributes._sb);

		return this;
	}

	public CustomAttributes add(Object... values) {
		for (int i = 0; i < values.length; i += 2) {
			add(String.valueOf(values[i]), values[i + 1]);
		}

		return this;
	}

	public CustomAttributes add(String key, boolean value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, byte value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, char value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, double value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, float value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, int value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, long value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, Object value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, short value) {
		return add(key, String.valueOf(value));
	}

	public CustomAttributes add(String key, String value) {
		if (_sb.length() > 0) {
			_sb.append(CharPool.SPACE);
		}

		if (Validator.isNotNull(_keyPrefix)) {
			_sb.append(_keyPrefix);
		}

		_sb.append(key);
		_sb.append(CharPool.EQUAL);
		_sb.append(CharPool.QUOTE);
		_sb.append(value);
		_sb.append(CharPool.QUOTE);

		return this;
	}

	public void reset() {
		_sb.setIndex(0);
	}

	@Override
	public String toString() {
		return _sb.toString();
	}

	private CustomAttributes() {
	}

	private CustomAttributes(String keyPrefix) {
		_keyPrefix = keyPrefix;
	}

	private String _keyPrefix;
	private StringBundler _sb = new StringBundler();

}