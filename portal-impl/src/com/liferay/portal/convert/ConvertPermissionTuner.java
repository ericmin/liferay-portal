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

package com.liferay.portal.convert;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Alexander Chow
 */
public class ConvertPermissionTuner extends ConvertProcess {

	@Override
	public String getDescription() {
		return "fine-tune-generated-roles";
	}

	@Override
	public String getPath() {
		return "/admin_server/edit_permissions";
	}

	@Override
	public boolean isEnabled() {
		boolean enabled = false;

		try {
			if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
				int count = RoleLocalServiceUtil.getSubtypeRolesCount(
					"lfr-permission-algorithm-5");

				if (count > 0) {
					enabled = true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return enabled;
	}

	@Override
	protected void doConvert() throws Exception {
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConvertPermissionTuner.class);

}