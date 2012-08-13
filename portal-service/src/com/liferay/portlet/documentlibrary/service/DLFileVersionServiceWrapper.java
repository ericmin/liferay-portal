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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DLFileVersionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLFileVersionService
 * @generated
 */
public class DLFileVersionServiceWrapper implements DLFileVersionService,
	ServiceWrapper<DLFileVersionService> {
	public DLFileVersionServiceWrapper(
		DLFileVersionService dlFileVersionService) {
		_dlFileVersionService = dlFileVersionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _dlFileVersionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlFileVersionService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileVersion getFileVersion(
		long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileVersionService.getFileVersion(fileVersionId);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileVersion getLatestFileVersion(
		long fileEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileVersionService.getLatestFileVersion(fileEntryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DLFileVersionService getWrappedDLFileVersionService() {
		return _dlFileVersionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDLFileVersionService(
		DLFileVersionService dlFileVersionService) {
		_dlFileVersionService = dlFileVersionService;
	}

	public DLFileVersionService getWrappedService() {
		return _dlFileVersionService;
	}

	public void setWrappedService(DLFileVersionService dlFileVersionService) {
		_dlFileVersionService = dlFileVersionService;
	}

	private DLFileVersionService _dlFileVersionService;
}