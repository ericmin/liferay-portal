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
import com.liferay.portal.model.LayoutSetBranch;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing LayoutSetBranch in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranch
 * @generated
 */
public class LayoutSetBranchCacheModel implements CacheModel<LayoutSetBranch>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(43);

		sb.append("{layoutSetBranchId=");
		sb.append(layoutSetBranchId);
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
		sb.append(", privateLayout=");
		sb.append(privateLayout);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", master=");
		sb.append(master);
		sb.append(", logo=");
		sb.append(logo);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", themeId=");
		sb.append(themeId);
		sb.append(", colorSchemeId=");
		sb.append(colorSchemeId);
		sb.append(", wapThemeId=");
		sb.append(wapThemeId);
		sb.append(", wapColorSchemeId=");
		sb.append(wapColorSchemeId);
		sb.append(", css=");
		sb.append(css);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);
		sb.append(", layoutSetPrototypeLinkEnabled=");
		sb.append(layoutSetPrototypeLinkEnabled);
		sb.append("}");

		return sb.toString();
	}

	public LayoutSetBranch toEntityModel() {
		LayoutSetBranchImpl layoutSetBranchImpl = new LayoutSetBranchImpl();

		layoutSetBranchImpl.setLayoutSetBranchId(layoutSetBranchId);
		layoutSetBranchImpl.setGroupId(groupId);
		layoutSetBranchImpl.setCompanyId(companyId);
		layoutSetBranchImpl.setUserId(userId);

		if (userName == null) {
			layoutSetBranchImpl.setUserName(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			layoutSetBranchImpl.setCreateDate(null);
		}
		else {
			layoutSetBranchImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutSetBranchImpl.setModifiedDate(null);
		}
		else {
			layoutSetBranchImpl.setModifiedDate(new Date(modifiedDate));
		}

		layoutSetBranchImpl.setPrivateLayout(privateLayout);

		if (name == null) {
			layoutSetBranchImpl.setName(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setName(name);
		}

		if (description == null) {
			layoutSetBranchImpl.setDescription(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setDescription(description);
		}

		layoutSetBranchImpl.setMaster(master);
		layoutSetBranchImpl.setLogo(logo);
		layoutSetBranchImpl.setLogoId(logoId);

		if (themeId == null) {
			layoutSetBranchImpl.setThemeId(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setThemeId(themeId);
		}

		if (colorSchemeId == null) {
			layoutSetBranchImpl.setColorSchemeId(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setColorSchemeId(colorSchemeId);
		}

		if (wapThemeId == null) {
			layoutSetBranchImpl.setWapThemeId(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setWapThemeId(wapThemeId);
		}

		if (wapColorSchemeId == null) {
			layoutSetBranchImpl.setWapColorSchemeId(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setWapColorSchemeId(wapColorSchemeId);
		}

		if (css == null) {
			layoutSetBranchImpl.setCss(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setCss(css);
		}

		if (settings == null) {
			layoutSetBranchImpl.setSettings(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setSettings(settings);
		}

		if (layoutSetPrototypeUuid == null) {
			layoutSetBranchImpl.setLayoutSetPrototypeUuid(StringPool.BLANK);
		}
		else {
			layoutSetBranchImpl.setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
		}

		layoutSetBranchImpl.setLayoutSetPrototypeLinkEnabled(layoutSetPrototypeLinkEnabled);

		layoutSetBranchImpl.resetOriginalValues();

		return layoutSetBranchImpl;
	}

	public long layoutSetBranchId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public boolean privateLayout;
	public String name;
	public String description;
	public boolean master;
	public boolean logo;
	public long logoId;
	public String themeId;
	public String colorSchemeId;
	public String wapThemeId;
	public String wapColorSchemeId;
	public String css;
	public String settings;
	public String layoutSetPrototypeUuid;
	public boolean layoutSetPrototypeLinkEnabled;
}