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

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.hibernate.cache.CacheDataDescription;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CollectionRegion;
import org.hibernate.cache.EntityRegion;
import org.hibernate.cache.QueryResultsRegion;
import org.hibernate.cache.RegionFactory;
import org.hibernate.cache.TimestampsRegion;
import org.hibernate.cache.access.AccessType;
import org.hibernate.cfg.Settings;

/**
 * @author Edward Han
 */
public class SingletonLiferayEhcacheRegionFactory implements RegionFactory {

	public static LiferayEhcacheRegionFactory getInstance() {
		return _liferayEhcacheRegionFactory;
	}

	public SingletonLiferayEhcacheRegionFactory(Properties properties) {
		synchronized (this) {
			if (_liferayEhcacheRegionFactory == null) {
				_liferayEhcacheRegionFactory = new LiferayEhcacheRegionFactory(
					properties);
			}
		}
	}

	public CollectionRegion buildCollectionRegion(
			String regionName, Properties properties,
			CacheDataDescription cacheDataDescription)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildCollectionRegion(
			regionName, properties, cacheDataDescription);
	}

	public EntityRegion buildEntityRegion(
			String regionName, Properties properties,
			CacheDataDescription cacheDataDescription)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildEntityRegion(
			regionName, properties, cacheDataDescription);
	}

	public QueryResultsRegion buildQueryResultsRegion(
			String regionName, Properties properties)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildQueryResultsRegion(
			regionName, properties);
	}

	public TimestampsRegion buildTimestampsRegion(
			String regionName, Properties properties)
		throws CacheException {

		return _liferayEhcacheRegionFactory.buildTimestampsRegion(
			regionName, properties);
	}

	public AccessType getDefaultAccessType() {
		return _liferayEhcacheRegionFactory.getDefaultAccessType();
	}

	public boolean isMinimalPutsEnabledByDefault() {
		return _liferayEhcacheRegionFactory.isMinimalPutsEnabledByDefault();
	}

	public long nextTimestamp() {
		return _liferayEhcacheRegionFactory.nextTimestamp();
	}

	public synchronized void start(Settings settings, Properties properties)
		throws CacheException {

		if (_instanceCounter.getAndIncrement() == 0) {
			_liferayEhcacheRegionFactory.start(settings, properties);
		}
	}

	public synchronized void stop() {
		if (_instanceCounter.decrementAndGet() == 0) {
			_liferayEhcacheRegionFactory.stop();
		}
	}

	private static AtomicInteger _instanceCounter = new AtomicInteger(0);
	private static LiferayEhcacheRegionFactory _liferayEhcacheRegionFactory;

}