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

package com.liferay.portlet.blogs.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

/**
 * @author Zsolt Berentey
 */
public class CheckEntryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		BlogsEntryLocalServiceUtil.checkEntries();
	}

}