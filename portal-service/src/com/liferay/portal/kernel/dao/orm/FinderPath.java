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

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.BaseModel;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class FinderPath {

	public FinderPath(
		boolean entityCacheEnabled, boolean finderCacheEnabled,
		Class<?> resultClass, String cacheName, String methodName,
		String[] params) {

		this(
			entityCacheEnabled, finderCacheEnabled, resultClass, cacheName,
			methodName, params, -1);
	}

	public FinderPath(
		boolean entityCacheEnabled, boolean finderCacheEnabled,
		Class<?> resultClass, String cacheName, String methodName,
		String[] params, long columnBitmask) {

		_entityCacheEnabled = entityCacheEnabled;
		_finderCacheEnabled = finderCacheEnabled;
		_resultClass = resultClass;
		_cacheName = cacheName;
		_columnBitmask = columnBitmask;

		if (BaseModel.class.isAssignableFrom(_resultClass)) {
			_cacheKeyGeneratorCacheName =
				FinderCache.class.getName() + "#BaseModel";
		}
		else {
			_cacheKeyGeneratorCacheName = FinderCache.class.getName();
		}

		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				_cacheKeyGeneratorCacheName);

		if (cacheKeyGenerator.isCallingGetCacheKeyThreadSafe()) {
			_cacheKeyGenerator = cacheKeyGenerator;
		}

		_initCacheKeyPrefix(methodName, params);
		_initLocalCacheKeyPrefix();
	}

	public Serializable encodeCacheKey(Object[] arguments) {
		StringBundler sb = new StringBundler(arguments.length * 2 + 3);

		sb.append(ShardUtil.getCurrentShardName());
		sb.append(StringPool.PERIOD);
		sb.append(_cacheKeyPrefix);

		for (Object arg : arguments) {
			sb.append(StringPool.PERIOD);
			sb.append(StringUtil.toHexString(arg));
		}

		return _getCacheKey(sb);
	}

	public Serializable encodeLocalCacheKey(Object[] arguments) {
		StringBundler sb = new StringBundler(arguments.length * 2 + 3);

		sb.append(ShardUtil.getCurrentShardName());
		sb.append(StringPool.PERIOD);
		sb.append(_localCacheKeyPrefix);

		for (Object arg : arguments) {
			sb.append(StringPool.PERIOD);
			sb.append(StringUtil.toHexString(arg));
		}

		return _getCacheKey(sb);
	}

	public String getCacheName() {
		return _cacheName;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	public Class<?> getResultClass() {
		return _resultClass;
	}

	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	public void setCacheKeyGeneratorCacheName(
		String cacheKeyGeneratorCacheName) {

		if (cacheKeyGeneratorCacheName == null) {
			cacheKeyGeneratorCacheName = FinderCache.class.getName();
		}

		_cacheKeyGeneratorCacheName = cacheKeyGeneratorCacheName;

		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				cacheKeyGeneratorCacheName);

		if (cacheKeyGenerator.isCallingGetCacheKeyThreadSafe()) {
			_cacheKeyGenerator = cacheKeyGenerator;
		}
		else {
			_cacheKeyGenerator = null;
		}
	}

	private Serializable _getCacheKey(StringBundler sb) {
		CacheKeyGenerator cacheKeyGenerator = _cacheKeyGenerator;

		if (cacheKeyGenerator == null) {
			cacheKeyGenerator = CacheKeyGeneratorUtil.getCacheKeyGenerator(
				_cacheKeyGeneratorCacheName);
		}

		return cacheKeyGenerator.getCacheKey(sb);
	}

	private void _initCacheKeyPrefix(String methodName, String[] params) {
		StringBundler sb = new StringBundler(params.length * 2 + 3);

		sb.append(methodName);
		sb.append(_PARAMS_SEPARATOR);

		for (String param : params) {
			sb.append(StringPool.PERIOD);
			sb.append(param);
		}

		sb.append(_ARGS_SEPARATOR);

		_cacheKeyPrefix = sb.toString();
	}

	private void _initLocalCacheKeyPrefix() {
		_localCacheKeyPrefix = _cacheName.concat(StringPool.PERIOD).concat(
			_cacheKeyPrefix);
	}

	private static final String _ARGS_SEPARATOR = "_A_";

	private static final String _PARAMS_SEPARATOR = "_P_";

	private CacheKeyGenerator _cacheKeyGenerator;
	private String _cacheKeyGeneratorCacheName;
	private String _cacheKeyPrefix;
	private String _cacheName;
	private long _columnBitmask;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
	private String _localCacheKeyPrefix;
	private Class<?> _resultClass;

}