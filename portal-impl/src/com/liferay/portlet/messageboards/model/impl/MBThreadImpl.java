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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.model.Lock;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBThread;

/**
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
public class MBThreadImpl extends MBThreadBaseImpl {

	public MBThreadImpl() {
	}

	public String getAttachmentsDir() {
		return "messageboards/" + getThreadId();
	}

	public Lock getLock() {
		try {
			return LockLocalServiceUtil.getLock(
				MBThread.class.getName(), getThreadId());
		}
		catch (Exception e) {
		}

		return null;
	}

	public boolean hasLock(long userId) {
		try {
			return LockLocalServiceUtil.hasLock(
				userId, MBThread.class.getName(), getThreadId());
		}
		catch (Exception e) {
		}

		return false;
	}

	public boolean isLocked() {
		try {
			return LockLocalServiceUtil.isLocked(
				MBThread.class.getName(), getThreadId());
		}
		catch (Exception e) {
		}

		return false;
	}

}