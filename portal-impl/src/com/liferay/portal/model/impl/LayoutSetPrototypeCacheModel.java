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
import com.liferay.portal.model.LayoutSetPrototype;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing LayoutSetPrototype in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetPrototype
 * @generated
 */
public class LayoutSetPrototypeCacheModel implements CacheModel<LayoutSetPrototype>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", layoutSetPrototypeId=");
		sb.append(layoutSetPrototypeId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", settings=");
		sb.append(settings);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public LayoutSetPrototype toEntityModel() {
		LayoutSetPrototypeImpl layoutSetPrototypeImpl = new LayoutSetPrototypeImpl();

		if (uuid == null) {
			layoutSetPrototypeImpl.setUuid(StringPool.BLANK);
		}
		else {
			layoutSetPrototypeImpl.setUuid(uuid);
		}

		layoutSetPrototypeImpl.setLayoutSetPrototypeId(layoutSetPrototypeId);
		layoutSetPrototypeImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			layoutSetPrototypeImpl.setCreateDate(null);
		}
		else {
			layoutSetPrototypeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutSetPrototypeImpl.setModifiedDate(null);
		}
		else {
			layoutSetPrototypeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			layoutSetPrototypeImpl.setName(StringPool.BLANK);
		}
		else {
			layoutSetPrototypeImpl.setName(name);
		}

		if (description == null) {
			layoutSetPrototypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			layoutSetPrototypeImpl.setDescription(description);
		}

		if (settings == null) {
			layoutSetPrototypeImpl.setSettings(StringPool.BLANK);
		}
		else {
			layoutSetPrototypeImpl.setSettings(settings);
		}

		layoutSetPrototypeImpl.setActive(active);

		layoutSetPrototypeImpl.resetOriginalValues();

		return layoutSetPrototypeImpl;
	}

	public String uuid;
	public long layoutSetPrototypeId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String settings;
	public boolean active;
}