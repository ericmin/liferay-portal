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
 * This class is a wrapper for {@link PluginSettingService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PluginSettingService
 * @generated
 */
public class PluginSettingServiceWrapper implements PluginSettingService,
	ServiceWrapper<PluginSettingService> {
	public PluginSettingServiceWrapper(
		PluginSettingService pluginSettingService) {
		_pluginSettingService = pluginSettingService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pluginSettingService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pluginSettingService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.PluginSetting updatePluginSetting(
		long companyId, java.lang.String pluginId, java.lang.String pluginType,
		java.lang.String roles, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pluginSettingService.updatePluginSetting(companyId, pluginId,
			pluginType, roles, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PluginSettingService getWrappedPluginSettingService() {
		return _pluginSettingService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPluginSettingService(
		PluginSettingService pluginSettingService) {
		_pluginSettingService = pluginSettingService;
	}

	public PluginSettingService getWrappedService() {
		return _pluginSettingService;
	}

	public void setWrappedService(PluginSettingService pluginSettingService) {
		_pluginSettingService = pluginSettingService;
	}

	private PluginSettingService _pluginSettingService;
}