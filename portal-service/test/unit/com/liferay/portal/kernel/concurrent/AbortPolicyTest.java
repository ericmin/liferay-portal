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

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.test.TestCase;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class AbortPolicyTest extends TestCase {

	public void testAbortPolicy() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 2, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 3,
			new AbortPolicy(), Executors.defaultThreadFactory(),
			new ThreadPoolHandlerAdapter());

		threadPoolExecutor.shutdown();

		try {
			threadPoolExecutor.execute(new MarkerBlockingJob());

			fail();
		}
		catch (RejectedExecutionException ree) {
		}
	}

}