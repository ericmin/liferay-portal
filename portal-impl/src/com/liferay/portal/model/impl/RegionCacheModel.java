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
import com.liferay.portal.model.Region;

import java.io.Serializable;

/**
 * The cache model class for representing Region in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Region
 * @generated
 */
public class RegionCacheModel implements CacheModel<Region>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{regionId=");
		sb.append(regionId);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", regionCode=");
		sb.append(regionCode);
		sb.append(", name=");
		sb.append(name);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public Region toEntityModel() {
		RegionImpl regionImpl = new RegionImpl();

		regionImpl.setRegionId(regionId);
		regionImpl.setCountryId(countryId);

		if (regionCode == null) {
			regionImpl.setRegionCode(StringPool.BLANK);
		}
		else {
			regionImpl.setRegionCode(regionCode);
		}

		if (name == null) {
			regionImpl.setName(StringPool.BLANK);
		}
		else {
			regionImpl.setName(name);
		}

		regionImpl.setActive(active);

		regionImpl.resetOriginalValues();

		return regionImpl;
	}

	public long regionId;
	public long countryId;
	public String regionCode;
	public String name;
	public boolean active;
}