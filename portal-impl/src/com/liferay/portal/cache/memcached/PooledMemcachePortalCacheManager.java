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

package com.liferay.portal.cache.memcached;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.net.URL;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael C. Han
 */
public class PooledMemcachePortalCacheManager implements PortalCacheManager {

	public void afterPropertiesSet() {
	}

	public void clearAll() {
		_portalCaches.clear();
	}

	public void destroy() throws Exception {
		for (PortalCache portalCache : _portalCaches.values()) {
			portalCache.destroy();
		}
	}

	public PortalCache getCache(String name) {
		return getCache(name, false);
	}

	public PortalCache getCache(String name, boolean blocking) {
		PortalCache portalCache = _portalCaches.get(name);

		if (portalCache == null) {
			portalCache = new PooledMemcachePortalCache(
				name, _memcachedClientFactory, _timeout, _timeoutTimeUnit);

			_portalCaches.put(name, portalCache);
		}

		return portalCache;
	}

	public void reconfigureCaches(URL configurationURL) {
	}

	public void removeCache(String name) {
		_portalCaches.remove(name);
	}

	public void setMemcachedClientPool(
		MemcachedClientFactory memcachedClientFactory) {

		_memcachedClientFactory = memcachedClientFactory;
	}

	public void setTimeout(int timeout) {
		_timeout = timeout;
	}

	public void setTimeoutTimeUnit(String timeoutTimeUnit) {
		_timeoutTimeUnit = TimeUnit.valueOf(timeoutTimeUnit);
	}

	private MemcachedClientFactory _memcachedClientFactory;
	private Map<String, PortalCache> _portalCaches =
		new ConcurrentHashMap<String, PortalCache>();
	private int _timeout;
	private TimeUnit _timeoutTimeUnit;

}