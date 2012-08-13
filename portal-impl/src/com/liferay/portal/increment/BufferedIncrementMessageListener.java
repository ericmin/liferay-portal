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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.security.auth.CompanyThreadLocal;

/**
 * @author Shuyang Zhou
 */
public class BufferedIncrementMessageListener extends BaseMessageListener {

	@Override
	@SuppressWarnings("rawtypes")
	protected void doReceive(Message message) throws Exception {
		long companyId = message.getLong("companyId");

		CompanyThreadLocal.setCompanyId(companyId);

		BatchablePipe<String, BufferedIncreasableEntry> batchablePipe =
			(BatchablePipe<String, BufferedIncreasableEntry>)
				message.getPayload();

		while (true) {
			BufferedIncreasableEntry bufferedIncreasableEntry =
				(BufferedIncreasableEntry)batchablePipe.take();

			if (bufferedIncreasableEntry == null) {
				break;
			}

			try {
				bufferedIncreasableEntry.proceed();
			}
			catch (Throwable t) {
				_log.error(
					"Cannot write buffered increment value to the database", t);
			}
		}

	}

	private static Log _log = LogFactoryUtil.getLog(
		BufferedIncrementMessageListener.class);

}