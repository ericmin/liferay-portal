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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.security.Permission;

import javax.management.MBeanPermission;
import javax.management.MBeanTrustPermission;

/**
 * @author Raymond Augé
 */
public class MBeanChecker extends BaseChecker {

	public void afterPropertiesSet() {
	}

	public void checkPermission(Permission permission) {
		String name = permission.getName();
		String actions = permission.getActions();

		if ((permission instanceof MBeanPermission) &&
			(actions.equals(MBEAN_PERMISSION_IS_INSTANCE_OF) ||
			 actions.equals(MBEAN_PERMISSION_REGISTER_MBEAN))) {
		}
		else if ((permission instanceof MBeanTrustPermission) &&
				 name.equals(MBEAN_TRUST_PERMISSION_REGISTER)) {
		}
		else {
			throwSecurityException(
				_log, "Attempted to perform MBean operation " + permission);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MBeanChecker.class);

}