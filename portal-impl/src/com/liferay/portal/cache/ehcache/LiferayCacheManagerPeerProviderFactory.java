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

package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.HtmlImpl;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.CacheManagerPeerProviderFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayCacheManagerPeerProviderFactory
	extends CacheManagerPeerProviderFactory {

	public LiferayCacheManagerPeerProviderFactory() {
		String className =
			PropsValues.EHCACHE_CACHE_MANAGER_PEER_PROVIDER_FACTORY;

		if (_log.isDebugEnabled()) {
			_log.debug("Instantiating " + className + " " + this.hashCode());
		}

		try {
			_cacheManagerPeerProviderFactory =
				(CacheManagerPeerProviderFactory)InstanceFactory.newInstance(
					className);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public CacheManagerPeerProvider createCachePeerProvider(
		CacheManager cacheManager, Properties properties) {

		String portalPropertyKey = properties.getProperty("portalPropertyKey");

		if (Validator.isNull(portalPropertyKey)) {
			throw new RuntimeException("portalPropertyKey is null");
		}

		Properties propsUtilProperties = PropsUtil.getProperties();

		String portalPropertiesString = propsUtilProperties.getProperty(
			portalPropertyKey);

		if (_log.isInfoEnabled()) {
			_log.info(
				"portalPropertyKey " + portalPropertyKey + " has value " +
					portalPropertiesString);
		}

		portalPropertiesString = StringUtil.replace(
			portalPropertiesString, CharPool.COMMA, CharPool.NEW_LINE);

		Properties portalProperties = null;

		try {
			portalProperties = PropertiesUtil.load(portalPropertiesString);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new RuntimeException(ioe.getMessage());
		}

		Object[] keys = portalProperties.keySet().toArray();

		for (Object key : keys) {
			String value = (String)portalProperties.remove(key);

			value = _htmlUtil.unescape(value);

			portalProperties.put(key, value);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(PropertiesUtil.list(portalProperties));
		}

		return _cacheManagerPeerProviderFactory.createCachePeerProvider(
			cacheManager, portalProperties);
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayCacheManagerPeerProviderFactory.class);

	private static HtmlImpl _htmlUtil = new HtmlImpl();

	private CacheManagerPeerProviderFactory _cacheManagerPeerProviderFactory;

}