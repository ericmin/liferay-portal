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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link ThemeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ThemeService
 * @generated
 */
public class ThemeServiceWrapper implements ThemeService,
	ServiceWrapper<ThemeService> {
	public ThemeServiceWrapper(ThemeService themeService) {
		_themeService = themeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _themeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_themeService.setBeanIdentifier(beanIdentifier);
	}

	public java.util.List<com.liferay.portal.model.Theme> getThemes(
		long companyId) {
		return _themeService.getThemes(companyId);
	}

	public com.liferay.portal.kernel.json.JSONArray getWARThemes() {
		return _themeService.getWARThemes();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ThemeService getWrappedThemeService() {
		return _themeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedThemeService(ThemeService themeService) {
		_themeService = themeService;
	}

	public ThemeService getWrappedService() {
		return _themeService;
	}

	public void setWrappedService(ThemeService themeService) {
		_themeService = themeService;
	}

	private ThemeService _themeService;
}