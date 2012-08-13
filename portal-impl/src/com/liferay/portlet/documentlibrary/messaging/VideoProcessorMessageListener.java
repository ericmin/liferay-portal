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

package com.liferay.portlet.documentlibrary.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.util.VideoProcessorUtil;

/**
 * @author Juan González
 * @author Sergio González
 */
public class VideoProcessorMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		FileVersion fileVersion = (FileVersion)message.getPayload();

		try {
			VideoProcessorUtil.generateVideo(fileVersion);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to generate video for file version " +
						fileVersion.getFileVersionId(),
					e);
			}
		}

		if (PropsValues.DL_FILE_ENTRY_PROCESSORS_TRIGGER_SYNCHRONOUSLY) {
			MessageBusUtil.sendMessage(
				message.getResponseDestinationName(), message);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		VideoProcessorMessageListener.class);

}