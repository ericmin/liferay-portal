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
public class UserTable {

	public static final String TABLE_NAME = "User_";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR},
		{"userId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"defaultUser", Types.BOOLEAN},
		{"contactId", Types.BIGINT},
		{"password_", Types.VARCHAR},
		{"passwordEncrypted", Types.BOOLEAN},
		{"passwordReset", Types.BOOLEAN},
		{"passwordModifiedDate", Types.TIMESTAMP},
		{"graceLoginCount", Types.INTEGER},
		{"screenName", Types.VARCHAR},
		{"emailAddress", Types.VARCHAR},
		{"openId", Types.VARCHAR},
		{"portraitId", Types.BIGINT},
		{"languageId", Types.VARCHAR},
		{"timeZoneId", Types.VARCHAR},
		{"greeting", Types.VARCHAR},
		{"comments", Types.VARCHAR},
		{"firstName", Types.VARCHAR},
		{"middleName", Types.VARCHAR},
		{"lastName", Types.VARCHAR},
		{"jobTitle", Types.VARCHAR},
		{"loginDate", Types.TIMESTAMP},
		{"loginIP", Types.VARCHAR},
		{"lastLoginDate", Types.TIMESTAMP},
		{"lastLoginIP", Types.VARCHAR},
		{"lastFailedLoginDate", Types.TIMESTAMP},
		{"failedLoginAttempts", Types.INTEGER},
		{"lockout", Types.BOOLEAN},
		{"lockoutDate", Types.TIMESTAMP},
		{"agreedToTermsOfUse", Types.BOOLEAN},
		{"active_", Types.BOOLEAN}
	};

	public static final String TABLE_SQL_CREATE = "create table User_ (uuid_ VARCHAR(75) null,userId LONG not null primary key,companyId LONG,createDate DATE null,modifiedDate DATE null,defaultUser BOOLEAN,contactId LONG,password_ VARCHAR(75) null,passwordEncrypted BOOLEAN,passwordReset BOOLEAN,passwordModifiedDate DATE null,graceLoginCount INTEGER,screenName VARCHAR(75) null,emailAddress VARCHAR(75) null,openId VARCHAR(1024) null,portraitId LONG,languageId VARCHAR(75) null,timeZoneId VARCHAR(75) null,greeting VARCHAR(255) null,comments STRING null,firstName VARCHAR(75) null,middleName VARCHAR(75) null,lastName VARCHAR(75) null,jobTitle VARCHAR(75) null,loginDate DATE null,loginIP VARCHAR(75) null,lastLoginDate DATE null,lastLoginIP VARCHAR(75) null,lastFailedLoginDate DATE null,failedLoginAttempts INTEGER,lockout BOOLEAN,lockoutDate DATE null,agreedToTermsOfUse BOOLEAN,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table User_";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_3A1E834E on User_ (companyId)",
		"create index IX_6EF03E4E on User_ (companyId, defaultUser)",
		"create unique index IX_615E9F7A on User_ (companyId, emailAddress)",
		"create unique index IX_C5806019 on User_ (companyId, screenName)",
		"create unique index IX_9782AD88 on User_ (companyId, userId)",
		"create unique index IX_5ADBE171 on User_ (contactId)",
		"create index IX_762F63C6 on User_ (emailAddress)",
		"create index IX_A9ED7DD3 on User_ (openId)",
		"create index IX_A18034A4 on User_ (portraitId)",
		"create index IX_E0422BDA on User_ (uuid_)"
	};

}