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

import com.liferay.portal.NoSuchUserGroupGroupRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupGroupRole;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.base.UserGroupGroupRoleLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.UserGroupGroupRolePK;

import java.util.List;

/**
 * @author Brett Swaim
 */
public class UserGroupGroupRoleLocalServiceImpl
	extends UserGroupGroupRoleLocalServiceBaseImpl {

	public void addUserGroupGroupRoles(
			long userGroupId, long groupId, long[] roleIds)
		throws PortalException, SystemException {

		checkGroupResource(groupId);

		for (long roleId : roleIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByPrimaryKey(pk);

			if (userGroupGroupRole == null) {
				userGroupGroupRole = userGroupGroupRolePersistence.create(pk);

				userGroupGroupRolePersistence.update(userGroupGroupRole, false);
			}
		}

		PermissionCacheUtil.clearCache();
	}

	public void addUserGroupGroupRoles(
			long[] userGroupIds, long groupId, long roleId)
		throws PortalException, SystemException {

		checkGroupResource(groupId);

		for (long userGroupId : userGroupIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			UserGroupGroupRole userGroupGroupRole =
				userGroupGroupRolePersistence.fetchByPrimaryKey(pk);

			if (userGroupGroupRole == null) {
				userGroupGroupRole = userGroupGroupRolePersistence.create(pk);

				userGroupGroupRolePersistence.update(userGroupGroupRole, false);
			}
		}

		PermissionCacheUtil.clearCache();
	}

	@Override
	public UserGroupGroupRole deleteUserGroupGroupRole(
			UserGroupGroupRole userGroupGroupRole)
		throws SystemException {

		userGroupGroupRolePersistence.remove(userGroupGroupRole);

		PermissionCacheUtil.clearCache();

		return userGroupGroupRole;
	}

	public void deleteUserGroupGroupRoles(
			long userGroupId, long groupId, long[] roleIds)
		throws SystemException {

		for (long roleId : roleIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			try {
				userGroupGroupRolePersistence.remove(pk);
			}
			catch (NoSuchUserGroupGroupRoleException nsuggre) {
			}
		}

		PermissionCacheUtil.clearCache();
	}

	public void deleteUserGroupGroupRoles(long userGroupId, long[] groupIds)
		throws SystemException {

		for (long groupId : groupIds) {
			userGroupGroupRolePersistence.removeByU_G(userGroupId, groupId);
		}

		PermissionCacheUtil.clearCache();
	}

	public void deleteUserGroupGroupRoles(long[] userGroupIds, long groupId)
		throws SystemException {

		for (long userGroupId : userGroupIds) {
			userGroupGroupRolePersistence.removeByU_G(userGroupId, groupId);
		}

		PermissionCacheUtil.clearCache();
	}

	public void deleteUserGroupGroupRoles(
			long[] userGroupIds, long groupId, long roleId)
		throws SystemException {

		for (long userGroupId : userGroupIds) {
			UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
				userGroupId, groupId, roleId);

			try {
				userGroupGroupRolePersistence.remove(pk);
			}
			catch (NoSuchUserGroupGroupRoleException nsuggre) {
			}
		}

		PermissionCacheUtil.clearCache();
	}

	public void deleteUserGroupGroupRolesByGroupId(long groupId)
		throws SystemException {

		userGroupGroupRolePersistence.removeByGroupId(groupId);

		PermissionCacheUtil.clearCache();
	}

	public void deleteUserGroupGroupRolesByRoleId(long roleId)
		throws SystemException {

		userGroupGroupRolePersistence.removeByRoleId(roleId);

		PermissionCacheUtil.clearCache();
	}

	public void deleteUserGroupGroupRolesByUserGroupId(long userGroupId)
		throws SystemException {

		userGroupGroupRolePersistence.removeByUserGroupId(userGroupId);

		PermissionCacheUtil.clearCache();
	}

	public List<UserGroupGroupRole> getUserGroupGroupRoles(long userGroupId)
		throws SystemException {

		return userGroupGroupRolePersistence.findByUserGroupId(userGroupId);
	}

	public List<UserGroupGroupRole> getUserGroupGroupRoles(
			long userGroupId, long groupId)
		throws SystemException {

		return userGroupGroupRolePersistence.findByU_G(userGroupId, groupId);
	}

	public List<UserGroupGroupRole> getUserGroupGroupRolesByGroupAndRole(
			long groupId, long roleId)
		throws SystemException {

		return userGroupGroupRolePersistence.findByG_R(groupId, roleId);
	}

	public boolean hasUserGroupGroupRole(
			long userGroupId, long groupId, long roleId)
		throws SystemException {

		UserGroupGroupRolePK pk = new UserGroupGroupRolePK(
			userGroupId, groupId, roleId);

		UserGroupGroupRole userGroupGroupRole =
			userGroupGroupRolePersistence.fetchByPrimaryKey(pk);

		if (userGroupGroupRole != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasUserGroupGroupRole(
			long userGroupId, long groupId, String roleName)
		throws PortalException, SystemException {

		UserGroup userGroup = userGroupPersistence.findByPrimaryKey(
			userGroupId);

		long companyId = userGroup.getCompanyId();

		Role role = rolePersistence.findByC_N(companyId, roleName);

		long roleId = role.getRoleId();

		return hasUserGroupGroupRole(userGroupId, groupId, roleId);
	}

	protected void checkGroupResource(long groupId)
		throws PortalException, SystemException {

		// Make sure that the individual resource for the group exists

		Group group = groupPersistence.findByPrimaryKey(groupId);

		resourceLocalService.addResource(
			group.getCompanyId(), Group.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(groupId));
	}

}