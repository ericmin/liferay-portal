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

package com.liferay.portal.kernel.struts;

import com.liferay.portal.kernel.util.PortalClassInvoker;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletActionInvoker {

	public static void processAction(
			String className, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortalClassInvoker.invoke(
			true, className, "processAction",
			new String[] {
				"org.apache.struts.action.ActionMapping",
				"org.apache.struts.action.ActionForm",
				PortletConfig.class.getName(), ActionRequest.class.getName(),
				ActionResponse.class.getName()
			},
			null, null, portletConfig, actionRequest, actionResponse);
	}

}