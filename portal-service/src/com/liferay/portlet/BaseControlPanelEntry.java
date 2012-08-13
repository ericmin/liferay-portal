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

package com.liferay.portlet;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletCategoryKeys;

/**
 * @author Jorge Ferrer
 */
public abstract class BaseControlPanelEntry implements ControlPanelEntry {

	public boolean isVisible(
			Portlet portlet, String category, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		Group group = themeDisplay.getScopeGroup();

		long plid = LayoutConstants.DEFAULT_PLID;

		if (category.equals(PortletCategoryKeys.CONTENT)) {
			plid = group.getDefaultPublicPlid();

			if (plid == LayoutConstants.DEFAULT_PLID) {
				plid = group.getDefaultPrivatePlid();
			}
		}

		if (category.equals(PortletCategoryKeys.CONTENT) &&
			permissionChecker.isGroupAdmin(group.getGroupId()) &&
			!group.isUser()) {

			return true;
		}

		long groupId = group.getGroupId();

		if (category.equals(PortletCategoryKeys.PORTAL) ||
			category.equals(PortletCategoryKeys.SERVER)) {

			groupId = 0;
		}

		if (PortletPermissionUtil.contains(
				permissionChecker, groupId, plid, portlet.getPortletId(),
				ActionKeys.ACCESS_IN_CONTROL_PANEL, true)) {

			return true;
		}

		return isVisible(themeDisplay.getPermissionChecker(), portlet);
	}

}