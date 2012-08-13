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
import com.liferay.portal.model.Role;
import com.liferay.portal.service.base.ResourceBlockServiceBaseImpl;

import java.util.List;
import java.util.Map;

/**
 * @author Connor McKay
 */
public class ResourceBlockServiceImpl extends ResourceBlockServiceBaseImpl {

	public void addCompanyScopePermission(
			long scopeGroupId, long companyId, String name, long roleId,
			String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.addCompanyScopePermission(
			companyId, name, roleId, actionId);
	}

	public void addGroupScopePermission(
			long scopeGroupId, long companyId, long groupId, String name,
			long roleId, String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.addGroupScopePermission(
			companyId, groupId, name, roleId, actionId);
	}

	public void addIndividualScopePermission(
			long companyId, long groupId, String name, long primKey,
			long roleId, String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(groupId, name, primKey);

		resourceBlockLocalService.addIndividualScopePermission(
			companyId, groupId, name, primKey, roleId, actionId);
	}

	public void removeAllGroupScopePermissions(
			long scopeGroupId, long companyId, String name, long roleId,
			String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.removeAllGroupScopePermissions(
			companyId, name, roleId, actionId);
	}

	public void removeCompanyScopePermission(
			long scopeGroupId, long companyId, String name, long roleId,
			String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.removeCompanyScopePermission(
			companyId, name, roleId, actionId);
	}

	public void removeGroupScopePermission(
			long scopeGroupId, long companyId, long groupId, String name,
			long roleId, String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.removeGroupScopePermission(
			companyId, groupId, name, roleId, actionId);
	}

	public void removeIndividualScopePermission(
			long companyId, long groupId, String name, long primKey,
			long roleId, String actionId)
		throws PortalException, SystemException {

		permissionService.checkPermission(groupId, name, primKey);

		resourceBlockLocalService.removeIndividualScopePermission(
			companyId, groupId, name, primKey, roleId, actionId);
	}

	public void setCompanyScopePermissions(
			long scopeGroupId, long companyId, String name, long roleId,
			List<String> actionIds)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.setCompanyScopePermissions(
			companyId, name, roleId, actionIds);
	}

	public void setGroupScopePermissions(
			long scopeGroupId, long companyId, long groupId, String name,
			long roleId, List<String> actionIds)
		throws PortalException, SystemException {

		permissionService.checkPermission(
			scopeGroupId, Role.class.getName(), roleId);

		resourceBlockLocalService.setGroupScopePermissions(
			companyId, groupId, name, roleId, actionIds);
	}

	public void setIndividualScopePermissions(
			long companyId, long groupId, String name, long primKey,
			long roleId, List<String> actionIds)
		throws PortalException, SystemException {

		permissionService.checkPermission(groupId, name, primKey);

		resourceBlockLocalService.setIndividualScopePermissions(
			companyId, groupId, name, primKey, roleId, actionIds);
	}

	public void setIndividualScopePermissions(
			long companyId, long groupId, String name, long primKey,
			Map<Long, String[]> roleIdsToActionIds)
		throws PortalException, SystemException {

		permissionService.checkPermission(groupId, name, primKey);

		resourceBlockLocalService.setIndividualScopePermissions(
			companyId, groupId, name, primKey, roleIdsToActionIds);
	}

}