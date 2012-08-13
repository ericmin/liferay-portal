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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class UserGroupRolePermissionImpl implements UserGroupRolePermission {

	public void check(
			PermissionChecker permissionChecker, long groupId, long roleId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, roleId)) {
			throw new PrincipalException();
		}
	}

	public boolean contains(
			PermissionChecker permissionChecker, long groupId, long roleId)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(groupId);

		if (permissionChecker.isGroupOwner(groupId) ||
			GroupPermissionUtil.contains(
				permissionChecker, groupId, ActionKeys.ASSIGN_USER_ROLES) ||
			OrganizationPermissionUtil.contains(
				permissionChecker, group.getOrganizationId(),
				ActionKeys.ASSIGN_USER_ROLES) ||
			RolePermissionUtil.contains(
				permissionChecker, groupId, roleId,
				ActionKeys.ASSIGN_MEMBERS)) {

			return true;
		}
		else {
			return false;
		}
	}

}