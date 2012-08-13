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

package com.liferay.portal.upgrade.v5_2_8_to_6_0_5.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class DLFileEntryTable {

	public static final String TABLE_NAME = "DLFileEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"fileEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"versionUserId", Types.BIGINT},
		{"versionUserName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"folderId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"extension", Types.VARCHAR},
		{"title", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"extraSettings", Types.CLOB},
		{"version", Types.VARCHAR},
		{"size_", Types.BIGINT},
		{"readCount", Types.INTEGER}
	};

	public static final String TABLE_SQL_CREATE = "create table DLFileEntry (uuid_ VARCHAR(75) null,fileEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,versionUserId LONG,versionUserName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,folderId LONG,name VARCHAR(255) null,extension VARCHAR(75) null,title VARCHAR(255) null,description STRING null,extraSettings TEXT null,version VARCHAR(75) null,size_ LONG,readCount INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table DLFileEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_4CB1B2B4 on DLFileEntry (companyId)",
		"create index IX_F4AF5636 on DLFileEntry (groupId)",
		"create index IX_93CF8193 on DLFileEntry (groupId, folderId)",
		"create unique index IX_5391712 on DLFileEntry (groupId, folderId, name)",
		"create unique index IX_ED5CA615 on DLFileEntry (groupId, folderId, title)",
		"create index IX_43261870 on DLFileEntry (groupId, userId)",
		"create index IX_D20C434D on DLFileEntry (groupId, userId, folderId)",
		"create index IX_64F0FE40 on DLFileEntry (uuid_)",
		"create unique index IX_BC2E7E6A on DLFileEntry (uuid_, groupId)"
	};

}