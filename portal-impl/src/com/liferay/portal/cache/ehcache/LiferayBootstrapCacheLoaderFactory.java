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
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.PropsValues;

import java.util.Properties;

import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
import net.sf.ehcache.bootstrap.BootstrapCacheLoaderFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayBootstrapCacheLoaderFactory
	extends BootstrapCacheLoaderFactory {

	public LiferayBootstrapCacheLoaderFactory() {
		String className = PropsValues.EHCACHE_BOOTSTRAP_CACHE_LOADER_FACTORY;

		if (_log.isDebugEnabled()) {
			_log.debug("Instantiating " + className + " " + this.hashCode());
		}

		try {
			_bootstrapCacheLoaderFactory =
				(BootstrapCacheLoaderFactory)InstanceFactory.newInstance(
					className);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BootstrapCacheLoader createBootstrapCacheLoader(
		Properties properties) {

		return _bootstrapCacheLoaderFactory.createBootstrapCacheLoader(
			properties);
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayBootstrapCacheLoaderFactory.class);

	private BootstrapCacheLoaderFactory _bootstrapCacheLoaderFactory;

}