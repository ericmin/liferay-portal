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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.messageboards.model.MBBan;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MBBan in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBBan
 * @generated
 */
public class MBBanCacheModel implements CacheModel<MBBan>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{banId=");
		sb.append(banId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", banUserId=");
		sb.append(banUserId);
		sb.append("}");

		return sb.toString();
	}

	public MBBan toEntityModel() {
		MBBanImpl mbBanImpl = new MBBanImpl();

		mbBanImpl.setBanId(banId);
		mbBanImpl.setGroupId(groupId);
		mbBanImpl.setCompanyId(companyId);
		mbBanImpl.setUserId(userId);

		if (userName == null) {
			mbBanImpl.setUserName(StringPool.BLANK);
		}
		else {
			mbBanImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mbBanImpl.setCreateDate(null);
		}
		else {
			mbBanImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mbBanImpl.setModifiedDate(null);
		}
		else {
			mbBanImpl.setModifiedDate(new Date(modifiedDate));
		}

		mbBanImpl.setBanUserId(banUserId);

		mbBanImpl.resetOriginalValues();

		return mbBanImpl;
	}

	public long banId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long banUserId;
}