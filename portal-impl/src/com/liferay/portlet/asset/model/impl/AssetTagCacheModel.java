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

import com.liferay.portlet.asset.model.AssetTag;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetTag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTag
 * @generated
 */
public class AssetTagCacheModel implements CacheModel<AssetTag>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{tagId=");
		sb.append(tagId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", assetCount=");
		sb.append(assetCount);
		sb.append("}");

		return sb.toString();
	}

	public AssetTag toEntityModel() {
		AssetTagImpl assetTagImpl = new AssetTagImpl();

		assetTagImpl.setTagId(tagId);
		assetTagImpl.setGroupId(groupId);
		assetTagImpl.setCompanyId(companyId);
		assetTagImpl.setUserId(userId);

		if (userName == null) {
			assetTagImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetTagImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetTagImpl.setCreateDate(null);
		}
		else {
			assetTagImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetTagImpl.setModifiedDate(null);
		}
		else {
			assetTagImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			assetTagImpl.setName(StringPool.BLANK);
		}
		else {
			assetTagImpl.setName(name);
		}

		assetTagImpl.setAssetCount(assetCount);

		assetTagImpl.resetOriginalValues();

		return assetTagImpl;
	}

	public long tagId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public int assetCount;
}