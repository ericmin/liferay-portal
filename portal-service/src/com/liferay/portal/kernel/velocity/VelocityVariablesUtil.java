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

package com.liferay.portal.kernel.velocity;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class VelocityVariablesUtil {

	public static VelocityVariables getVelocityVariables() {
		PortalRuntimePermission.checkGetBeanProperty(
			VelocityVariablesUtil.class);

		return _velocityVariables;
	}

	public static void insertHelperUtilities(
		VelocityContext velocityContext, String[] restrictedVariables) {

		getVelocityVariables().insertHelperUtilities(
			velocityContext, restrictedVariables);
	}

	public static void insertVariables(
			VelocityContext velocityContext, HttpServletRequest request)
		throws Exception {

		getVelocityVariables().insertVariables(velocityContext, request);
	}

	public void setVelocityVariables(VelocityVariables velocityVariables) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_velocityVariables = velocityVariables;
	}

	private static VelocityVariables _velocityVariables;

}