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

import net.spy.memcached.MemcachedClientIF;

/**
 * @author Michael C. Han
 */
public class MemcachePortalCacheManager implements PortalCacheManager {

	public void clearAll() {
		_memcachePortalCaches.clear();
	}

	public void destroy() throws Exception {
		for (MemcachePortalCache memcachePortalCache :
				_memcachePortalCaches.values()) {

			memcachePortalCache.destroy();
		}
	}

	public PortalCache getCache(String name) {
		return getCache(name, false);
	}

	public PortalCache getCache(String name, boolean blocking) {
		MemcachePortalCache memcachePortalCache = _memcachePortalCaches.get(
			name);

		if (memcachePortalCache == null) {
			try {
				MemcachedClientIF memcachedClient =
					_memcachedClientFactory.getMemcachedClient();

				memcachePortalCache = new MemcachePortalCache(
					name, memcachedClient, _timeout, _timeoutTimeUnit);

				_memcachePortalCaches.put(name, memcachePortalCache);
			}
			catch (Exception e) {
				throw new IllegalStateException(
					"Unable to initiatlize Memcache connection", e);
			}
		}

		return memcachePortalCache;
	}

	public void reconfigureCaches(URL configurationURL) {
	}

	public void removeCache(String name) {
		_memcachePortalCaches.remove(name);
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
	private Map<String, MemcachePortalCache> _memcachePortalCaches =
		new ConcurrentHashMap<String, MemcachePortalCache>();
	private int _timeout;
	private TimeUnit _timeoutTimeUnit;

}