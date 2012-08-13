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
public class SCProductEntryTable {

	public static final String TABLE_NAME = "SCProductEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"productEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"name", Types.VARCHAR},
		{"type_", Types.VARCHAR},
		{"tags", Types.VARCHAR},
		{"shortDescription", Types.VARCHAR},
		{"longDescription", Types.VARCHAR},
		{"pageURL", Types.VARCHAR},
		{"author", Types.VARCHAR},
		{"repoGroupId", Types.VARCHAR},
		{"repoArtifactId", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table SCProductEntry (productEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,type_ VARCHAR(75) null,tags VARCHAR(255) null,shortDescription STRING null,longDescription STRING null,pageURL STRING null,author VARCHAR(75) null,repoGroupId VARCHAR(75) null,repoArtifactId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table SCProductEntry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_5D25244F on SCProductEntry (companyId)",
		"create index IX_72F87291 on SCProductEntry (groupId)",
		"create index IX_98E6A9CB on SCProductEntry (groupId, userId)",
		"create index IX_7311E812 on SCProductEntry (repoGroupId, repoArtifactId)"
	};

}