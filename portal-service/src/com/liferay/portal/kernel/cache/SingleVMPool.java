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

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public interface SingleVMPool {

	public void clear();

	public void clear(String name);

	/**
	 * @deprecated
	 */
	public Object get(PortalCache portalCache, String key);

	public Object get(String name, String key);

	public PortalCache getCache(String name);

	public PortalCache getCache(String name, boolean blocking);

	/**
	 * @deprecated
	 */
	public void put(PortalCache portalCache, String key, Object value);

	/**
	 * @deprecated
	 */
	public void put(
		PortalCache portalCache, String key, Object value, int timeToLive);

	/**
	 * @deprecated
	 */
	public void put(PortalCache portalCache, String key, Serializable value);

	/**
	 * @deprecated
	 */
	public void put(
		PortalCache portalCache, String key, Serializable value,
		int timeToLive);

	public void put(String name, String key, Object value);

	public void put(String name, String key, Serializable value);

	/**
	 * @deprecated
	 */
	public void remove(PortalCache portalCache, String key);

	public void remove(String name, String key);

	public void removeCache(String name);

}