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

package com.liferay.portlet.layoutsetprototypes.action;

import com.liferay.portal.NoSuchLayoutSetPrototypeException;
import com.liferay.portal.RequiredLayoutSetPrototypeException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
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
 * @author Ryan Park
 * @author Máté Thurzó
 */
public class EditLayoutSetPrototypeAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateLayoutSetPrototype(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteLayoutSetPrototypes(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, e.getClass());

				setForward(
					actionRequest, "portlet.layout_set_prototypes.error");
			}
			else if (e instanceof RequiredLayoutSetPrototypeException) {
				SessionErrors.add(actionRequest, e.getClass());

				String redirect = PortalUtil.escapeRedirect(
					ParamUtil.getString(actionRequest, "redirect"));

				if (Validator.isNotNull(redirect)) {
					actionResponse.sendRedirect(redirect);
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
			ActionUtil.getLayoutSetPrototype(renderRequest);
		}
		catch (Exception e) {
			if (e instanceof NoSuchLayoutSetPrototypeException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return mapping.findForward(
					"portlet.layout_set_prototypes.error");
			}
			else {
				throw e;
			}
		}

		return mapping.findForward(getForward(
			renderRequest,
			"portlet.layout_set_prototypes.edit_layout_set_prototype"));
	}

	protected void deleteLayoutSetPrototypes(ActionRequest actionRequest)
		throws Exception {

		long[] layoutSetPrototypeIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "layoutSetPrototypeIds"), 0L);

		for (long layoutSetPrototypeId : layoutSetPrototypeIds) {
			LayoutSetPrototypeServiceUtil.deleteLayoutSetPrototype(
				layoutSetPrototypeId);
		}
	}

	protected void updateLayoutSetPrototype(ActionRequest actionRequest)
		throws Exception {

		long layoutSetPrototypeId = ParamUtil.getLong(
			actionRequest, "layoutSetPrototypeId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");
		boolean layoutsUpdateable = ParamUtil.getBoolean(
			actionRequest, "layoutsUpdateable");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		LayoutSetPrototype layoutSetPrototype = null;

		if (layoutSetPrototypeId <= 0) {

			// Add layout prototoype

			layoutSetPrototype =
				LayoutSetPrototypeServiceUtil.addLayoutSetPrototype(
					nameMap, description, active, layoutsUpdateable,
					serviceContext);
		}
		else {

			// Update layout prototoype

			layoutSetPrototype =
				LayoutSetPrototypeServiceUtil.updateLayoutSetPrototype(
					layoutSetPrototypeId, nameMap, description, active,
					layoutsUpdateable, serviceContext);
		}

		// Custom JSPs

		String customJspServletContextName = ParamUtil.getString(
			actionRequest, "customJspServletContextName");

		UnicodeProperties settingsProperties =
			layoutSetPrototype.getSettingsProperties();

		settingsProperties.setProperty(
			"customJspServletContextName", customJspServletContextName);

		LayoutSetPrototypeServiceUtil.updateLayoutSetPrototype(
			layoutSetPrototype.getLayoutSetPrototypeId(),
			settingsProperties.toString());
	}

}