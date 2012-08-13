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

package com.liferay.portal.upgrade.v5_0_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class LayoutSetTable {

	public static final String TABLE_NAME = "LayoutSet";

	public static final Object[][] TABLE_COLUMNS = {
		{"layoutSetId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"privateLayout", Types.BOOLEAN},
		{"logo", Types.BOOLEAN},
		{"logoId", Types.BIGINT},
		{"themeId", Types.VARCHAR},
		{"colorSchemeId", Types.VARCHAR},
		{"wapThemeId", Types.VARCHAR},
		{"wapColorSchemeId", Types.VARCHAR},
		{"css", Types.VARCHAR},
		{"pageCount", Types.INTEGER},
		{"virtualHost", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table LayoutSet (layoutSetId LONG not null primary key,groupId LONG,companyId LONG,privateLayout BOOLEAN,logo BOOLEAN,logoId LONG,themeId VARCHAR(75) null,colorSchemeId VARCHAR(75) null,wapThemeId VARCHAR(75) null,wapColorSchemeId VARCHAR(75) null,css STRING null,pageCount INTEGER,virtualHost VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table LayoutSet";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_A40B8BEC on LayoutSet (groupId)",
		"create index IX_48550691 on LayoutSet (groupId, privateLayout)",
		"create index IX_5ABC2905 on LayoutSet (virtualHost)"
	};

}