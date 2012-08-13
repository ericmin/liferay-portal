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

package com.liferay.portlet.mobiledevicerules.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;

/**
 * @author Michael C. Han
 */
public interface MDRRuleGroupPermission {

	public void check(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException, SystemException;

	public void check(
			PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
			String actionId)
		throws PortalException;

	public boolean contains(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException, SystemException;

	public boolean contains(
		PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
		String actionId);

}