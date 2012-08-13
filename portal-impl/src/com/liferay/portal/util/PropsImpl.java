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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.Props;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class PropsImpl implements Props {

	public boolean contains(String key) {
		return PropsUtil.contains(key);
	}

	public String get(String key) {
		return PropsUtil.get(key);
	}

	public String get(String key, Filter filter) {
		return PropsUtil.get(key, filter);
	}

	public String[] getArray(String key) {
		return PropsUtil.getArray(key);
	}

	public String[] getArray(String key, Filter filter) {
		return PropsUtil.getArray(key, filter);
	}

	public Properties getProperties() {
		return PropsUtil.getProperties();
	}

	public Properties getProperties(String prefix, boolean removePrefix) {
		return PropsUtil.getProperties(prefix, removePrefix);
	}

}