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

package com.liferay.portal.upgrade.v6_0_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class DLFileShortcutTable {

	public static final String TABLE_NAME = "DLFileShortcut";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"fileShortcutId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"folderId", Types.BIGINT},
		{"toFolderId", Types.BIGINT},
		{"toName", Types.VARCHAR},
		{"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final String TABLE_SQL_CREATE = "create table DLFileShortcut (uuid_ VARCHAR(75) null,fileShortcutId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,folderId LONG,toFolderId LONG,toName VARCHAR(255) null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table DLFileShortcut";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_B0051937 on DLFileShortcut (groupId, folderId)",
		"create index IX_ECCE311D on DLFileShortcut (groupId, folderId, status)",
		"create index IX_55C736AC on DLFileShortcut (groupId, toFolderId, toName)",
		"create index IX_346A0992 on DLFileShortcut (groupId, toFolderId, toName, status)",
		"create index IX_4831EBE4 on DLFileShortcut (uuid_)",
		"create unique index IX_FDB4A946 on DLFileShortcut (uuid_, groupId)"
	};

}