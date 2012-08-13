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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.documentlibrary.model.DLFileShortcut;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DLFileShortcut in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DLFileShortcut
 * @generated
 */
public class DLFileShortcutCacheModel implements CacheModel<DLFileShortcut>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", fileShortcutId=");
		sb.append(fileShortcutId);
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
		sb.append(", repositoryId=");
		sb.append(repositoryId);
		sb.append(", folderId=");
		sb.append(folderId);
		sb.append(", toFileEntryId=");
		sb.append(toFileEntryId);
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

	public DLFileShortcut toEntityModel() {
		DLFileShortcutImpl dlFileShortcutImpl = new DLFileShortcutImpl();

		if (uuid == null) {
			dlFileShortcutImpl.setUuid(StringPool.BLANK);
		}
		else {
			dlFileShortcutImpl.setUuid(uuid);
		}

		dlFileShortcutImpl.setFileShortcutId(fileShortcutId);
		dlFileShortcutImpl.setGroupId(groupId);
		dlFileShortcutImpl.setCompanyId(companyId);
		dlFileShortcutImpl.setUserId(userId);

		if (userName == null) {
			dlFileShortcutImpl.setUserName(StringPool.BLANK);
		}
		else {
			dlFileShortcutImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			dlFileShortcutImpl.setCreateDate(null);
		}
		else {
			dlFileShortcutImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dlFileShortcutImpl.setModifiedDate(null);
		}
		else {
			dlFileShortcutImpl.setModifiedDate(new Date(modifiedDate));
		}

		dlFileShortcutImpl.setRepositoryId(repositoryId);
		dlFileShortcutImpl.setFolderId(folderId);
		dlFileShortcutImpl.setToFileEntryId(toFileEntryId);
		dlFileShortcutImpl.setStatus(status);
		dlFileShortcutImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			dlFileShortcutImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			dlFileShortcutImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			dlFileShortcutImpl.setStatusDate(null);
		}
		else {
			dlFileShortcutImpl.setStatusDate(new Date(statusDate));
		}

		dlFileShortcutImpl.resetOriginalValues();

		return dlFileShortcutImpl;
	}

	public String uuid;
	public long fileShortcutId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long repositoryId;
	public long folderId;
	public long toFileEntryId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
}