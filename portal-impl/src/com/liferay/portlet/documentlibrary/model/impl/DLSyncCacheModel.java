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

import com.liferay.portlet.documentlibrary.model.DLSync;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DLSync in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DLSync
 * @generated
 */
public class DLSyncCacheModel implements CacheModel<DLSync>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{syncId=");
		sb.append(syncId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", fileUuid=");
		sb.append(fileUuid);
		sb.append(", repositoryId=");
		sb.append(repositoryId);
		sb.append(", parentFolderId=");
		sb.append(parentFolderId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", event=");
		sb.append(event);
		sb.append(", type=");
		sb.append(type);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	public DLSync toEntityModel() {
		DLSyncImpl dlSyncImpl = new DLSyncImpl();

		dlSyncImpl.setSyncId(syncId);
		dlSyncImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			dlSyncImpl.setCreateDate(null);
		}
		else {
			dlSyncImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			dlSyncImpl.setModifiedDate(null);
		}
		else {
			dlSyncImpl.setModifiedDate(new Date(modifiedDate));
		}

		dlSyncImpl.setFileId(fileId);

		if (fileUuid == null) {
			dlSyncImpl.setFileUuid(StringPool.BLANK);
		}
		else {
			dlSyncImpl.setFileUuid(fileUuid);
		}

		dlSyncImpl.setRepositoryId(repositoryId);
		dlSyncImpl.setParentFolderId(parentFolderId);

		if (name == null) {
			dlSyncImpl.setName(StringPool.BLANK);
		}
		else {
			dlSyncImpl.setName(name);
		}

		if (description == null) {
			dlSyncImpl.setDescription(StringPool.BLANK);
		}
		else {
			dlSyncImpl.setDescription(description);
		}

		if (event == null) {
			dlSyncImpl.setEvent(StringPool.BLANK);
		}
		else {
			dlSyncImpl.setEvent(event);
		}

		if (type == null) {
			dlSyncImpl.setType(StringPool.BLANK);
		}
		else {
			dlSyncImpl.setType(type);
		}

		if (version == null) {
			dlSyncImpl.setVersion(StringPool.BLANK);
		}
		else {
			dlSyncImpl.setVersion(version);
		}

		dlSyncImpl.resetOriginalValues();

		return dlSyncImpl;
	}

	public long syncId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long fileId;
	public String fileUuid;
	public long repositoryId;
	public long parentFolderId;
	public String name;
	public String description;
	public String event;
	public String type;
	public String version;
}