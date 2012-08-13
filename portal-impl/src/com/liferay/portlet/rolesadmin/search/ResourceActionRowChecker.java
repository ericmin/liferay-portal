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

package com.liferay.portlet.rolesadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.ResourceTypePermissionLocalServiceUtil;
import com.liferay.portal.util.PropsValues;

import javax.portlet.RenderResponse;

/**
 * @author Jorge Ferrer
 * @author Connor McKay
 */
public class ResourceActionRowChecker extends RowChecker {

	public ResourceActionRowChecker(RenderResponse renderResponse) {
		super(renderResponse);
	}

	@Override
	public boolean isChecked(Object obj) {
		try {
			return doIsChecked(obj);
		}
		catch (Exception e) {
			return false;
		}
	}

	protected boolean doIsChecked(Object obj) throws Exception {
		Object[] objArray = (Object[])obj;

		Role role = (Role)objArray[0];
		String actionId = (String)objArray[1];
		String resourceName = (String)objArray[2];
		Integer scope = (Integer)objArray[4];

		if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
			if (ResourceBlockLocalServiceUtil.isSupported(resourceName)) {
				return ResourceTypePermissionLocalServiceUtil.
					hasEitherScopePermission(
						role.getCompanyId(), resourceName, role.getRoleId(),
						actionId);
			}

			return
				ResourcePermissionLocalServiceUtil.hasScopeResourcePermission(
					role.getCompanyId(), resourceName, scope, role.getRoleId(),
					actionId);
		}
		else {
			return PermissionLocalServiceUtil.hasRolePermission(
				role.getRoleId(), role.getCompanyId(), resourceName, scope,
				actionId);
		}
	}

}