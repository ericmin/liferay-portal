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
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class DiscardOldestPolicyTest extends TestCase {

	public void testDiscardOldestPolicy1() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 1, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 1,
			new DiscardOldestPolicy(), Executors.defaultThreadFactory(),
			new ThreadPoolHandlerAdapter());

		threadPoolExecutor.shutdown();

		MarkerBlockingJob markerBlockingJob = new MarkerBlockingJob();

		threadPoolExecutor.execute(markerBlockingJob);

		assertFalse(markerBlockingJob.isStarted());
	}

	public void testDiscardOldestPolicy2() throws InterruptedException {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 1, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 1,
			new DiscardOldestPolicy(), Executors.defaultThreadFactory(),
			new ThreadPoolHandlerAdapter());

		try {
			MarkerBlockingJob markerBlockingJob1 = new MarkerBlockingJob(true);
			MarkerBlockingJob markerBlockingJob2 = new MarkerBlockingJob(true);
			MarkerBlockingJob markerBlockingJob3 = new MarkerBlockingJob();

			threadPoolExecutor.execute(markerBlockingJob1);

			markerBlockingJob1.waitUntilBlock();

			threadPoolExecutor.execute(markerBlockingJob2);

			assertEquals(1, threadPoolExecutor.getActiveCount());
			assertEquals(1, threadPoolExecutor.getPendingTaskCount());

			threadPoolExecutor.execute(markerBlockingJob3);

			markerBlockingJob1.unBlock();

			TestUtil.waitUntilEnded(markerBlockingJob1);

			assertEquals(0, threadPoolExecutor.getActiveCount());
			assertEquals(0, threadPoolExecutor.getPendingTaskCount());
			assertTrue(markerBlockingJob1.isEnded());
			assertFalse(markerBlockingJob2.isStarted());
			assertTrue(markerBlockingJob3.isEnded());
		}
		finally {
			TestUtil.closePool(threadPoolExecutor);
		}
	}

}