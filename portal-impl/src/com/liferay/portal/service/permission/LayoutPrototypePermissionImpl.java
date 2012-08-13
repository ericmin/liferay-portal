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

import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Jorge Ferrer
 */
public class LayoutPrototypePermissionImpl
	implements LayoutPrototypePermission {

	public void check(
			PermissionChecker permissionChecker, long layoutPrototypeId,
			String actionId)
		throws PrincipalException {

		if (!contains(permissionChecker, layoutPrototypeId, actionId)) {
			throw new PrincipalException();
		}
	}

	public boolean contains(
		PermissionChecker permissionChecker, long layoutPrototypeId,
		String actionId) {

		if (permissionChecker.hasPermission(
				0, LayoutPrototype.class.getName(), layoutPrototypeId,
				actionId)) {

			return true;
		}

		return false;
	}

}