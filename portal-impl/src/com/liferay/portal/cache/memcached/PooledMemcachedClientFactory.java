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

package com.liferay.portal.cache.memcached;

import net.spy.memcached.MemcachedClientIF;

import org.apache.commons.pool.ObjectPool;

/**
 * @author Michael C. Han
 */
public class PooledMemcachedClientFactory implements MemcachedClientFactory {

	public void clear() throws Exception {
		_memcachedClientPool.clear();
	}

	public void close() throws Exception {
		_memcachedClientPool.close();
	}

	public void destroy() {
		try {
			close();
		}
		catch (Exception e) {
		}
	}

	public MemcachedClientIF getMemcachedClient() throws Exception {
		return (MemcachedClientIF)_memcachedClientPool.borrowObject();
	}

	public int getNumActive() {
		return _memcachedClientPool.getNumActive();
	}

	public int getNumIdle() {
		return _memcachedClientPool.getNumIdle();
	}

	public void invalidateMemcachedClient(MemcachedClientIF memcachedClient)
		throws Exception {

		_memcachedClientPool.invalidateObject(memcachedClient);
	}

	public void returnMemcachedObject(MemcachedClientIF memcachedClient)
		throws Exception {

		_memcachedClientPool.returnObject(memcachedClient);
	}

	public void setMemcachedClientPool(ObjectPool memcachedClientPool) {
		_memcachedClientPool = memcachedClientPool;
	}

	private ObjectPool _memcachedClientPool;

}