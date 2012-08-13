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

package com.liferay.portal.upgrade.v5_1_5.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class ResourceTable {

	public static final String TABLE_NAME = "Resource_";

	public static final Object[][] TABLE_COLUMNS = {
		{"resourceId", Types.BIGINT},
		{"codeId", Types.BIGINT},
		{"primKey", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table Resource_ (resourceId LONG not null primary key,codeId LONG,primKey VARCHAR(255) null)";

	public static final String TABLE_SQL_DROP = "drop table Resource_";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_2578FBD3 on Resource_ (codeId)",
		"create unique index IX_67DE7856 on Resource_ (codeId, primKey)"
	};

}