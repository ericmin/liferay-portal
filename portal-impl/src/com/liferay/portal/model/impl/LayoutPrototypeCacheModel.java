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
import com.liferay.portal.model.LayoutPrototype;

import java.io.Serializable;

/**
 * The cache model class for representing LayoutPrototype in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPrototype
 * @generated
 */
public class LayoutPrototypeCacheModel implements CacheModel<LayoutPrototype>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", layoutPrototypeId=");
		sb.append(layoutPrototypeId);
		sb.append(", companyId=");
		sb.append(companyId);
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

	public LayoutPrototype toEntityModel() {
		LayoutPrototypeImpl layoutPrototypeImpl = new LayoutPrototypeImpl();

		if (uuid == null) {
			layoutPrototypeImpl.setUuid(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setUuid(uuid);
		}

		layoutPrototypeImpl.setLayoutPrototypeId(layoutPrototypeId);
		layoutPrototypeImpl.setCompanyId(companyId);

		if (name == null) {
			layoutPrototypeImpl.setName(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setName(name);
		}

		if (description == null) {
			layoutPrototypeImpl.setDescription(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setDescription(description);
		}

		if (settings == null) {
			layoutPrototypeImpl.setSettings(StringPool.BLANK);
		}
		else {
			layoutPrototypeImpl.setSettings(settings);
		}

		layoutPrototypeImpl.setActive(active);

		layoutPrototypeImpl.resetOriginalValues();

		return layoutPrototypeImpl;
	}

	public String uuid;
	public long layoutPrototypeId;
	public long companyId;
	public String name;
	public String description;
	public String settings;
	public boolean active;
}