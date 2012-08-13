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

import com.liferay.portlet.messageboards.model.MBThreadFlag;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MBThreadFlag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBThreadFlag
 * @generated
 */
public class MBThreadFlagCacheModel implements CacheModel<MBThreadFlag>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{threadFlagId=");
		sb.append(threadFlagId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", threadId=");
		sb.append(threadId);
		sb.append("}");

		return sb.toString();
	}

	public MBThreadFlag toEntityModel() {
		MBThreadFlagImpl mbThreadFlagImpl = new MBThreadFlagImpl();

		mbThreadFlagImpl.setThreadFlagId(threadFlagId);
		mbThreadFlagImpl.setUserId(userId);

		if (modifiedDate == Long.MIN_VALUE) {
			mbThreadFlagImpl.setModifiedDate(null);
		}
		else {
			mbThreadFlagImpl.setModifiedDate(new Date(modifiedDate));
		}

		mbThreadFlagImpl.setThreadId(threadId);

		mbThreadFlagImpl.resetOriginalValues();

		return mbThreadFlagImpl;
	}

	public long threadFlagId;
	public long userId;
	public long modifiedDate;
	public long threadId;
}