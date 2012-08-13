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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FriendlyURLNormalizer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.Normalizer;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class FriendlyURLNormalizerImpl implements FriendlyURLNormalizer {

	public String normalize(String friendlyURL) {
		return normalize(friendlyURL, null);
	}

	public String normalize(String friendlyURL, char[] replaceChars) {
		if (Validator.isNull(friendlyURL)) {
			return friendlyURL;
		}

		friendlyURL = GetterUtil.getString(friendlyURL);
		friendlyURL = friendlyURL.toLowerCase();
		friendlyURL = Normalizer.normalizeToAscii(friendlyURL);

		StringBuilder sb = null;

		int index = 0;

		for (int i = 0; i < friendlyURL.length(); i++) {
			char c = friendlyURL.charAt(i);

			if ((Arrays.binarySearch(_REPLACE_CHARS, c) >= 0) ||
				((replaceChars != null) &&
				 ArrayUtil.contains(replaceChars, c))) {

				if (sb == null) {
					sb = new StringBuilder();
				}

				if (i > index) {
					sb.append(friendlyURL.substring(index, i));
				}

				sb.append(CharPool.DASH);

				index = i + 1;
			}
		}

		if (sb != null) {
			if (index < friendlyURL.length()) {
				sb.append(friendlyURL.substring(index));
			}

			friendlyURL = sb.toString();
		}

		while (friendlyURL.indexOf(StringPool.DOUBLE_DASH) >= 0) {
			friendlyURL = StringUtil.replace(
				friendlyURL, StringPool.DOUBLE_DASH, StringPool.DASH);
		}

		/*if (friendlyURL.startsWith(StringPool.DASH)) {
			friendlyURL = friendlyURL.substring(1);
		}

		if (friendlyURL.endsWith(StringPool.DASH)) {
			friendlyURL = friendlyURL.substring(0, friendlyURL.length() - 1);
		}*/

		return friendlyURL;
	}

	private static final char[] _REPLACE_CHARS;

	static {
		char[] replaceChars = new char[] {
			' ', ',', '\\', '\'', '\"', '(', ')', '[', ']', '{', '}', '?', '#',
			'@', '+', '~', ';', '$', '%', '!', '=', ':', '&'
		};

		Arrays.sort(replaceChars);

		_REPLACE_CHARS = replaceChars;
	}

}