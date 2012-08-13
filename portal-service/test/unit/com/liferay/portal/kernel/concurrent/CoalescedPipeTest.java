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

import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 */
public class CoalescedPipeTest extends TestCase {

	public void testBlockingTake() throws InterruptedException {
		final CoalescedPipe<String> coalescedPipe = new CoalescedPipe<String>();

		ScheduledExecutorService scheduledExecutorService =
			Executors.newScheduledThreadPool(1);

		scheduledExecutorService.schedule(
			new Runnable() {

				public void run() {
					try {
						coalescedPipe.put("test1");
					}
					catch (InterruptedException ie) {
						fail(ie.getMessage());
					}
				}

			},
			500, TimeUnit.MILLISECONDS
		);

		long startTime = System.currentTimeMillis();

		assertEquals("test1", coalescedPipe.take());
		assertTrue((System.currentTimeMillis() - startTime) > 250L);

		scheduledExecutorService.shutdownNow();
		scheduledExecutorService.awaitTermination(120, TimeUnit.SECONDS);
	}

	public void testNonBlockingTake() throws InterruptedException {
		CoalescedPipe<String> coalescedPipe = new CoalescedPipe<String>();

		coalescedPipe.put("test2");
		coalescedPipe.put("test3");

		long startTime = System.currentTimeMillis();

		assertEquals("test2", coalescedPipe.take());
		assertTrue((System.currentTimeMillis() - startTime) < 100);

		startTime = System.currentTimeMillis();

		assertEquals("test3", coalescedPipe.take());
		assertTrue((System.currentTimeMillis() - startTime) < 100);
	}

	public void testPut() throws InterruptedException {

		// Without comparator

		CoalescedPipe<String> coalescedPipe = new CoalescedPipe<String>();

		// Null

		try {
			coalescedPipe.put(null);

			fail();
		}
		catch (NullPointerException npe) {
		}

		// Normal

		coalescedPipe.put("test1");

		assertEquals(1, coalescedPipe.pendingCount());
		assertEquals(0, coalescedPipe.coalescedCount());

		coalescedPipe.put("test2");

		assertEquals(2, coalescedPipe.pendingCount());
		assertEquals(0, coalescedPipe.coalescedCount());

		// Coalesce

		coalescedPipe.put("test1");

		assertEquals(2, coalescedPipe.pendingCount());
		assertEquals(1, coalescedPipe.coalescedCount());

		coalescedPipe.put("test2");

		assertEquals(2, coalescedPipe.pendingCount());
		assertEquals(2, coalescedPipe.coalescedCount());

		// With comparator

		coalescedPipe = new CoalescedPipe<String>(
			new Comparator<String>() {

				public int compare(String o1, String o2) {
					return o1.length() - o2.length();
				}

			}
		);

		// Null

		try {
			coalescedPipe.put(null);

			fail();
		}
		catch (NullPointerException npe) {
		}

		// Normal

		coalescedPipe.put("a");

		assertEquals(1, coalescedPipe.pendingCount());
		assertEquals(0, coalescedPipe.coalescedCount());

		coalescedPipe.put("ab");

		assertEquals(2, coalescedPipe.pendingCount());
		assertEquals(0, coalescedPipe.coalescedCount());

		// Coalesce

		coalescedPipe.put("c");

		assertEquals(2, coalescedPipe.pendingCount());
		assertEquals(1, coalescedPipe.coalescedCount());

		coalescedPipe.put("cd");

		assertEquals(2, coalescedPipe.pendingCount());
		assertEquals(2, coalescedPipe.coalescedCount());
	}

	public void testTakeSnapshot() throws InterruptedException {
		CoalescedPipe<String> coalescedPipe = new CoalescedPipe<String>();

		Object[] snapShot = coalescedPipe.takeSnapshot();

		assertEquals(0, snapShot.length);

		coalescedPipe.put("test1");

		snapShot = coalescedPipe.takeSnapshot();

		assertEquals(1, snapShot.length);
		assertEquals("test1", snapShot[0]);

		coalescedPipe.put("test2");

		snapShot = coalescedPipe.takeSnapshot();

		assertEquals(2, snapShot.length);
		assertEquals("test1", snapShot[0]);
		assertEquals("test2", snapShot[1]);
	}

}