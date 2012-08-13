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
public class IGFolderTable {

	public static final String TABLE_NAME = "IGFolder";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"folderId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"parentFolderId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"description", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table IGFolder (uuid_ VARCHAR(75) null,folderId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,parentFolderId LONG,name VARCHAR(75) null,description STRING null)";

	public static final String TABLE_SQL_DROP = "drop table IGFolder";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_60214CF6 on IGFolder (companyId)",
		"create index IX_206498F8 on IGFolder (groupId)",
		"create index IX_1A605E9F on IGFolder (groupId, parentFolderId)",
		"create index IX_9BBAFB1E on IGFolder (groupId, parentFolderId, name)",
		"create index IX_F73C0982 on IGFolder (uuid_)",
		"create index IX_B10EFD68 on IGFolder (uuid_, groupId)"
	};

}