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

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.CacheListenerScope;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.MemcachedClientIF;

/**
 * @author Michael C. Han
 */
public class MemcachePortalCache implements PortalCache {

	public MemcachePortalCache(
		String name, MemcachedClientIF memcachedClient, int timeout,
		TimeUnit timeoutTimeUnit) {

		_name = name;
		_memcachedClient = memcachedClient;
		_timeout = timeout;
		_timeoutTimeUnit = timeoutTimeUnit;
	}

	public void destroy() {
		_memcachedClient.shutdown();
	}

	public Collection<Object> get(Collection<Serializable> keys) {
		List<String> processedKeys = new ArrayList<String>(keys.size());

		for (Serializable key : keys) {
			String processedKey = _name.concat(String.valueOf(key));

			processedKeys.add(processedKey);
		}

		Future<Map<String, Object>> future = null;

		try {
			future = _memcachedClient.asyncGetBulk(processedKeys);
		}
		catch (IllegalArgumentException iae) {
			if (_log.isWarnEnabled()) {
				_log.warn("Error retrieving with keys " + keys, iae);
			}

			return null;
		}

		Map<String, Object> values = null;

		try {
			values = future.get(_timeout, _timeoutTimeUnit);
		}
		catch (Throwable t) {
			if (_log.isWarnEnabled()) {
				_log.warn("Memcache operation error", t);
			}

			future.cancel(true);
		}

		if (values != null) {
			return values.values();
		}

		return null;
	}

	public Object get(Serializable key) {
		String processedKey = _name.concat(String.valueOf(key));

		Future<Object> future = null;

		try {
			future = _memcachedClient.asyncGet(processedKey);
		}
		catch (IllegalArgumentException iae) {
			if (_log.isWarnEnabled()) {
				_log.warn("Error retrieving with key " + key, iae);
			}

			return null;
		}

		Object value = null;

		try {
			value = future.get(_timeout, _timeoutTimeUnit);
		}
		catch (Throwable t) {
			if (_log.isWarnEnabled()) {
				_log.warn("Memcache operation error", t);
			}

			future.cancel(true);
		}

		return value;
	}

	public String getName() {
		return _name;
	}

	public void put(Serializable key, Object value) {
		put(key, value, _timeToLive);
	}

	public void put(Serializable key, Object value, int timeToLive) {
		String processedKey = _name.concat(String.valueOf(key));

		try {
			_memcachedClient.set(processedKey, timeToLive, value);
		}
		catch (IllegalArgumentException iae) {
			if (_log.isWarnEnabled()) {
				_log.warn("Error storing value with key " + key, iae);
			}
		}
	}

	public void put(Serializable key, Serializable value) {
		put(key, value, _timeToLive);
	}

	public void put(Serializable key, Serializable value, int timeToLive) {
		String processedKey = _name.concat(String.valueOf(key));

		try {
			_memcachedClient.set(processedKey, timeToLive, value);
		}
		catch (IllegalArgumentException iae) {
			if (_log.isWarnEnabled()) {
				_log.warn("Error storing value with key " + key, iae);
			}
		}
	}

	public void registerCacheListener(CacheListener cacheListener) {
		registerCacheListener(cacheListener, CacheListenerScope.ALL);
	}

	public void registerCacheListener(
		CacheListener cacheListener, CacheListenerScope cacheListenerScope) {

		throw new UnsupportedOperationException();
	}

	public void remove(Serializable key) {
		String processedKey = _name.concat(String.valueOf(key));

		try {
			_memcachedClient.delete(processedKey);
		}
		catch (IllegalArgumentException iae) {
			if (_log.isWarnEnabled()) {
				_log.warn("Error removing value with key " + key, iae);
			}
		}
	}

	public void removeAll() {
		_memcachedClient.flush();
	}

	public void setTimeToLive(int timeToLive) {
		_timeToLive = timeToLive;
	}

	public void unregisterCacheListener(CacheListener cacheListener) {
	}

	public void unregisterCacheListeners() {
	}

	private static Log _log = LogFactoryUtil.getLog(MemcachePortalCache.class);

	private MemcachedClientIF _memcachedClient;
	private String _name;
	private int _timeout;
	private TimeUnit _timeoutTimeUnit;
	private int _timeToLive;

}