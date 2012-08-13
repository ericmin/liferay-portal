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

package com.liferay.portal.convert.util;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.upgrade.util.Table;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 */
public class PermissionView extends Table {

	public static String getActionId(String[] values) {
		return values[values.length - 4];
	}

	public static long getCompanyId(String[] values) {
		return Long.parseLong(values[values.length - 5]);
	}

	public static String getNameId(String[] values) {
		return values[values.length - 2];
	}

	public static long getPermissionId(String[] values) {
		return Long.parseLong(values[values.length - 6]);
	}

	public static long getPrimaryKey(String[] values) {
		return Long.parseLong(values[0]);
	}

	public static long getResourceId(String[] values) {
		return Long.parseLong(values[values.length - 3]);
	}

	public static int getScopeId(String[] values) {
		return Integer.parseInt(values[values.length - 1]);
	}

	public PermissionView(String tableName, String[] primKeys) {
		super(tableName);

		List<Object[]> columns = new ArrayList<Object[]>();

		for (String primKey : primKeys) {
			columns.add(new Object[] {primKey, Types.BIGINT});
		}

		columns.add(new Object[] {"permissionId", Types.BIGINT});
		columns.add(new Object[] {"companyId", Types.BIGINT});
		columns.add(new Object[] {"actionId", Types.VARCHAR});
		columns.add(new Object[] {"resourceId", Types.BIGINT});
		columns.add(new Object[] {"name", Types.VARCHAR});
		columns.add(new Object[] {"scope", Types.INTEGER});

		setColumns(columns.toArray(new Object[0][]));
	}

	@Override
	public String getSelectSQL() throws Exception {
		return StringUtil.replace(_SELECT_SQL, "_OLD_TABLE_", getTableName());
	}

	private static final String _SELECT_SQL =
		"SELECT _OLD_TABLE_.*, Permission_.companyId, Permission_.actionId, " +
		"Resource_.resourceId, ResourceCode.name, ResourceCode.scope FROM " +
		"_OLD_TABLE_, Permission_, Resource_, ResourceCode WHERE " +
		"_OLD_TABLE_.permissionId = Permission_.permissionId AND " +
		"Permission_.resourceId = Resource_.resourceId AND " +
		"Resource_.codeId = ResourceCode.codeId ORDER BY " +
		"Resource_.resourceId";

}