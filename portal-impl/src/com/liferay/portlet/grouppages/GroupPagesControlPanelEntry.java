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

package com.liferay.portlet.grouppages;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletCategoryKeys;
import com.liferay.portlet.BaseControlPanelEntry;

/**
 * @author Jorge Ferrer
 * @author Sergio González
 */
public class GroupPagesControlPanelEntry extends BaseControlPanelEntry {

	public boolean isVisible(
			PermissionChecker permissionChecker, Portlet portlet)
		throws Exception {

		return false;
	}

	@Override
	public boolean isVisible(
			Portlet portlet, String category, ThemeDisplay themeDisplay)
		throws Exception {

		String controlPanelCategory = themeDisplay.getControlPanelCategory();

		if (controlPanelCategory.equals(PortletCategoryKeys.CONTENT)) {
			return false;
		}

		boolean visible = super.isVisible(portlet, category, themeDisplay);

		if (!visible) {
			visible = GroupPermissionUtil.contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), ActionKeys.MANAGE_LAYOUTS);
		}

		if (visible) {
			Group scopeGroup = themeDisplay.getScopeGroup();

			if (scopeGroup.isCompany()) {
				visible = false;
			}
		}

		return visible;
	}

}