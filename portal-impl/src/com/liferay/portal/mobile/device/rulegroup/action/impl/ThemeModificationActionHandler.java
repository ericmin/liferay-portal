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

package com.liferay.portal.mobile.device.rulegroup.action.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.mobile.device.rulegroup.action.ActionHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Theme;
import com.liferay.portal.model.impl.ColorSchemeImpl;
import com.liferay.portal.service.ThemeLocalService;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public class ThemeModificationActionHandler implements ActionHandler {

	public static String getHandlerType() {
		return ThemeModificationActionHandler.class.getName();
	}

	public void applyAction(
		MDRAction mdrAction, HttpServletRequest request,
		HttpServletResponse response) {

		long companyId = PortalUtil.getCompanyId(request);

		UnicodeProperties typeSettingsProperties =
			mdrAction.getTypeSettingsProperties();

		String themeId = GetterUtil.getString(
			typeSettingsProperties.getProperty("themeId"));

		Theme theme = _themeLocalService.fetchTheme(companyId, themeId);

		if (theme == null) {
			return;
		}

		request.setAttribute(WebKeys.THEME, theme);

		String colorSchemeId = GetterUtil.getString(
			typeSettingsProperties.getProperty("colorSchemeId"));

		ColorScheme colorScheme = _themeLocalService.fetchColorScheme(
			companyId, themeId, colorSchemeId);

		if (colorScheme == null) {
			colorScheme = ColorSchemeImpl.getNullColorScheme();
		}

		request.setAttribute(WebKeys.COLOR_SCHEME, colorScheme);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		themeDisplay.setLookAndFeel(theme, colorScheme);
	}

	public Collection<String> getPropertyNames() {
		return _propertyNames;
	}

	public String getType() {
		return getHandlerType();
	}

	public void setThemeLocalService(ThemeLocalService themeLocalService) {
		_themeLocalService = themeLocalService;
	}

	private static Collection<String> _propertyNames;

	@BeanReference(type = ThemeLocalService.class)
	private ThemeLocalService _themeLocalService;

	static {
		_propertyNames = new ArrayList<String>(2);

		_propertyNames.add("colorSchemeId");
		_propertyNames.add("themeId");

		_propertyNames = Collections.unmodifiableCollection(_propertyNames);
	}

}