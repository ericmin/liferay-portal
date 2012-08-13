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

package com.liferay.portal.upgrade.v5_2_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class OrganizationTable {

	public static final String TABLE_NAME = "Organization_";

	public static final Object[][] TABLE_COLUMNS = {
		{"organizationId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"parentOrganizationId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"type_", Types.VARCHAR},
		{"recursable", Types.BOOLEAN},
		{"regionId", Types.BIGINT},
		{"countryId", Types.BIGINT},
		{"statusId", Types.INTEGER},
		{"comments", Types.VARCHAR}
	};

	public static final String TABLE_SQL_CREATE = "create table Organization_ (organizationId LONG not null primary key,companyId LONG,parentOrganizationId LONG,name VARCHAR(100) null,type_ VARCHAR(75) null,recursable BOOLEAN,regionId LONG,countryId LONG,statusId INTEGER,comments STRING null)";

	public static final String TABLE_SQL_DROP = "drop table Organization_";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_834BCEB6 on Organization_ (companyId)",
		"create index IX_E301BDF5 on Organization_ (companyId, name)",
		"create index IX_418E4522 on Organization_ (companyId, parentOrganizationId)"
	};

}