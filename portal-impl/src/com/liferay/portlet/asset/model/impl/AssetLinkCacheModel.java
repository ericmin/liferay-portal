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

import com.liferay.portlet.asset.model.AssetLink;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetLink
 * @generated
 */
public class AssetLinkCacheModel implements CacheModel<AssetLink>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{linkId=");
		sb.append(linkId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", entryId1=");
		sb.append(entryId1);
		sb.append(", entryId2=");
		sb.append(entryId2);
		sb.append(", type=");
		sb.append(type);
		sb.append(", weight=");
		sb.append(weight);
		sb.append("}");

		return sb.toString();
	}

	public AssetLink toEntityModel() {
		AssetLinkImpl assetLinkImpl = new AssetLinkImpl();

		assetLinkImpl.setLinkId(linkId);
		assetLinkImpl.setCompanyId(companyId);
		assetLinkImpl.setUserId(userId);

		if (userName == null) {
			assetLinkImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetLinkImpl.setCreateDate(null);
		}
		else {
			assetLinkImpl.setCreateDate(new Date(createDate));
		}

		assetLinkImpl.setEntryId1(entryId1);
		assetLinkImpl.setEntryId2(entryId2);
		assetLinkImpl.setType(type);
		assetLinkImpl.setWeight(weight);

		assetLinkImpl.resetOriginalValues();

		return assetLinkImpl;
	}

	public long linkId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long entryId1;
	public long entryId2;
	public int type;
	public int weight;
}