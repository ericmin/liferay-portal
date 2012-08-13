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
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.service.base.MBDiscussionLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class MBDiscussionLocalServiceImpl
	extends MBDiscussionLocalServiceBaseImpl {

	public MBDiscussion addDiscussion(
			long classNameId, long classPK, long threadId)
		throws SystemException {

		long discussionId = counterLocalService.increment();

		MBDiscussion discussion = mbDiscussionPersistence.create(discussionId);

		discussion.setClassNameId(classNameId);
		discussion.setClassPK(classPK);
		discussion.setThreadId(threadId);

		mbDiscussionPersistence.update(discussion, false);

		return discussion;
	}

	public MBDiscussion getDiscussion(long discussionId)
		throws PortalException, SystemException {

		return mbDiscussionPersistence.findByPrimaryKey(discussionId);
	}

	public MBDiscussion getDiscussion(String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return mbDiscussionPersistence.findByC_C(classNameId, classPK);
	}

	public MBDiscussion getThreadDiscussion(long threadId)
		throws PortalException, SystemException {

		return mbDiscussionPersistence.findByThreadId(threadId);
	}

}