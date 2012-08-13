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

package com.liferay.portal.kernel.memory;

import com.liferay.portal.kernel.test.BaseTestCase;

/**
 * @author Shuyang Zhou
 */
public class FinalizeManagerTest extends BaseTestCase {

	public void testRegister() throws InterruptedException {
		if (FinalizeManager.THREAD_ENABLED) {
			registerWithThread();
		}
		else {
			registerWithoutThread();
		}
	}

	protected void registerWithoutThread() throws InterruptedException {
		Object testObject = new Object();

		MarkFinalizeAction markFinalizeAction = new MarkFinalizeAction();

		FinalizeManager.register(testObject, markFinalizeAction);

		assertFalse(markFinalizeAction.isMarked());

		testObject = null;

		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < 100) {
			System.gc();
			Thread.sleep(1);

			if (markFinalizeAction.isMarked()) {
				break;
			}
		}

		FinalizeManager.register(new Object(), markFinalizeAction);

		assertTrue(markFinalizeAction.isMarked());
	}

	protected void registerWithThread() throws InterruptedException {
		Object testObject = new Object();

		MarkFinalizeAction markFinalizeAction = new MarkFinalizeAction();

		FinalizeManager.register(testObject, markFinalizeAction);

		assertFalse(markFinalizeAction.isMarked());

		testObject = null;

		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < 100) {
			System.gc();

			Thread.sleep(1);

			if (markFinalizeAction.isMarked()) {
				break;
			}
		}

		assertTrue(markFinalizeAction.isMarked());
	}

	private class MarkFinalizeAction implements FinalizeAction {

		public void doFinalize() {
			_marked = true;
		}

		public boolean isMarked() {
			return _marked;
		}

		private volatile boolean _marked;

	}

}