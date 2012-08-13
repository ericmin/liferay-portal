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

package com.liferay.portlet.usersadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.PermissionLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class GroupPermissionChecker extends RowChecker {

	public GroupPermissionChecker(
		RenderResponse renderResponse, Role role, String resourceName,
		String actionId) {

		super(renderResponse);

		_role = role;
		_resourceName = resourceName;
		_actionId = actionId;
	}

	@Override
	public boolean isChecked(Object obj) {
		Group group = (Group)obj;

		try {
			return PermissionLocalServiceUtil.hasRolePermission(
				_role.getRoleId(), group.getCompanyId(), _resourceName,
				ResourceConstants.SCOPE_GROUP,
				String.valueOf(group.getGroupId()), _actionId);
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		GroupPermissionChecker.class);

	private String _actionId;
	private String _resourceName;
	private Role _role;

}