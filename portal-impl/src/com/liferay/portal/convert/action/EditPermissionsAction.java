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

package com.liferay.portal.convert.action;

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.RolePermissionsException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.struts.PortletAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Alexander Chow
 */
public class EditPermissionsAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals("merge")) {
				merge(actionRequest, actionResponse);
			}
			else if (cmd.equals("reassign")) {
				reassign(actionRequest, actionResponse);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchRoleException ||
				e instanceof PrincipalException ||
				e instanceof RolePermissionsException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				setForward(actionRequest, "portlet.admin.error");
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		return mapping.findForward(
			getForward(renderRequest, "portlet.admin.edit_permissions"));
	}

	protected void merge(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] roleIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "roleIds"), 0L);

		long toRoleId = roleIds[0];

		for (int i = 1; i < roleIds.length; i++) {
			long fromRoleId = roleIds[i];

			ResourcePermissionLocalServiceUtil.mergePermissions(
				fromRoleId, toRoleId);
		}
	}

	protected void reassign(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long resourcePermissionId = ParamUtil.getLong(
			actionRequest, "resourcePermissionId");
		long toRoleId = ParamUtil.getLong(actionRequest, "toRoleId");

		ResourcePermissionLocalServiceUtil.reassignPermissions(
			resourcePermissionId, toRoleId);
	}

}