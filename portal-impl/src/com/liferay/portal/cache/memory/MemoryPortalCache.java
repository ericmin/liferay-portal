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

package com.liferay.portal.cache.memory;

import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.CacheListenerScope;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 * @author Shuyang Zhou
 */
public class MemoryPortalCache implements PortalCache {

	public MemoryPortalCache(String name, int initialCapacity) {
		_name = name;
		_map = new ConcurrentHashMap<Serializable, Object>(initialCapacity);
	}

	public void destroy() {
		removeAll();

		_cacheListeners = null;
		_map = null;
		_name = null;
	}

	public Collection<Object> get(Collection<Serializable> keys) {
		List<Object> values = new ArrayList<Object>(keys.size());

		for (Serializable key : keys) {
			values.add(get(key));
		}

		return values;
	}

	public Object get(Serializable key) {
		return _map.get(key);
	}

	public String getName() {
		return _name;
	}

	public void put(Serializable key, Object value) {
		Object oldValue = _map.put(key, value);

		notifyPutEvents(key, value, oldValue != null);
	}

	public void put(Serializable key, Object value, int timeToLive) {
		Object oldValue = _map.put(key, value);

		notifyPutEvents(key, value, oldValue != null);
	}

	public void put(Serializable key, Serializable value) {
		Object oldValue = _map.put(key, value);

		notifyPutEvents(key, value, oldValue != null);
	}

	public void put(Serializable key, Serializable value, int timeToLive) {
		Object oldValue = _map.put(key, value);

		notifyPutEvents(key, value, oldValue != null);
	}

	public void registerCacheListener(CacheListener cacheListener) {
		_cacheListeners.add(cacheListener);
	}

	public void registerCacheListener(
		CacheListener cacheListener, CacheListenerScope cacheListenerScope) {

		registerCacheListener(cacheListener);
	}

	public void remove(Serializable key) {
		Object value = _map.remove(key);

		for (CacheListener cacheListener : _cacheListeners) {
			cacheListener.notifyEntryRemoved(this, key, value);
		}
	}

	public void removeAll() {
		_map.clear();

		for (CacheListener cacheListener : _cacheListeners) {
			cacheListener.notifyRemoveAll(this);
		}
	}

	public void unregisterCacheListener(CacheListener cacheListener) {
		_cacheListeners.remove(cacheListener);
	}

	public void unregisterCacheListeners() {
		_cacheListeners.clear();
	}

	protected void notifyPutEvents(
		Serializable key, Object value, boolean updated) {

		if (updated) {
			for (CacheListener cacheListener : _cacheListeners) {
				cacheListener.notifyEntryUpdated(this, key, value);
			}
		}
		else {
			for (CacheListener cacheListener : _cacheListeners) {
				cacheListener.notifyEntryPut(this, key, value);
			}
		}
	}

	private Set<CacheListener> _cacheListeners =
		new ConcurrentHashSet<CacheListener>();
	private Map<Serializable, Object> _map;
	private String _name;

}