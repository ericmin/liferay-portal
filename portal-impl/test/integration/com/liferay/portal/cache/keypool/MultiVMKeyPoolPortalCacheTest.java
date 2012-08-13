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

package com.liferay.portal.cache.keypool;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MultiVMKeyPoolPortalCacheTest {

	@Test
	public void testPutAndGet() {
		PortalCache clusterPortalCache = SingleVMPoolUtil.getCache(
			"ClusterPortalCache");
		PortalCache localPortalCache = SingleVMPoolUtil.getCache(
			"LocalPortalCache");

		MultiVMKeyPoolPortalCache multiVMKeyPoolPortalCache =
			new MultiVMKeyPoolPortalCache(clusterPortalCache, localPortalCache);

		String testKey = "testKey";
		String testValue = "testValue";

		multiVMKeyPoolPortalCache.put(testKey, testValue);

		Assert.assertEquals(testValue, multiVMKeyPoolPortalCache.get(testKey));
	}

}