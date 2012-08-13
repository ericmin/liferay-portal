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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.base.UserGroupRoleServiceBaseImpl;
import com.liferay.portal.service.permission.UserGroupRolePermissionUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class UserGroupRoleServiceImpl extends UserGroupRoleServiceBaseImpl {

	public void addUserGroupRoles(long userId, long groupId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			UserGroupRolePermissionUtil.check(
				getPermissionChecker(), groupId, roleId);
		}

		userGroupRoleLocalService.addUserGroupRoles(userId, groupId, roleIds);
	}

	public void addUserGroupRoles(long[] userIds, long groupId, long roleId)
		throws PortalException, SystemException {

		UserGroupRolePermissionUtil.check(
			getPermissionChecker(), groupId, roleId);

		userGroupRoleLocalService.addUserGroupRoles(userIds, groupId, roleId);
	}

	public void deleteUserGroupRoles(long userId, long groupId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			UserGroupRolePermissionUtil.check(
				getPermissionChecker(), groupId, roleId);
		}

		userGroupRoleLocalService.deleteUserGroupRoles(
			userId, groupId, roleIds);
	}

	public void deleteUserGroupRoles(long[] userIds, long groupId, long roleId)
		throws PortalException, SystemException {

		UserGroupRolePermissionUtil.check(
			getPermissionChecker(), groupId, roleId);

		userGroupRoleLocalService.deleteUserGroupRoles(
			userIds, groupId, roleId);
	}

}