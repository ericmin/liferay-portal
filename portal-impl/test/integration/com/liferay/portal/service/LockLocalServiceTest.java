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

package com.liferay.portal.service;

import com.liferay.portal.dao.db.PostgreSQLDB;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Lock;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.LockAcquisitionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class LockLocalServiceTest {

	@Test
	public void testMutualExcludeLockingParallel() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		List<LockingJob> lockingJobs = new ArrayList<LockingJob>();

		for (int i = 0; i < 10; i++) {
			LockingJob lockingJob = new LockingJob(
				"className", "key", "owner", 10);

			lockingJobs.add(lockingJob);

			executorService.execute(lockingJob);
		}

		executorService.shutdown();

		Assert.assertTrue(
			executorService.awaitTermination(600, TimeUnit.SECONDS));

		for (LockingJob lockingJob : lockingJobs) {
			SystemException systemException = lockingJob.getSystemException();

			if (systemException != null) {
				Assert.fail(systemException.getMessage());
			}
		}
	}

	@Test
	public void testMutualExcludeLockingSerial() throws Exception {
		String className = "testClassName";
		String key = "testKey";
		String owner1 = "testOwner1";

		Lock lock1 = LockLocalServiceUtil.lock(className, key, owner1, false);

		Assert.assertEquals(owner1, lock1.getOwner());
		Assert.assertTrue(lock1.isNew());

		String owner2 = "owner2";

		Lock lock2 = LockLocalServiceUtil.lock(className, key, owner2, false);

		Assert.assertEquals(owner1, lock2.getOwner());
		Assert.assertFalse(lock2.isNew());

		LockLocalServiceUtil.unlock(className, key, owner1, false);

		lock2 = LockLocalServiceUtil.lock(className, key, owner2, false);

		Assert.assertEquals(owner2, lock2.getOwner());
		Assert.assertTrue(lock2.isNew());

		LockLocalServiceUtil.unlock(className, key, owner2, false);
	}

	private class LockingJob implements Runnable {

		public LockingJob(
			String className, String key, String owner,
			int requiredSuccessCount) {

			_className = className;
			_key = key;
			_owner = owner;
			_requiredSuccessCount = requiredSuccessCount;
		}

		public SystemException getSystemException() {
			return _systemException;
		}

		public void run() {
			int count = 0;

			while (true) {
				try {
					Lock lock = LockLocalServiceUtil.lock(
						_className, _key, _owner, false);

					if (lock.isNew()) {
						LockLocalServiceUtil.unlock(_className, _key);

						if (++count >= _requiredSuccessCount) {
							break;
						}
					}
				}
				catch (SystemException se) {
					Throwable cause = se.getCause();

					if (cause instanceof ORMException) {
						cause = cause.getCause();

						if (cause instanceof LockAcquisitionException) {
							continue;
						}

						// PostgreSQL fails to do row or table level locking.
						// A unique index is required to enforce mutual exclude
						// locking, but it may do so by violating a unique index
						// constraint.

						DB db = DBFactoryUtil.getDB();

						if ((db instanceof PostgreSQLDB) &&
							(cause instanceof ConstraintViolationException)) {

							continue;
						}
					}

					_systemException = se;

					break;
				}
			}
		}

		private String _className;
		private String _key;
		private String _owner;
		private int _requiredSuccessCount;
		private SystemException _systemException;

	}

}