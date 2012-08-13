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

package com.liferay.portlet.rolesadmin.action;

import com.liferay.portal.DuplicateRoleException;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.RequiredRoleException;
import com.liferay.portal.RoleNameException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.RoleServiceUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.util.PortalUtil;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class EditRoleAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateRole(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteRole(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, e.getClass());

				setForward(actionRequest, "portlet.roles_admin.error");
			}
			else if (e instanceof DuplicateRoleException ||
					 e instanceof NoSuchRoleException ||
					 e instanceof RequiredRoleException ||
					 e instanceof RoleNameException) {

				SessionErrors.add(actionRequest, e.getClass());

				if (cmd.equals(Constants.DELETE)) {
					String redirect = PortalUtil.escapeRedirect(
						ParamUtil.getString(actionRequest, "redirect"));

					if (Validator.isNotNull(redirect)) {
						actionResponse.sendRedirect(redirect);
					}
				}
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

		try {
			ActionUtil.getRole(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchRoleException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return mapping.findForward("portlet.roles_admin.error");
			}
			else {
				throw e;
			}
		}

		return mapping.findForward(
			getForward(renderRequest, "portlet.roles_admin.edit_role"));
	}

	protected void deleteRole(ActionRequest actionRequest) throws Exception {
		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		RoleServiceUtil.deleteRole(roleId);
	}

	protected void updateRole(ActionRequest actionRequest) throws Exception {
		long roleId = ParamUtil.getLong(actionRequest, "roleId");

		String name = ParamUtil.getString(actionRequest, "name");
		Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		int type = ParamUtil.getInteger(
			actionRequest, "type", RoleConstants.TYPE_REGULAR);
		String subtype = ParamUtil.getString(actionRequest, "subtype");

		if (roleId <= 0) {

			// Add role

			RoleServiceUtil.addRole(name, titleMap, descriptionMap, type);
		}
		else {

			// Update role

			RoleServiceUtil.updateRole(
				roleId, name, titleMap, descriptionMap, subtype);
		}
	}

}