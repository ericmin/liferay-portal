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

package com.liferay.portal.velocity;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.util.StringPool;

import org.apache.velocity.runtime.resource.util.StringResource;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

/**
 * @author Raymond Augé
 */
public class StringResourceRepositoryImpl implements StringResourceRepository {

	public String getEncoding() {
		return _encoding;
	}

	public StringResource getStringResource(String key) {
		Object resource = _portalCache.get(key);

		if ((resource != null) &&
			(resource instanceof SerializableStringResource)) {

			SerializableStringResource serializableStringResource =
				(SerializableStringResource)resource;

			return serializableStringResource.toStringResource();
		}

		return null;
	}

	public void putStringResource(String key, String body) {
		_portalCache.put(
			key, new SerializableStringResource(body, getEncoding()));
	}

	public void putStringResource(String key, String body, String encoding) {
		_portalCache.put(key, new SerializableStringResource(body, encoding));
	}

	public void removeStringResource(String key) {
		_portalCache.remove(key);
	}

	public void setEncoding(String encoding) {
		_encoding = encoding;
	}

	private static final String _CACHE_NAME =
		StringResourceRepository.class.getName();

	private static PortalCache _portalCache = MultiVMPoolUtil.getCache(
		_CACHE_NAME);

	private String _encoding = StringPool.UTF8;

}