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
import com.liferay.portal.kernel.util.IPDetector;
import com.liferay.portal.kernel.util.OSDetector;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.distribution.CacheManagerPeerProvider;
import net.sf.ehcache.distribution.CacheManagerPeerProviderFactory;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-11061.
 * </p>
 *
 * @author Tina Tian
 */
public class JGroupsCacheManagerPeerProviderFactory
	extends CacheManagerPeerProviderFactory {

	@Override
	public CacheManagerPeerProvider createCachePeerProvider(
		CacheManager cacheManager, Properties properties) {

		if (_log.isDebugEnabled()) {
			_log.debug("Creating JGroups peer provider");
		}

		String clusterName = properties.getProperty("clusterName");

		if (clusterName == null) {
			clusterName = _DEFAULT_CLUSTER_NAME;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Cluster name " + clusterName);
		}

		String channelProperties = properties.getProperty("channelProperties");

		if (channelProperties != null) {
			channelProperties = channelProperties.replaceAll(
				StringPool.SPACE, StringPool.BLANK);

			if (Validator.isNull(channelProperties)) {
				channelProperties = null;
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Channel properties " + channelProperties);
		}

		if (!_initialized) {
			if (OSDetector.isUnix() && IPDetector.isSupportsV6() &&
				!IPDetector.isPrefersV4() && _log.isWarnEnabled()) {

				StringBundler sb = new StringBundler(4);

				sb.append(
					"You are on an Unix server with IPv6 enabled. JGroups ");
				sb.append("may not work with IPv6. If you see a multicast ");
				sb.append("error, try adding java.net.preferIPv4Stack=true ");
				sb.append("as a JVM startup parameter.");

				_log.warn(sb.toString());
			}

			_initialized = true;
		}

		return new JGroupsManager(cacheManager, clusterName, channelProperties);
	}

	private static final String _DEFAULT_CLUSTER_NAME = "Ehcache";

	private static Log _log = LogFactoryUtil.getLog(
		JGroupsCacheManagerPeerProviderFactory.class);

	private static boolean _initialized;

}