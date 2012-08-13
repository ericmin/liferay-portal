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

import com.liferay.portlet.asset.model.AssetCategoryProperty;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetCategoryProperty in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryProperty
 * @generated
 */
public class AssetCategoryPropertyCacheModel implements CacheModel<AssetCategoryProperty>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{categoryPropertyId=");
		sb.append(categoryPropertyId);
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
		sb.append(", categoryId=");
		sb.append(categoryId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	public AssetCategoryProperty toEntityModel() {
		AssetCategoryPropertyImpl assetCategoryPropertyImpl = new AssetCategoryPropertyImpl();

		assetCategoryPropertyImpl.setCategoryPropertyId(categoryPropertyId);
		assetCategoryPropertyImpl.setCompanyId(companyId);
		assetCategoryPropertyImpl.setUserId(userId);

		if (userName == null) {
			assetCategoryPropertyImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetCategoryPropertyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetCategoryPropertyImpl.setCreateDate(null);
		}
		else {
			assetCategoryPropertyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetCategoryPropertyImpl.setModifiedDate(null);
		}
		else {
			assetCategoryPropertyImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetCategoryPropertyImpl.setCategoryId(categoryId);

		if (key == null) {
			assetCategoryPropertyImpl.setKey(StringPool.BLANK);
		}
		else {
			assetCategoryPropertyImpl.setKey(key);
		}

		if (value == null) {
			assetCategoryPropertyImpl.setValue(StringPool.BLANK);
		}
		else {
			assetCategoryPropertyImpl.setValue(value);
		}

		assetCategoryPropertyImpl.resetOriginalValues();

		return assetCategoryPropertyImpl;
	}

	public long categoryPropertyId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long categoryId;
	public String key;
	public String value;
}