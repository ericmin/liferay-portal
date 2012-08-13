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

package com.liferay.portal.upgrade.v5_2_3.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class BookmarksEntryTable {

	public static final String TABLE_NAME = "BookmarksEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"entryId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"folderId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"url", Types.VARCHAR},
		{"comments", Types.VARCHAR},
		{"visits", Types.INTEGER},
		{"priority", Types.INTEGER}
	};

	public static final String TABLE_SQL_CREATE = "create table BookmarksEntry (uuid_ VARCHAR(75) null,entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,folderId LONG,name VARCHAR(255) null,url STRING null,comments STRING null,visits INTEGER,priority INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table BookmarksEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_443BDC38 on BookmarksEntry (folderId)",
		"create index IX_E52FF7EF on BookmarksEntry (groupId)",
		"create index IX_E2E9F129 on BookmarksEntry (groupId, userId)",
		"create index IX_B670BA39 on BookmarksEntry (uuid_)",
		"create unique index IX_EAA02A91 on BookmarksEntry (uuid_, groupId)"
	};

}