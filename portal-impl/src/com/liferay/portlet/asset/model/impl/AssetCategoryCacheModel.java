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

package com.liferay.portlet.asset.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.asset.model.AssetCategory;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetCategory in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategory
 * @generated
 */
public class AssetCategoryCacheModel implements CacheModel<AssetCategory>,
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
		sb.append(", leftCategoryId=");
		sb.append(leftCategoryId);
		sb.append(", rightCategoryId=");
		sb.append(rightCategoryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", vocabularyId=");
		sb.append(vocabularyId);
		sb.append("}");

		return sb.toString();
	}

	public AssetCategory toEntityModel() {
		AssetCategoryImpl assetCategoryImpl = new AssetCategoryImpl();

		if (uuid == null) {
			assetCategoryImpl.setUuid(StringPool.BLANK);
		}
		else {
			assetCategoryImpl.setUuid(uuid);
		}

		assetCategoryImpl.setCategoryId(categoryId);
		assetCategoryImpl.setGroupId(groupId);
		assetCategoryImpl.setCompanyId(companyId);
		assetCategoryImpl.setUserId(userId);

		if (userName == null) {
			assetCategoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetCategoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetCategoryImpl.setCreateDate(null);
		}
		else {
			assetCategoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetCategoryImpl.setModifiedDate(null);
		}
		else {
			assetCategoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetCategoryImpl.setParentCategoryId(parentCategoryId);
		assetCategoryImpl.setLeftCategoryId(leftCategoryId);
		assetCategoryImpl.setRightCategoryId(rightCategoryId);

		if (name == null) {
			assetCategoryImpl.setName(StringPool.BLANK);
		}
		else {
			assetCategoryImpl.setName(name);
		}

		if (title == null) {
			assetCategoryImpl.setTitle(StringPool.BLANK);
		}
		else {
			assetCategoryImpl.setTitle(title);
		}

		if (description == null) {
			assetCategoryImpl.setDescription(StringPool.BLANK);
		}
		else {
			assetCategoryImpl.setDescription(description);
		}

		assetCategoryImpl.setVocabularyId(vocabularyId);

		assetCategoryImpl.resetOriginalValues();

		return assetCategoryImpl;
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
	public long leftCategoryId;
	public long rightCategoryId;
	public String name;
	public String title;
	public String description;
	public long vocabularyId;
}