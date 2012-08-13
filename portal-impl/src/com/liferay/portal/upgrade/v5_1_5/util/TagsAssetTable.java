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
public class TagsAssetTable {

	public static final String TABLE_NAME = "TagsAsset";

	public static final Object[][] TABLE_COLUMNS = {
		{"assetId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT},
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

	public static final String TABLE_SQL_CREATE = "create table TagsAsset (assetId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,startDate DATE null,endDate DATE null,publishDate DATE null,expirationDate DATE null,mimeType VARCHAR(75) null,title VARCHAR(255) null,description STRING null,summary STRING null,url STRING null,height INTEGER,width INTEGER,priority DOUBLE,viewCount INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table TagsAsset";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_1AB6D6D2 on TagsAsset (classNameId, classPK)",
		"create index IX_AB3D8BCB on TagsAsset (companyId)"
	};

}