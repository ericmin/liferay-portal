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

package com.liferay.portlet.dynamicdatalists.lar;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Michael C. Han
 */
public class DDLPortletDataHandlerUtil {

	public static DDLPortletDataHandler getDDLPortletDataHandler() {
		PortalRuntimePermission.checkGetBeanProperty(
			DDLPortletDataHandlerUtil.class);

		return _ddlPortletDataHandler;
	}

	public void setDDLPortletDataHandler(
		DDLPortletDataHandler ddlPortletDataHandler) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddlPortletDataHandler = ddlPortletDataHandler;
	}

	private static DDLPortletDataHandler _ddlPortletDataHandler;

}