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

import com.liferay.portal.kernel.cache.CacheListenerScope;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.cache.SingleVMPool;

import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class MultiVMKeyPoolPortalCacheManager implements PortalCacheManager {

	public void clearAll() {
		for (MultiVMKeyPoolPortalCache multiVMKeyPoolPortalCache :
				_multiVMKeyPoolPortalCaches.values()) {

			multiVMKeyPoolPortalCache.removeAll();
		}
	}

	public PortalCache getCache(String name) {
		return getCache(name, false);
	}

	public PortalCache getCache(String name, boolean blocking) {
		MultiVMKeyPoolPortalCache multiVMKeyPoolPortalCache =
			_multiVMKeyPoolPortalCaches.get(name);

		if (multiVMKeyPoolPortalCache != null) {
			return multiVMKeyPoolPortalCache;
		}

		synchronized (_multiVMKeyPoolPortalCaches) {
			PortalCache clusterPortalCache = _multiVMPool.getCache(
				name, blocking);
			PortalCache localPortalCache = _singleVMPool.getCache(
				name, blocking);

			multiVMKeyPoolPortalCache = new MultiVMKeyPoolPortalCache(
				clusterPortalCache, localPortalCache);

			multiVMKeyPoolPortalCache.registerCacheListener(
				new MultiVMKeyPoolCacheListener(localPortalCache),
				CacheListenerScope.REMOTE);

			_multiVMKeyPoolPortalCaches.put(name, multiVMKeyPoolPortalCache);
		}

		return multiVMKeyPoolPortalCache;
	}

	public void reconfigureCaches(URL configurationURL) {
	}

	public void removeCache(String name) {
		synchronized (_multiVMKeyPoolPortalCaches) {
			MultiVMKeyPoolPortalCache multiVMKeyPoolPortalCache =
				_multiVMKeyPoolPortalCaches.get(name);

			if (multiVMKeyPoolPortalCache != null) {
				_multiVMPool.removeCache(name);
				_singleVMPool.removeCache(name);

				_multiVMKeyPoolPortalCaches.remove(name);
			}
		}
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		_multiVMPool = multiVMPool;
	}

	public void setSingleVMPool(SingleVMPool singleVMPool) {
		_singleVMPool = singleVMPool;
	}

	private Map<String, MultiVMKeyPoolPortalCache> _multiVMKeyPoolPortalCaches =
		new ConcurrentHashMap<String, MultiVMKeyPoolPortalCache>();
	private MultiVMPool _multiVMPool;
	private SingleVMPool _singleVMPool;

}