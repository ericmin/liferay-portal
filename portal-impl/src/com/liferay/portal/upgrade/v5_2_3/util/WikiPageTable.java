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
public class WikiPageTable {

	public static final String TABLE_NAME = "WikiPage";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"pageId", Types.BIGINT},
		{"resourcePrimKey", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"nodeId", Types.BIGINT},
		{"title", Types.VARCHAR},
		{"version", Types.DOUBLE},
		{"minorEdit", Types.BOOLEAN},
		{"content", Types.CLOB},
		{"summary", Types.VARCHAR},
		{"format", Types.VARCHAR},
		{"head", Types.BOOLEAN},
		{"parentTitle", Types.VARCHAR},
		{"redirectTitle", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table WikiPage (uuid_ VARCHAR(75) null,pageId LONG not null primary key,resourcePrimKey LONG,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,nodeId LONG,title VARCHAR(255) null,version DOUBLE,minorEdit BOOLEAN,content TEXT null,summary STRING null,format VARCHAR(75) null,head BOOLEAN,parentTitle VARCHAR(75) null,redirectTitle VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table WikiPage";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_A2001730 on WikiPage (format)",
		"create index IX_C8A9C476 on WikiPage (nodeId)",
		"create index IX_E7F635CA on WikiPage (nodeId, head)",
		"create index IX_65E84AF4 on WikiPage (nodeId, head, parentTitle)",
		"create index IX_46EEF3C8 on WikiPage (nodeId, parentTitle)",
		"create index IX_1ECC7656 on WikiPage (nodeId, redirectTitle)",
		"create index IX_997EEDD2 on WikiPage (nodeId, title)",
		"create index IX_E745EA26 on WikiPage (nodeId, title, head)",
		"create unique index IX_3D4AF476 on WikiPage (nodeId, title, version)",
		"create index IX_9C0E478F on WikiPage (uuid_)",
		"create unique index IX_899D3DFB on WikiPage (uuid_, groupId)"
	};

}