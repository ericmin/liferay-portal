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

package com.liferay.portal.cache.keypool;

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.PortalCache;

import java.io.Serializable;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class MultiVMKeyPoolCacheListener implements CacheListener {

	public MultiVMKeyPoolCacheListener(PortalCache localPortalCache) {
		_localPortalCache = localPortalCache;
	}

	public void notifyEntryEvicted(
		PortalCache portalCache, Serializable key, Object value) {

		_localPortalCache.remove(key);
	}

	public void notifyEntryExpired(
		PortalCache portalCache, Serializable key, Object value) {

		_localPortalCache.remove(key);
	}

	public void notifyEntryPut(
		PortalCache portalCache, Serializable key, Object value) {

		_localPortalCache.put(key, value);
	}

	public void notifyEntryRemoved(
		PortalCache portalCache, Serializable key, Object value) {

		_localPortalCache.remove(key);
	}

	public void notifyEntryUpdated(
		PortalCache portalCache, Serializable key, Object value) {

		_localPortalCache.put(key, value);
	}

	public void notifyRemoveAll(PortalCache portalCache) {
		_localPortalCache.removeAll();
	}

	private PortalCache _localPortalCache;

}