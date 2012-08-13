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
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

/**
 * @author Charles May
 * @author Jorge Ferrer
 */
public class OrganizationPermissionImpl implements OrganizationPermission {

	public void check(
			PermissionChecker permissionChecker, long organizationId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, organizationId, actionId)) {
			throw new PrincipalException();
		}
	}

	public void check(
			PermissionChecker permissionChecker, Organization organization,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, organization, actionId)) {
			throw new PrincipalException();
		}
	}

	public boolean contains(
			PermissionChecker permissionChecker, long organizationId,
			String actionId)
		throws PortalException, SystemException {

		if (organizationId > 0) {
			Organization organization =
				OrganizationLocalServiceUtil.getOrganization(organizationId);

			return contains(permissionChecker, organization, actionId);
		}
		else {
			return false;
		}
	}

	public boolean contains(
			PermissionChecker permissionChecker, long[] organizationIds,
			String actionId)
		throws PortalException, SystemException {

		if ((organizationIds == null) || (organizationIds.length == 0)) {
			return true;
		}

		for (long organizationId : organizationIds) {
			check(permissionChecker, organizationId, actionId);
		}

		return true;
	}

	public boolean contains(
			PermissionChecker permissionChecker, Organization organization,
			String actionId)
		throws PortalException, SystemException {

		Group group = organization.getGroup();

		long groupId = group.getGroupId();

		if (contains(permissionChecker, groupId, organization, actionId)) {
			return true;
		}

		while (!organization.isRoot()) {
			Organization parentOrganization =
				organization.getParentOrganization();

			Group parentGroup = parentOrganization.getGroup();

			groupId = parentGroup.getGroupId();

			if (contains(
					permissionChecker, groupId, parentOrganization,
					ActionKeys.MANAGE_SUBORGANIZATIONS)) {

				return true;
			}

			organization = parentOrganization;
		}

		return false;
	}

	protected boolean contains(
			PermissionChecker permissionChecker, long groupId,
			Organization organization, String actionId)
		throws PortalException, SystemException {

		while ((organization != null) &&
			   (organization.getOrganizationId() !=
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID)) {

			if (permissionChecker.hasPermission(
					groupId, Organization.class.getName(),
					organization.getOrganizationId(), actionId)) {

				return true;
			}

			organization = organization.getParentOrganization();
		}

		return false;
	}

}