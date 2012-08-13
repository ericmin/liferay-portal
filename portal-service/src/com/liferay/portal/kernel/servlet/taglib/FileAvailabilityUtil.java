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
import com.liferay.portal.kernel.util.Validator;

import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;

/**
 * @author Shuyang Zhou
 */
public class FileAvailabilityUtil {

	public static boolean isAvailable(
		ServletContext servletContext, String path) {

		if (Validator.isNull(path)) {
			return false;
		}

		if (path.charAt(0) != CharPool.SLASH) {
			return true;
		}

		Boolean available = _availabilities.get(path);

		if (available == null) {
			URL url = null;

			try {
				url = servletContext.getResource(path);
			}
			catch (Exception e) {
			}

			if (url == null) {
				available = Boolean.FALSE;
			}
			else {
				available = Boolean.TRUE;
			}

			_availabilities.put(path, available);
		}

		return available;
	}

	public static void reset() {
		_availabilities.clear();
	}

	private static Map<String, Boolean> _availabilities =
		new ConcurrentHashMap<String, Boolean>();

}