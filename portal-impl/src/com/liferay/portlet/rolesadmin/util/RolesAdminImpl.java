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

package com.liferay.portlet.rolesadmin.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class RolesAdminImpl implements RolesAdmin {

	public String getCssClassName(Role role) {
		String cssClassName = StringPool.BLANK;

		String name = role.getName();
		int type = role.getType();

		if (name.equals(RoleConstants.GUEST)) {
			cssClassName = "lfr-role-guest";
		}
		else if (type == RoleConstants.TYPE_ORGANIZATION) {
			cssClassName = "lfr-role-organization";
		}
		else if (type == RoleConstants.TYPE_REGULAR) {
			cssClassName = "lfr-role-regular";
		}
		else if (type == RoleConstants.TYPE_SITE) {
			cssClassName = "lfr-role-site";
		}
		else if (role.isTeam()) {
			cssClassName = "lfr-role-team";
		}

		return "lfr-role " + cssClassName;
	}

}