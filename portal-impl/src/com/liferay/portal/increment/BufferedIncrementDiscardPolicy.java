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

package com.liferay.portal.increment;

import com.liferay.portal.kernel.concurrent.BatchablePipe;
import com.liferay.portal.kernel.concurrent.RejectedExecutionHandler;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageRunnable;

/**
 * @author Shuyang Zhou
 */
public class BufferedIncrementDiscardPolicy
	implements RejectedExecutionHandler {

	@SuppressWarnings("rawtypes")
	public void rejectedExecution(
		Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

		MessageRunnable messageRunnable = (MessageRunnable)runnable;

		Message message = messageRunnable.getMessage();

		BatchablePipe<String, BufferedIncreasableEntry> batchablePipe =
			(BatchablePipe<String, BufferedIncreasableEntry>)
				message.getPayload();

		for (int i = 0; i < _discardNumber; i++) {
			BufferedIncreasableEntry bufferedIncreasableEntry =
				(BufferedIncreasableEntry)batchablePipe.take();

			if (bufferedIncreasableEntry == null) {
				break;
			}
			else if (_log.isInfoEnabled()) {
				_log.info(
					"Discarding BufferedIncreasableEntry " +
						bufferedIncreasableEntry);
			}
		}
	}

	public void setDiscardNumber(int discardNumber) {
		_discardNumber = discardNumber;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BufferedIncrementDiscardPolicy.class);

	private int _discardNumber = 1;

}