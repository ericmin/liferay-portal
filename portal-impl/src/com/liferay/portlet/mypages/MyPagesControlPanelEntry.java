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

package com.liferay.portlet.mypages;

import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.BaseControlPanelEntry;

/**
 * @author Jorge Ferrer
 * @author Amos Fong
 */
public class MyPagesControlPanelEntry extends BaseControlPanelEntry {

	public boolean isVisible(
			PermissionChecker permissionChecker, Portlet portlet)
		throws Exception {

		return false;
	}

	@Override
	public boolean isVisible(
			Portlet portlet, String category, ThemeDisplay themeDisplay)
		throws Exception {

		if (!PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED &&
			!PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {

			return false;
		}

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		boolean hasPowerUserRole = RoleLocalServiceUtil.hasUserRole(
			permissionChecker.getUserId(), permissionChecker.getCompanyId(),
			RoleConstants.POWER_USER, true);

		if ((PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_POWER_USER_REQUIRED ||
			 PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED) &&
			!hasPowerUserRole) {

			return false;
		}

		return super.isVisible(portlet, category, themeDisplay);
	}

}