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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plugin setting remote service. This utility wraps {@link com.liferay.portal.service.impl.PluginSettingServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PluginSettingService
 * @see com.liferay.portal.service.base.PluginSettingServiceBaseImpl
 * @see com.liferay.portal.service.impl.PluginSettingServiceImpl
 * @generated
 */
public class PluginSettingServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.PluginSettingServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static com.liferay.portal.model.PluginSetting updatePluginSetting(
		long companyId, java.lang.String pluginId, java.lang.String pluginType,
		java.lang.String roles, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePluginSetting(companyId, pluginId, pluginType, roles,
			active);
	}

	public static PluginSettingService getService() {
		if (_service == null) {
			_service = (PluginSettingService)PortalBeanLocatorUtil.locate(PluginSettingService.class.getName());

			ReferenceRegistry.registerReference(PluginSettingServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(PluginSettingService service) {
	}

	private static PluginSettingService _service;
}