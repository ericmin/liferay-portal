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
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.asset.model.AssetTagStats;

import java.io.Serializable;

/**
 * The cache model class for representing AssetTagStats in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagStats
 * @generated
 */
public class AssetTagStatsCacheModel implements CacheModel<AssetTagStats>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{tagStatsId=");
		sb.append(tagStatsId);
		sb.append(", tagId=");
		sb.append(tagId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", assetCount=");
		sb.append(assetCount);
		sb.append("}");

		return sb.toString();
	}

	public AssetTagStats toEntityModel() {
		AssetTagStatsImpl assetTagStatsImpl = new AssetTagStatsImpl();

		assetTagStatsImpl.setTagStatsId(tagStatsId);
		assetTagStatsImpl.setTagId(tagId);
		assetTagStatsImpl.setClassNameId(classNameId);
		assetTagStatsImpl.setAssetCount(assetCount);

		assetTagStatsImpl.resetOriginalValues();

		return assetTagStatsImpl;
	}

	public long tagStatsId;
	public long tagId;
	public long classNameId;
	public int assetCount;
}