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

package com.liferay.portal.security.permission;

import com.liferay.portal.model.Permission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PermissionsListFilterImpl implements PermissionsListFilter {

	public List<Permission> filterGroupPermissions(
		long companyId, long groupId, long userId, String name, String primKey,
		boolean portletActions, List<Permission> permissions) {

		return permissions;
	}

	public List<Permission> filterGuestPermissions(
		long companyId, long groupId, long userId, String name, String primKey,
		boolean portletActions, List<Permission> permissions) {

		return permissions;
	}

	public List<Permission> filterUserPermissions(
		long companyId, long groupId, long userId, String name, String primKey,
		boolean portletActions, List<Permission> permissions) {

		return permissions;
	}

}