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

import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationSynchronousMessageSender;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseProxyBean {

	public final SingleDestinationMessageSender
		getSingleDestinationMessageSender() {

		return _singleDestinationMessageSender;
	}

	public final SingleDestinationSynchronousMessageSender
		getSingleDestinationSynchronousMessageSender() {

		return _singleDestinationSynchronousMessageSender;
	}

	public final void setSingleDestinationMessageSender(
		SingleDestinationMessageSender singleDestinationMessageSender) {

		_singleDestinationMessageSender = singleDestinationMessageSender;
	}

	public final void setSingleDestinationSynchronousMessageSender(
		SingleDestinationSynchronousMessageSender
		singleDestinationSynchronousMessageSender) {

		_singleDestinationSynchronousMessageSender =
			singleDestinationSynchronousMessageSender;
	}

	private SingleDestinationMessageSender _singleDestinationMessageSender;
	private SingleDestinationSynchronousMessageSender
		_singleDestinationSynchronousMessageSender;

}