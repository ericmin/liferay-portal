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

package com.liferay.portal.model;

/**
 * Contains constants used by roles, including the names of the default roles
 * and the role types.
 *
 * @author Brian Wing Shun Chan
 */
public class RoleConstants {

	public static final String ADMINISTRATOR = "Administrator";

	public static final String GUEST = "Guest";

	public static final String ORGANIZATION_ADMINISTRATOR =
		"Organization Administrator";

	public static final String ORGANIZATION_OWNER = "Organization Owner";

	public static final String ORGANIZATION_USER = "Organization User";

	public static final String OWNER = "Owner";

	public static final String POWER_USER = "Power User";

	public static final String SITE_ADMINISTRATOR = "Site Administrator";

	public static final String SITE_MEMBER = "Site Member";

	public static final String SITE_OWNER = "Site Owner";

	public static final String[] SYSTEM_ORGANIZATION_ROLES = {
		ORGANIZATION_ADMINISTRATOR, ORGANIZATION_OWNER, ORGANIZATION_USER
	};

	public static final String[] SYSTEM_ROLES = {
		ADMINISTRATOR, GUEST, OWNER, POWER_USER, RoleConstants.USER
	};

	public static final String[] SYSTEM_SITE_ROLES = {
		SITE_ADMINISTRATOR, SITE_MEMBER, SITE_OWNER
	};

	public static final int TYPE_ORGANIZATION = 3;

	public static final String TYPE_ORGANIZATION_LABEL = "organization";

	public static final int TYPE_PROVIDER = 4;

	public static final int TYPE_REGULAR = 1;

	public static final String TYPE_REGULAR_LABEL = "regular";

	public static final int TYPE_SITE = 2;

	public static final String TYPE_SITE_LABEL = "site";

	public static final String USER = "User";

	public static String getTypeLabel(int type) {
		if (type == TYPE_ORGANIZATION) {
			return TYPE_ORGANIZATION_LABEL;
		}
		else if (type == TYPE_SITE) {
			return TYPE_SITE_LABEL;
		}
		else {
			return TYPE_REGULAR_LABEL;
		}
	}

}