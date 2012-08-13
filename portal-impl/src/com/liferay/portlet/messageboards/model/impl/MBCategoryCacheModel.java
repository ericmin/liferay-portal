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

import com.liferay.portlet.messageboards.model.MBCategory;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MBCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBCategory
 * @generated
 */
public class MBCategoryCacheModel implements CacheModel<MBCategory>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", categoryId=");
		sb.append(categoryId);
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
		sb.append(", parentCategoryId=");
		sb.append(parentCategoryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", displayStyle=");
		sb.append(displayStyle);
		sb.append(", threadCount=");
		sb.append(threadCount);
		sb.append(", messageCount=");
		sb.append(messageCount);
		sb.append(", lastPostDate=");
		sb.append(lastPostDate);
		sb.append("}");

		return sb.toString();
	}

	public MBCategory toEntityModel() {
		MBCategoryImpl mbCategoryImpl = new MBCategoryImpl();

		if (uuid == null) {
			mbCategoryImpl.setUuid(StringPool.BLANK);
		}
		else {
			mbCategoryImpl.setUuid(uuid);
		}

		mbCategoryImpl.setCategoryId(categoryId);
		mbCategoryImpl.setGroupId(groupId);
		mbCategoryImpl.setCompanyId(companyId);
		mbCategoryImpl.setUserId(userId);

		if (userName == null) {
			mbCategoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			mbCategoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mbCategoryImpl.setCreateDate(null);
		}
		else {
			mbCategoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mbCategoryImpl.setModifiedDate(null);
		}
		else {
			mbCategoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		mbCategoryImpl.setParentCategoryId(parentCategoryId);

		if (name == null) {
			mbCategoryImpl.setName(StringPool.BLANK);
		}
		else {
			mbCategoryImpl.setName(name);
		}

		if (description == null) {
			mbCategoryImpl.setDescription(StringPool.BLANK);
		}
		else {
			mbCategoryImpl.setDescription(description);
		}

		if (displayStyle == null) {
			mbCategoryImpl.setDisplayStyle(StringPool.BLANK);
		}
		else {
			mbCategoryImpl.setDisplayStyle(displayStyle);
		}

		mbCategoryImpl.setThreadCount(threadCount);
		mbCategoryImpl.setMessageCount(messageCount);

		if (lastPostDate == Long.MIN_VALUE) {
			mbCategoryImpl.setLastPostDate(null);
		}
		else {
			mbCategoryImpl.setLastPostDate(new Date(lastPostDate));
		}

		mbCategoryImpl.resetOriginalValues();

		return mbCategoryImpl;
	}

	public String uuid;
	public long categoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long parentCategoryId;
	public String name;
	public String description;
	public String displayStyle;
	public int threadCount;
	public int messageCount;
	public long lastPostDate;
}