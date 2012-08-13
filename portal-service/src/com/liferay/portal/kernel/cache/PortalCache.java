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

package com.liferay.portal.kernel.cache;

import java.io.Serializable;

import java.util.Collection;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 * @author Shuyang Zhou
 */
public interface PortalCache {

	public void destroy();

	public Collection<Object> get(Collection<Serializable> keys);

	public Object get(Serializable key);

	public String getName();

	public void put(Serializable key, Object value);

	public void put(Serializable key, Object value, int timeToLive);

	public void put(Serializable key, Serializable value);

	public void put(Serializable key, Serializable value, int timeToLive);

	public void registerCacheListener(CacheListener cacheListener);

	public void registerCacheListener(
		CacheListener cacheListener, CacheListenerScope cacheListenerScope);

	public void remove(Serializable key);

	public void removeAll();

	public void unregisterCacheListener(CacheListener cacheListener);

	public void unregisterCacheListeners();

}