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

import java.util.Iterator;

import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.ResourceCache;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayResourceCache implements ResourceCache {

	public Iterator<Object> enumerateKeys() {
		throw new RuntimeException("enumerateKeys is not implemented");
	}

	public Resource get(Object key) {
		return LiferayResourceCacheUtil.get(key.toString());
	}

	public void initialize(RuntimeServices rs) {
	}

	public Resource put(Object key, Resource resource) {
		LiferayResourceCacheUtil.put(key.toString(), resource);

		return resource;
	}

	public Resource remove(Object key) {
		Resource resource = get(key);

		if (resource != null) {
			LiferayResourceCacheUtil.remove(key.toString());
		}

		return resource;
	}

}