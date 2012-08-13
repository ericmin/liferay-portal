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

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Shuyang Zhou
 */
public class FileTimestampUtil {

	public static long getTimestamp(
		ServletContext servletContext, String path) {

		return getTimestamp(servletContext, path, 0);
	}

	public static long getTimestamp(
		ServletContext servletContext, String path, long defaultTimestamp) {

		if (Validator.isNull(path)) {
			return defaultTimestamp;
		}

		if (path.charAt(0) != CharPool.SLASH) {
			return defaultTimestamp;
		}

		Long timestamp = _timestamps.get(path);

		if (timestamp != null) {
			return timestamp;
		}

		timestamp = defaultTimestamp;

		String uriRealPath = ServletContextUtil.getRealPath(
			servletContext, path);

		if (uriRealPath != null) {
			File uriFile = new File(uriRealPath);

			if (uriFile.exists()) {
				timestamp = uriFile.lastModified();
			}
		}

		_timestamps.put(path, timestamp);

		return timestamp;
	}

	public static void reset() {
		_timestamps.clear();
	}

	private static Map<String, Long> _timestamps =
		new ConcurrentHashMap<String, Long>();

}