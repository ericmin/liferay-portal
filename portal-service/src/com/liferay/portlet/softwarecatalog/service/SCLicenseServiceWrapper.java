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

package com.liferay.portlet.softwarecatalog.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SCLicenseService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SCLicenseService
 * @generated
 */
public class SCLicenseServiceWrapper implements SCLicenseService,
	ServiceWrapper<SCLicenseService> {
	public SCLicenseServiceWrapper(SCLicenseService scLicenseService) {
		_scLicenseService = scLicenseService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _scLicenseService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_scLicenseService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.softwarecatalog.model.SCLicense addLicense(
		java.lang.String name, java.lang.String url, boolean openSource,
		boolean active, boolean recommended)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scLicenseService.addLicense(name, url, openSource, active,
			recommended);
	}

	public void deleteLicense(long licenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_scLicenseService.deleteLicense(licenseId);
	}

	public com.liferay.portlet.softwarecatalog.model.SCLicense getLicense(
		long licenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scLicenseService.getLicense(licenseId);
	}

	public com.liferay.portlet.softwarecatalog.model.SCLicense updateLicense(
		long licenseId, java.lang.String name, java.lang.String url,
		boolean openSource, boolean active, boolean recommended)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _scLicenseService.updateLicense(licenseId, name, url,
			openSource, active, recommended);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SCLicenseService getWrappedSCLicenseService() {
		return _scLicenseService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSCLicenseService(SCLicenseService scLicenseService) {
		_scLicenseService = scLicenseService;
	}

	public SCLicenseService getWrappedService() {
		return _scLicenseService;
	}

	public void setWrappedService(SCLicenseService scLicenseService) {
		_scLicenseService = scLicenseService;
	}

	private SCLicenseService _scLicenseService;
}