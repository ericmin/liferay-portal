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

package com.liferay.portal.kernel.freemarker;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class FreeMarkerVariablesUtil {

	public static FreeMarkerVariables getFreeMarkerVariables() {
		PortalRuntimePermission.checkGetBeanProperty(
			FreeMarkerVariablesUtil.class);

		return _freeMarkerVariables;
	}

	public static void insertHelperUtilities(
		FreeMarkerContext freeMarkerContext, String[] restrictedVariables) {

		getFreeMarkerVariables().insertHelperUtilities(
			freeMarkerContext, restrictedVariables);
	}

	public static void insertVariables(
			FreeMarkerContext freeMarkerContext, HttpServletRequest request)
		throws Exception {

		getFreeMarkerVariables().insertVariables(freeMarkerContext, request);
	}

	public void setFreeMarkerVariables(
		FreeMarkerVariables freeMarkerVariables) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_freeMarkerVariables = freeMarkerVariables;
	}

	private static FreeMarkerVariables _freeMarkerVariables;

}