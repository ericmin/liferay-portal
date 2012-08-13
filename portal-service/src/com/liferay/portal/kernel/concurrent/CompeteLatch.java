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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * <p>
 * A synchronizer based on the JDK's AQS framework to simulate a single winner
 * competition. This synchronizer supports cyclical competition. In this
 * situation, loser threads should try again. The single winner thread will lock
 * the latch while other threads will block on the latch by calling
 * <code>await</code>. After the winner thread finishes its job, it should call
 * <code>done</code> which will open the latch. All blocking loser threads can
 * pass the latch at the same time.
 * </p>
 *
 * <p>
 * See LPS-3744 for a sample use case.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class CompeteLatch {

	/**
	 * This method should only be called by a loser thread. If the latch is
	 * locked, that means the winner is executing its job and all loser threads
	 * that call this method will be blocked. If the latch is not locked, that
	 * means the winner has finished its job and all the loser threads calling
	 * this method will return immediately. If the winner thread calls this
	 * method before his job completed, then all threads will deadlock.
	 *
	 * @throws InterruptedException if the current thread is interrupted
	 */
	public void await() throws InterruptedException {
		_sync.acquireSharedInterruptibly(1);
	}

	/**
	 * This method should only be called by a loser thread. If the latch is
	 * locked, that means the winner is executing its job and all loser threads
	 * that call this method will be blocked for the given waiting time. If the
	 * latch is not locked, that means the winner has finished its job and all
	 * the loser threads calling this method will return immediately. If the
	 * winner thread calls this method before his job completed, then all
	 * threads will deadlock.
	 *
	 * @return <code>true</code> if the latch was open, <code>false</code> if
	 *         the waiting time elapsed before the latch be opened.
	 * @throws InterruptedException if the current thread is interrupted
	 */
	public boolean await(long timeout, TimeUnit timeUnit)
		throws InterruptedException {

		return _sync.tryAcquireSharedNanos(1, timeUnit.toNanos(timeout));
	}

	/**
	 * Tells the current thread to join the competition. Return immediately
	 * whether or not the current thread is the winner thread or a loser thread.
	 * No matter how many threads join this competition, only one thread can be
	 * the winner thread.
	 *
	 * @return <code>true</code> if the current thread is the winner thread
	 */
	public boolean compete() {
		return _sync._tryInitAcquireShared();
	}

	/**
	 * This method should only be called by the winner thread. The winner thread
	 * calls this method to indicate that it has finished its job and unlocks
	 * the latch to allow all loser threads return from the <code>await</code>
	 * method. If a loser thread does call this method when a winner thread has
	 * locked the latch, the latch will break and the winner thread may be put
	 * into a non thread safe state. You should never have to do this except to
	 * get out of a deadlock. If no one threads have locked the latch, then
	 * calling this method has no effect. This method will return immediately.
	 *
	 * @return <code>true</code> if this call opens the latch,
	 *         <code>false</code> if the latch is already open
	 */
	public boolean done() {
		return _sync.releaseShared(1);
	}

	/**
	 * Returns <code>true</code> if the latch is locked. This method should not
	 * be used to test the latch before joining a competition because it is not
	 * thread safe. The only purpose for this method is to give external systems
	 * a way to monitor the latch which is usually be used for deadlock
	 * detection.
	 */
	public boolean isLocked() {
		return _sync._isLocked();
	}

	private Sync _sync = new Sync();

	private class Sync extends AbstractQueuedSynchronizer {

		@Override
		protected int tryAcquireShared(int arg) {
			if (getState() == 0) {
				return 1;
			}
			else {
				return -1;
			}
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			if (compareAndSetState(1, 0)) {
				return true;
			}
			else {
				return false;
			}
		}

		private boolean _isLocked() {
			if (getState() == 1) {
				return true;
			}
			else {
				return false;
			}
		}

		private boolean _tryInitAcquireShared() {
			if (compareAndSetState(0, 1)) {
				return true;
			}
			else {
				return false;
			}
		}

	}

}