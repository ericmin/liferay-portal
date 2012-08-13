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
public class SCProductVersionTable {

	public static final String TABLE_NAME = "SCProductVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"productVersionId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"productEntryId", Types.BIGINT},
		{"version", Types.VARCHAR},
		{"changeLog", Types.VARCHAR},
		{"downloadPageURL", Types.VARCHAR},
		{"directDownloadURL", Types.VARCHAR},
		{"repoStoreArtifact", Types.BOOLEAN}
	};

	public static final String TABLE_SQL_CREATE = "create table SCProductVersion (productVersionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,productEntryId LONG,version VARCHAR(75) null,changeLog STRING null,downloadPageURL STRING null,directDownloadURL VARCHAR(2000) null,repoStoreArtifact BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table SCProductVersion";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_7020130F on SCProductVersion (directDownloadURL)",
		"create index IX_8377A211 on SCProductVersion (productEntryId)"
	};

}