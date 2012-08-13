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
 * This class is a wrapper for {@link PortalLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortalLocalService
 * @generated
 */
public class PortalLocalServiceWrapper implements PortalLocalService,
	ServiceWrapper<PortalLocalService> {
	public PortalLocalServiceWrapper(PortalLocalService portalLocalService) {
		_portalLocalService = portalLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _portalLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portalLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PortalLocalService getWrappedPortalLocalService() {
		return _portalLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPortalLocalService(
		PortalLocalService portalLocalService) {
		_portalLocalService = portalLocalService;
	}

	public PortalLocalService getWrappedService() {
		return _portalLocalService;
	}

	public void setWrappedService(PortalLocalService portalLocalService) {
		_portalLocalService = portalLocalService;
	}

	private PortalLocalService _portalLocalService;
}