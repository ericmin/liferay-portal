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

package com.liferay.portal.kernel.messaging.proxy;

import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;

/**
 * @author Michael C. Han
 */
public abstract class BaseMultiDestinationProxyBean {

	public abstract String getDestinationName(ProxyRequest proxyRequest);

	public MessageSender getMessageSender() {
		return _messageSender;
	}

	public SynchronousMessageSender getSynchronousMessageSender() {
		return _synchronousMessageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		_messageSender = messageSender;
	}

	public void setSynchronousMessageSender(
		SynchronousMessageSender synchronousMessageSender) {

		_synchronousMessageSender = synchronousMessageSender;
	}

	private MessageSender _messageSender;
	private SynchronousMessageSender _synchronousMessageSender;

}