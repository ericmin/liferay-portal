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

package com.liferay.portal.upgrade.v6_0_12.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class AssetEntryTable {

	public static final String TABLE_NAME = "AssetEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"entryId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT},
		{"classUuid", Types.VARCHAR},
		{"visible", Types.BOOLEAN},
		{"startDate", Types.TIMESTAMP},
		{"endDate", Types.TIMESTAMP},
		{"publishDate", Types.TIMESTAMP},
		{"expirationDate", Types.TIMESTAMP},
		{"mimeType", Types.VARCHAR},
		{"title", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"summary", Types.VARCHAR},
		{"url", Types.VARCHAR},
		{"height", Types.INTEGER},
		{"width", Types.INTEGER},
		{"priority", Types.DOUBLE},
		{"viewCount", Types.INTEGER}
	};

	public static final String TABLE_SQL_CREATE = "create table AssetEntry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,classUuid VARCHAR(75) null,visible BOOLEAN,startDate DATE null,endDate DATE null,publishDate DATE null,expirationDate DATE null,mimeType VARCHAR(75) null,title STRING null,description STRING null,summary STRING null,url STRING null,height INTEGER,width INTEGER,priority DOUBLE,viewCount INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table AssetEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_1E9D371D on AssetEntry (classNameId, classPK)",
		"create index IX_FC1F9C7B on AssetEntry (classUuid)",
		"create index IX_7306C60 on AssetEntry (companyId)",
		"create index IX_1EBA6821 on AssetEntry (groupId, classUuid)"
	};

}