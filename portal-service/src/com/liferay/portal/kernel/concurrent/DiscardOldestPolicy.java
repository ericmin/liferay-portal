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
public class DiscardOldestPolicy implements RejectedExecutionHandler {

	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		if (threadPoolExecutor.isShutdown()) {
			return;
		}

		TaskQueue<Runnable> taskQueue = threadPoolExecutor.getTaskQueue();

		taskQueue.poll();

		threadPoolExecutor.execute(runnable);
	}

}