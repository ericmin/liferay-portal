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
 * This class is a wrapper for {@link DLSyncService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLSyncService
 * @generated
 */
public class DLSyncServiceWrapper implements DLSyncService,
	ServiceWrapper<DLSyncService> {
	public DLSyncServiceWrapper(DLSyncService dlSyncService) {
		_dlSyncService = dlSyncService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _dlSyncService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlSyncService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.documentlibrary.model.DLSyncUpdate getDLSyncUpdate(
		long companyId, long repositoryId, java.util.Date lastAccessDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncService.getDLSyncUpdate(companyId, repositoryId,
			lastAccessDate);
	}

	public java.io.InputStream getFileDeltaAsStream(long fileEntryId,
		java.lang.String sourceVersion, java.lang.String destinationVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncService.getFileDeltaAsStream(fileEntryId, sourceVersion,
			destinationVersion);
	}

	public com.liferay.portal.kernel.repository.model.FileEntry updateFileEntry(
		long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.InputStream deltaInputStream, long size,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlSyncService.updateFileEntry(fileEntryId, sourceFileName,
			mimeType, title, description, changeLog, majorVersion,
			deltaInputStream, size, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DLSyncService getWrappedDLSyncService() {
		return _dlSyncService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDLSyncService(DLSyncService dlSyncService) {
		_dlSyncService = dlSyncService;
	}

	public DLSyncService getWrappedService() {
		return _dlSyncService;
	}

	public void setWrappedService(DLSyncService dlSyncService) {
		_dlSyncService = dlSyncService;
	}

	private DLSyncService _dlSyncService;
}