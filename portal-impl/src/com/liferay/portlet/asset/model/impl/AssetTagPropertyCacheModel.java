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

import com.liferay.portlet.asset.model.AssetTagProperty;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetTagProperty in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagProperty
 * @generated
 */
public class AssetTagPropertyCacheModel implements CacheModel<AssetTagProperty>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{tagPropertyId=");
		sb.append(tagPropertyId);
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
		sb.append(", tagId=");
		sb.append(tagId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	public AssetTagProperty toEntityModel() {
		AssetTagPropertyImpl assetTagPropertyImpl = new AssetTagPropertyImpl();

		assetTagPropertyImpl.setTagPropertyId(tagPropertyId);
		assetTagPropertyImpl.setCompanyId(companyId);
		assetTagPropertyImpl.setUserId(userId);

		if (userName == null) {
			assetTagPropertyImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetTagPropertyImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetTagPropertyImpl.setCreateDate(null);
		}
		else {
			assetTagPropertyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetTagPropertyImpl.setModifiedDate(null);
		}
		else {
			assetTagPropertyImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetTagPropertyImpl.setTagId(tagId);

		if (key == null) {
			assetTagPropertyImpl.setKey(StringPool.BLANK);
		}
		else {
			assetTagPropertyImpl.setKey(key);
		}

		if (value == null) {
			assetTagPropertyImpl.setValue(StringPool.BLANK);
		}
		else {
			assetTagPropertyImpl.setValue(value);
		}

		assetTagPropertyImpl.resetOriginalValues();

		return assetTagPropertyImpl;
	}

	public long tagPropertyId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long tagId;
	public String key;
	public String value;
}