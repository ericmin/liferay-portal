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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Role;

/**
 * @author Brian Wing Shun Chan
 */
public class RolesAdminUtil {

	public static String getCssClassName(Role role) {
		return getRolesAdmin().getCssClassName(role);
	}

	public static RolesAdmin getRolesAdmin() {
		PortalRuntimePermission.checkGetBeanProperty(RolesAdminUtil.class);

		return _rolesAdmin;
	}

	public void setRolesAdmin(RolesAdmin rolesAdmin) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_rolesAdmin = rolesAdmin;
	}

	private static RolesAdmin _rolesAdmin;

}