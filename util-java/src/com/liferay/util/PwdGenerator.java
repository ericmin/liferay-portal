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

package com.liferay.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class PwdGenerator {

	public static final String KEY1 = "0123456789";

	public static final String KEY2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String KEY3 = "abcdefghijklmnopqrstuvwxyz";

	public static String getPassword() {
		return getPassword(8);
	}

	public static String getPassword(int length) {
		return _getPassword(KEY1 + KEY2 + KEY3, length, true);
	}

	public static String getPassword(String key, int length) {
		return getPassword(key, length, true);
	}

	public static String getPassword(
		String key, int length, boolean useAllKeys) {

		return _getPassword(key, length, useAllKeys);
	}

	public static String getPinNumber() {
		return _getPassword(KEY1, 4, true);
	}

	private static String _getPassword(
		String key, int length, boolean useAllKeys) {

		int keysCount = 0;

		if (key.contains(KEY1)) {
			keysCount++;
		}

		if (key.contains(KEY2)) {
			keysCount++;
		}

		if (key.contains(KEY3)) {
			keysCount++;
		}

		if (keysCount > length) {
			if (_log.isWarnEnabled()) {
				_log.warn("Length is too short");
			}

			length = keysCount;
		}

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			sb.append(key.charAt((int)(Math.random() * key.length())));
		}

		String password = sb.toString();

		if (!useAllKeys) {
			return password;
		}

		boolean invalidPassword = false;

		if (key.contains(KEY1)) {
			if (Validator.isNull(StringUtil.extractDigits(password))) {
				invalidPassword = true;
			}
		}

		if (key.contains(KEY2)) {
			if (password.equals(password.toLowerCase())) {
				invalidPassword = true;
			}
		}

		if (key.contains(KEY3)) {
			if (password.equals(password.toUpperCase())) {
				invalidPassword = true;
			}
		}

		if (invalidPassword) {
			return _getPassword(key, length, useAllKeys);
		}

		return password;
	}

	private static Log _log = LogFactoryUtil.getLog(PwdGenerator.class);

}