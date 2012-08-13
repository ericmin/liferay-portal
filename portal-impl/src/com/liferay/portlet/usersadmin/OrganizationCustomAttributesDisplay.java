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

package com.liferay.portlet.usersadmin;

import com.liferay.portal.model.Organization;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.BaseCustomAttributesDisplay;

/**
 * @author Jorge Ferrer
 */
public class OrganizationCustomAttributesDisplay
	extends BaseCustomAttributesDisplay {

	public static final String CLASS_NAME = Organization.class.getName();

	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getIconPath(ThemeDisplay themeDisplay) {
		return
			themeDisplay.getPathThemeImages() + "/common/organization_icon.png";
	}

}