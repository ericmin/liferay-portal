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

package com.liferay.portlet.expando.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.expando.model.ExpandoColumn;

import java.io.Serializable;

/**
 * The cache model class for representing ExpandoColumn in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoColumn
 * @generated
 */
public class ExpandoColumnCacheModel implements CacheModel<ExpandoColumn>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{columnId=");
		sb.append(columnId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", tableId=");
		sb.append(tableId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", defaultData=");
		sb.append(defaultData);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append("}");

		return sb.toString();
	}

	public ExpandoColumn toEntityModel() {
		ExpandoColumnImpl expandoColumnImpl = new ExpandoColumnImpl();

		expandoColumnImpl.setColumnId(columnId);
		expandoColumnImpl.setCompanyId(companyId);
		expandoColumnImpl.setTableId(tableId);

		if (name == null) {
			expandoColumnImpl.setName(StringPool.BLANK);
		}
		else {
			expandoColumnImpl.setName(name);
		}

		expandoColumnImpl.setType(type);

		if (defaultData == null) {
			expandoColumnImpl.setDefaultData(StringPool.BLANK);
		}
		else {
			expandoColumnImpl.setDefaultData(defaultData);
		}

		if (typeSettings == null) {
			expandoColumnImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			expandoColumnImpl.setTypeSettings(typeSettings);
		}

		expandoColumnImpl.resetOriginalValues();

		return expandoColumnImpl;
	}

	public long columnId;
	public long companyId;
	public long tableId;
	public String name;
	public int type;
	public String defaultData;
	public String typeSettings;
}