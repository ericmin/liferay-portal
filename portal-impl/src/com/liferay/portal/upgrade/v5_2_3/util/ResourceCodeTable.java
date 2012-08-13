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
public class ResourceCodeTable {

	public static final String TABLE_NAME = "ResourceCode";

	public static final Object[][] TABLE_COLUMNS = {
		{"codeId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"scope", Types.INTEGER}
	};

	public static final String TABLE_SQL_CREATE = "create table ResourceCode (codeId LONG not null primary key,companyId LONG,name VARCHAR(255) null,scope INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table ResourceCode";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_717FDD47 on ResourceCode (companyId)",
		"create unique index IX_A32C097E on ResourceCode (companyId, name, scope)",
		"create index IX_AACAFF40 on ResourceCode (name)"
	};

}