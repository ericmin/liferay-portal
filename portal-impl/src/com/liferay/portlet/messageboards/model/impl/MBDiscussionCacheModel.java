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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.messageboards.model.MBDiscussion;

import java.io.Serializable;

/**
 * The cache model class for representing MBDiscussion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussion
 * @generated
 */
public class MBDiscussionCacheModel implements CacheModel<MBDiscussion>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{discussionId=");
		sb.append(discussionId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", threadId=");
		sb.append(threadId);
		sb.append("}");

		return sb.toString();
	}

	public MBDiscussion toEntityModel() {
		MBDiscussionImpl mbDiscussionImpl = new MBDiscussionImpl();

		mbDiscussionImpl.setDiscussionId(discussionId);
		mbDiscussionImpl.setClassNameId(classNameId);
		mbDiscussionImpl.setClassPK(classPK);
		mbDiscussionImpl.setThreadId(threadId);

		mbDiscussionImpl.resetOriginalValues();

		return mbDiscussionImpl;
	}

	public long discussionId;
	public long classNameId;
	public long classPK;
	public long threadId;
}