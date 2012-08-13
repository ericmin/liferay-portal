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
 * This class is a wrapper for {@link PortletService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortletService
 * @generated
 */
public class PortletServiceWrapper implements PortletService,
	ServiceWrapper<PortletService> {
	public PortletServiceWrapper(PortletService portletService) {
		_portletService = portletService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _portletService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portletService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.kernel.json.JSONArray getWARPortlets() {
		return _portletService.getWARPortlets();
	}

	public com.liferay.portal.model.Portlet updatePortlet(long companyId,
		java.lang.String portletId, java.lang.String roles, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _portletService.updatePortlet(companyId, portletId, roles, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PortletService getWrappedPortletService() {
		return _portletService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPortletService(PortletService portletService) {
		_portletService = portletService;
	}

	public PortletService getWrappedService() {
		return _portletService;
	}

	public void setWrappedService(PortletService portletService) {
		_portletService = portletService;
	}

	private PortletService _portletService;
}