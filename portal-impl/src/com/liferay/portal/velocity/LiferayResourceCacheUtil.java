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

package com.liferay.portal.velocity;

import com.liferay.portal.kernel.cache.MultiVMKeyPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;

import org.apache.velocity.runtime.resource.Resource;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayResourceCacheUtil {

	public static void clear() {
		_portalCache.removeAll();
	}

	public static Resource get(String key) {
		Object obj = _portalCache.get(key);

		if ((obj != null) && (obj instanceof Resource)) {
			return (Resource)obj;
		}
		else {
			return null;
		}
	}

	public static void put(String key, Resource resource) {
		_portalCache.put(key, resource);
	}

	public static void remove(String key) {
		_portalCache.remove(key);
	}

	private static PortalCache _portalCache = MultiVMKeyPoolUtil.getCache(
		LiferayResourceCacheUtil.class.getName());

}