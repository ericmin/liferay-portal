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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.memory.DummyFinalizeAction;
import com.liferay.portal.kernel.memory.FinalizeManager;
import com.liferay.portal.kernel.test.BaseTestCase;

/**
 * @author Shuyang Zhou
 */
public class WeakValueConcurrentHashMapTest extends BaseTestCase {

	public void testAutoRemove() throws Exception {
		WeakValueConcurrentHashMap<String, Object> weakValueConcurrentHashMap =
			new WeakValueConcurrentHashMap<String, Object>();

		String testKey = "testKey";
		Object testValue = new Object();

		weakValueConcurrentHashMap.put(testKey, testValue);

		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < 100) {
			System.gc();

			Thread.sleep(1);

			assertTrue(weakValueConcurrentHashMap.containsKey(testKey));
		}

		testValue = null;

		startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < 100) {
			System.gc();

			Thread.sleep(1);

			if (!FinalizeManager.THREAD_ENABLED) {
				FinalizeManager.register(
					new Object(), new DummyFinalizeAction());
			}

			if (!weakValueConcurrentHashMap.containsKey(testKey)) {
				break;
			}
		}

		assertFalse(weakValueConcurrentHashMap.containsKey(testKey));
	}

}