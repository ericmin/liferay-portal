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

package com.liferay.portlet.portletconfiguration.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.struts.JSONAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.InvokerPortletImpl;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import java.util.Locale;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 * @author Wilson Man
 */
public class UpdateLookAndFeelAction extends JSONAction {

	@Override
	public String getJSON(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		String portletId = ParamUtil.getString(request, "portletId");

		if (!PortletPermissionUtil.contains(
				permissionChecker, themeDisplay.getPlid(), portletId,
				ActionKeys.CONFIGURATION)) {

			return null;
		}

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				layout, portletId);

		String css = ParamUtil.getString(request, "css");

		if (_log.isDebugEnabled()) {
			_log.debug("Updating css " + css);
		}

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject(css);

		JSONObject portletData = jsonObj.getJSONObject("portletData");

		jsonObj.remove("portletData");

		css = jsonObj.toString();

		boolean useCustomTitle = portletData.getBoolean("useCustomTitle");
		String showBorders = portletData.getString("showBorders");
		String linkToLayoutUuid = GetterUtil.getString(
			portletData.getString("portletLinksTarget"));

		JSONObject titles = portletData.getJSONObject("titles");

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (int i = 0; i < locales.length; i++) {
			String languageId = LocaleUtil.toLanguageId(locales[i]);

			String title = null;

			if (titles.has(languageId)) {
				title = GetterUtil.getString(titles.getString(languageId));
			}

			if (Validator.isNotNull(title)) {
				portletSetup.setValue("portletSetupTitle_" + languageId, title);
			}
			else {
				portletSetup.reset("portletSetupTitle_" + languageId);
			}
		}

		portletSetup.setValue(
			"portletSetupUseCustomTitle", String.valueOf(useCustomTitle));

		if (Validator.isNotNull(showBorders)) {
			boolean showBordersBoolean = portletData.getBoolean("showBorders");

			portletSetup.setValue(
				"portletSetupShowBorders", String.valueOf(showBordersBoolean));
		}
		else {
			portletSetup.reset("portletSetupShowBorders");
		}

		if (Validator.isNotNull(linkToLayoutUuid)) {
			portletSetup.setValue(
				"portletSetupLinkToLayoutUuid", linkToLayoutUuid);
		}
		else {
			portletSetup.reset("portletSetupLinkToLayoutUuid");
		}

		portletSetup.setValue("portletSetupCss", css);

		JSONObject wapData = jsonObj.getJSONObject("wapData");

		String wapTitle = wapData.getString("title");
		String wapInitialWindowState = wapData.getString("initialWindowState");

		portletSetup.setValue("lfrWapTitle", wapTitle);
		portletSetup.setValue(
			"lfrWapInitialWindowState", wapInitialWindowState);

		portletSetup.store();

		InvokerPortletImpl.clearResponse(
			session, layout.getPrimaryKey(), portletId,
			LanguageUtil.getLanguageId(request));

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		UpdateLookAndFeelAction.class);

}