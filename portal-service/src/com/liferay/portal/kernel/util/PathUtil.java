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

package com.liferay.portal.kernel.util;

/**
 * @author Brian Wing Shun Chan
 */
public class PathUtil {

	public static String toUnixPath(String path) {
		return StringUtil.replace(
			path,
			new String[] {
				StringPool.BACK_SLASH, StringPool.DOUBLE_SLASH
			},
			new String[] {StringPool.SLASH, StringPool.SLASH});
	}

	public static String toWindowsPath(String path) {
		return StringUtil.replace(
			path,
			new String[] {
				StringPool.SLASH, StringPool.DOUBLE_BACK_SLASH
			},
			new String[] {StringPool.BACK_SLASH, StringPool.BACK_SLASH});
	}

}