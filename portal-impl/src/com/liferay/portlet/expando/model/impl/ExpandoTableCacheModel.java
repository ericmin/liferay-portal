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

import com.liferay.portlet.expando.model.ExpandoTable;

import java.io.Serializable;

/**
 * The cache model class for representing ExpandoTable in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoTable
 * @generated
 */
public class ExpandoTableCacheModel implements CacheModel<ExpandoTable>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{tableId=");
		sb.append(tableId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public ExpandoTable toEntityModel() {
		ExpandoTableImpl expandoTableImpl = new ExpandoTableImpl();

		expandoTableImpl.setTableId(tableId);
		expandoTableImpl.setCompanyId(companyId);
		expandoTableImpl.setClassNameId(classNameId);

		if (name == null) {
			expandoTableImpl.setName(StringPool.BLANK);
		}
		else {
			expandoTableImpl.setName(name);
		}

		expandoTableImpl.resetOriginalValues();

		return expandoTableImpl;
	}

	public long tableId;
	public long companyId;
	public long classNameId;
	public String name;
}