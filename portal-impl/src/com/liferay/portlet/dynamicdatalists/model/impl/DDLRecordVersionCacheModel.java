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

package com.liferay.portlet.dynamicdatalists.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DDLRecordVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordVersion
 * @generated
 */
public class DDLRecordVersionCacheModel implements CacheModel<DDLRecordVersion>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{recordVersionId=");
		sb.append(recordVersionId);
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
		sb.append(", DDMStorageId=");
		sb.append(DDMStorageId);
		sb.append(", recordSetId=");
		sb.append(recordSetId);
		sb.append(", recordId=");
		sb.append(recordId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", displayIndex=");
		sb.append(displayIndex);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	public DDLRecordVersion toEntityModel() {
		DDLRecordVersionImpl ddlRecordVersionImpl = new DDLRecordVersionImpl();

		ddlRecordVersionImpl.setRecordVersionId(recordVersionId);
		ddlRecordVersionImpl.setGroupId(groupId);
		ddlRecordVersionImpl.setCompanyId(companyId);
		ddlRecordVersionImpl.setUserId(userId);

		if (userName == null) {
			ddlRecordVersionImpl.setUserName(StringPool.BLANK);
		}
		else {
			ddlRecordVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddlRecordVersionImpl.setCreateDate(null);
		}
		else {
			ddlRecordVersionImpl.setCreateDate(new Date(createDate));
		}

		ddlRecordVersionImpl.setDDMStorageId(DDMStorageId);
		ddlRecordVersionImpl.setRecordSetId(recordSetId);
		ddlRecordVersionImpl.setRecordId(recordId);

		if (version == null) {
			ddlRecordVersionImpl.setVersion(StringPool.BLANK);
		}
		else {
			ddlRecordVersionImpl.setVersion(version);
		}

		ddlRecordVersionImpl.setDisplayIndex(displayIndex);
		ddlRecordVersionImpl.setStatus(status);
		ddlRecordVersionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			ddlRecordVersionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			ddlRecordVersionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			ddlRecordVersionImpl.setStatusDate(null);
		}
		else {
			ddlRecordVersionImpl.setStatusDate(new Date(statusDate));
		}

		ddlRecordVersionImpl.resetOriginalValues();

		return ddlRecordVersionImpl;
	}

	public long recordVersionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long DDMStorageId;
	public long recordSetId;
	public long recordId;
	public String version;
	public int displayIndex;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}