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

/**
 * @author Shuyang Zhou
 */
public class CallerRunsPolicy implements RejectedExecutionHandler {

	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		if (threadPoolExecutor.isShutdown()) {
			return;
		}

		ThreadPoolHandler threadPoolHandler =
			threadPoolExecutor.getThreadPoolHandler();

		Throwable throwable = null;

		threadPoolHandler.beforeExecute(Thread.currentThread(), runnable);

		try {
			runnable.run();
		}
		catch (RuntimeException re) {
			throwable = re;

			throw re;
		}
		finally {
			threadPoolHandler.afterExecute(runnable, throwable);
		}
	}

}