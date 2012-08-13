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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.base.PluginSettingServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class PluginSettingServiceImpl extends PluginSettingServiceBaseImpl {

	public PluginSetting updatePluginSetting(
			long companyId, String pluginId, String pluginType, String roles,
			boolean active)
		throws PortalException, SystemException {

		if (!roleLocalService.hasUserRole(
				getUserId(), companyId, RoleConstants.ADMINISTRATOR, true)) {

			throw new PrincipalException();
		}

		return pluginSettingLocalService.updatePluginSetting(
			companyId, pluginId, pluginType, roles, active);
	}

}