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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Repository;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Repository in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Repository
 * @generated
 */
public class RepositoryCacheModel implements CacheModel<Repository>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", repositoryId=");
		sb.append(repositoryId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", portletId=");
		sb.append(portletId);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append(", dlFolderId=");
		sb.append(dlFolderId);
		sb.append("}");

		return sb.toString();
	}

	public Repository toEntityModel() {
		RepositoryImpl repositoryImpl = new RepositoryImpl();

		if (uuid == null) {
			repositoryImpl.setUuid(StringPool.BLANK);
		}
		else {
			repositoryImpl.setUuid(uuid);
		}

		repositoryImpl.setRepositoryId(repositoryId);
		repositoryImpl.setGroupId(groupId);
		repositoryImpl.setCompanyId(companyId);
		repositoryImpl.setUserId(userId);

		if (userName == null) {
			repositoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			repositoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			repositoryImpl.setCreateDate(null);
		}
		else {
			repositoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			repositoryImpl.setModifiedDate(null);
		}
		else {
			repositoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		repositoryImpl.setClassNameId(classNameId);

		if (name == null) {
			repositoryImpl.setName(StringPool.BLANK);
		}
		else {
			repositoryImpl.setName(name);
		}

		if (description == null) {
			repositoryImpl.setDescription(StringPool.BLANK);
		}
		else {
			repositoryImpl.setDescription(description);
		}

		if (portletId == null) {
			repositoryImpl.setPortletId(StringPool.BLANK);
		}
		else {
			repositoryImpl.setPortletId(portletId);
		}

		if (typeSettings == null) {
			repositoryImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			repositoryImpl.setTypeSettings(typeSettings);
		}

		repositoryImpl.setDlFolderId(dlFolderId);

		repositoryImpl.resetOriginalValues();

		return repositoryImpl;
	}

	public String uuid;
	public long repositoryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public String name;
	public String description;
	public String portletId;
	public String typeSettings;
	public long dlFolderId;
}