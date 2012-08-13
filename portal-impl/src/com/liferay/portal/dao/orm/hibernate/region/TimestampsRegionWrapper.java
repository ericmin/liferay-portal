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

package com.liferay.portal.dao.orm.hibernate.region;

import net.sf.ehcache.hibernate.regions.EhcacheTimestampsRegion;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.TimestampsRegion;

/**
 * @author Edward Han
 */
public class TimestampsRegionWrapper
	extends BaseRegionWrapper implements TimestampsRegion {

	public TimestampsRegionWrapper(
		EhcacheTimestampsRegion ehcacheTimestampsRegion) {

		super(ehcacheTimestampsRegion);
	}

	public void evict(Object key) throws CacheException {
		EhcacheTimestampsRegion ehcacheTimestampsRegion =
			getEhcacheTimestampsRegion();

		ehcacheTimestampsRegion.evict(key);
	}

	public void evictAll() throws CacheException {
		EhcacheTimestampsRegion ehcacheTimestampsRegion =
			getEhcacheTimestampsRegion();

		ehcacheTimestampsRegion.evictAll();
	}

	public Object get(Object key) throws CacheException {
		EhcacheTimestampsRegion ehcacheTimestampsRegion =
			getEhcacheTimestampsRegion();

		return ehcacheTimestampsRegion.get(key);
	}

	public void invalidate() {
		EhcacheTimestampsRegion ehcacheTimestampsRegion =
			getEhcacheTimestampsRegion();

		ehcacheTimestampsRegion.evictAll();
	}

	public void put(Object key, Object value) throws CacheException {
		EhcacheTimestampsRegion ehcacheTimestampsRegion =
			getEhcacheTimestampsRegion();

		ehcacheTimestampsRegion.put(key, value);
	}

	protected EhcacheTimestampsRegion getEhcacheTimestampsRegion() {
		return (EhcacheTimestampsRegion)getEhcacheDataRegion();
	}

}