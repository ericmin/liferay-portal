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
import com.liferay.portal.model.LayoutSet;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing LayoutSet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSet
 * @generated
 */
public class LayoutSetCacheModel implements CacheModel<LayoutSet>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{layoutSetId=");
		sb.append(layoutSetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", privateLayout=");
		sb.append(privateLayout);
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
		sb.append(", pageCount=");
		sb.append(pageCount);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);
		sb.append(", layoutSetPrototypeLinkEnabled=");
		sb.append(layoutSetPrototypeLinkEnabled);
		sb.append("}");

		return sb.toString();
	}

	public LayoutSet toEntityModel() {
		LayoutSetImpl layoutSetImpl = new LayoutSetImpl();

		layoutSetImpl.setLayoutSetId(layoutSetId);
		layoutSetImpl.setGroupId(groupId);
		layoutSetImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			layoutSetImpl.setCreateDate(null);
		}
		else {
			layoutSetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutSetImpl.setModifiedDate(null);
		}
		else {
			layoutSetImpl.setModifiedDate(new Date(modifiedDate));
		}

		layoutSetImpl.setPrivateLayout(privateLayout);
		layoutSetImpl.setLogo(logo);
		layoutSetImpl.setLogoId(logoId);

		if (themeId == null) {
			layoutSetImpl.setThemeId(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setThemeId(themeId);
		}

		if (colorSchemeId == null) {
			layoutSetImpl.setColorSchemeId(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setColorSchemeId(colorSchemeId);
		}

		if (wapThemeId == null) {
			layoutSetImpl.setWapThemeId(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setWapThemeId(wapThemeId);
		}

		if (wapColorSchemeId == null) {
			layoutSetImpl.setWapColorSchemeId(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setWapColorSchemeId(wapColorSchemeId);
		}

		if (css == null) {
			layoutSetImpl.setCss(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setCss(css);
		}

		layoutSetImpl.setPageCount(pageCount);

		if (settings == null) {
			layoutSetImpl.setSettings(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setSettings(settings);
		}

		if (layoutSetPrototypeUuid == null) {
			layoutSetImpl.setLayoutSetPrototypeUuid(StringPool.BLANK);
		}
		else {
			layoutSetImpl.setLayoutSetPrototypeUuid(layoutSetPrototypeUuid);
		}

		layoutSetImpl.setLayoutSetPrototypeLinkEnabled(layoutSetPrototypeLinkEnabled);

		layoutSetImpl.resetOriginalValues();

		return layoutSetImpl;
	}

	public long layoutSetId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public boolean privateLayout;
	public boolean logo;
	public long logoId;
	public String themeId;
	public String colorSchemeId;
	public String wapThemeId;
	public String wapColorSchemeId;
	public String css;
	public int pageCount;
	public String settings;
	public String layoutSetPrototypeUuid;
	public boolean layoutSetPrototypeLinkEnabled;
}