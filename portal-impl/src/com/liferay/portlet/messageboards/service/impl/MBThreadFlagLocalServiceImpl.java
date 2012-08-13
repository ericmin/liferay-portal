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

package com.liferay.portlet.messageboards.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.MBThreadFlag;
import com.liferay.portlet.messageboards.service.base.MBThreadFlagLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class MBThreadFlagLocalServiceImpl
	extends MBThreadFlagLocalServiceBaseImpl {

	public void addThreadFlag(long userId, MBThread thread)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		if (user.isDefaultUser()) {
			return;
		}

		long threadId = thread.getThreadId();

		MBThreadFlag threadFlag = mbThreadFlagPersistence.fetchByU_T(
			userId, threadId);

		if (threadFlag == null) {
			long threadFlagId = counterLocalService.increment();

			threadFlag = mbThreadFlagPersistence.create(threadFlagId);

			threadFlag.setUserId(userId);
			threadFlag.setModifiedDate(thread.getLastPostDate());
			threadFlag.setThreadId(threadId);

			try {
				mbThreadFlagPersistence.update(threadFlag, false);
			}
			catch (SystemException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Add failed, fetch {userId=" + userId + ", threadId=" +
							threadId + "}");
				}

				threadFlag = mbThreadFlagPersistence.fetchByU_T(
					userId, threadId, false);

				if (threadFlag == null) {
					throw se;
				}
			}
		}
		else if (!DateUtil.equals(
					threadFlag.getModifiedDate(), thread.getLastPostDate(),
					true)) {

			threadFlag.setModifiedDate(thread.getLastPostDate());

			mbThreadFlagPersistence.update(threadFlag, false);
		}
	}

	public void deleteThreadFlag(long threadFlagId)
		throws PortalException, SystemException {

		MBThreadFlag threadFlag = mbThreadFlagPersistence.findByPrimaryKey(
			threadFlagId);

		deleteThreadFlag(threadFlag);
	}

	public void deleteThreadFlag(MBThreadFlag threadFlag)
		throws SystemException {

		mbThreadFlagPersistence.remove(threadFlag);
	}

	public void deleteThreadFlagsByThreadId(long threadId)
		throws SystemException {

		mbThreadFlagPersistence.removeByThreadId(threadId);
	}

	public void deleteThreadFlagsByUserId(long userId) throws SystemException {
		mbThreadFlagPersistence.removeByUserId(userId);
	}

	public MBThreadFlag getThreadFlag(long userId, MBThread thread)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		if (user.isDefaultUser()) {
			return null;
		}

		return mbThreadFlagPersistence.fetchByU_T(userId, thread.getThreadId());
	}

	public boolean hasThreadFlag(long userId, MBThread thread)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		if (user.isDefaultUser()) {
			return true;
		}

		MBThreadFlag threadFlag = mbThreadFlagPersistence.fetchByU_T(
			userId, thread.getThreadId());

		if ((threadFlag != null) &&
			(DateUtil.equals(
				threadFlag.getModifiedDate(), thread.getLastPostDate(),
				true))) {

			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MBThreadFlagLocalServiceImpl.class);

}