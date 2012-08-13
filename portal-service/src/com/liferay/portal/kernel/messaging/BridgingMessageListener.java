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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

/**
 * @author Michael C. Han
 */
public class BridgingMessageListener implements MessageListener {

	public BridgingMessageListener() {
	}

	/**
	 * @deprecated
	 */
	public BridgingMessageListener(
		SingleDestinationMessageSender singleDestinationMessageSender) {

		_singleDestinationMessageSender = singleDestinationMessageSender;
	}

	public void receive(Message message) {
		_singleDestinationMessageSender.send(message);
	}

	public void setSingleDestinationMessageSender(
		SingleDestinationMessageSender singleDestinationMessageSender) {

		_singleDestinationMessageSender = singleDestinationMessageSender;
	}

	private SingleDestinationMessageSender _singleDestinationMessageSender;

}