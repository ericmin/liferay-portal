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
public class IGImageTable {

	public static final String TABLE_NAME = "IGImage";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"imageId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"folderId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"smallImageId", Types.BIGINT},
		{"largeImageId", Types.BIGINT},
		{"custom1ImageId", Types.BIGINT},
		{"custom2ImageId", Types.BIGINT}
	};

	public static final String TABLE_SQL_CREATE = "create table IGImage (uuid_ VARCHAR(75) null,imageId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,folderId LONG,name VARCHAR(75) null,description STRING null,smallImageId LONG,largeImageId LONG,custom1ImageId LONG,custom2ImageId LONG)";

	public static final String TABLE_SQL_DROP = "drop table IGImage";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_E597322D on IGImage (custom1ImageId)",
		"create index IX_D9E0A34C on IGImage (custom2ImageId)",
		"create index IX_4438CA80 on IGImage (folderId)",
		"create index IX_BCB13A3F on IGImage (folderId, name)",
		"create index IX_64F0B572 on IGImage (largeImageId)",
		"create index IX_D3D32126 on IGImage (smallImageId)",
		"create index IX_265BB0F1 on IGImage (uuid_)"
	};

}