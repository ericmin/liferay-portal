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
 * This class is a wrapper for {@link CMISRepositoryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CMISRepositoryLocalService
 * @generated
 */
public class CMISRepositoryLocalServiceWrapper
	implements CMISRepositoryLocalService,
		ServiceWrapper<CMISRepositoryLocalService> {
	public CMISRepositoryLocalServiceWrapper(
		CMISRepositoryLocalService cmisRepositoryLocalService) {
		_cmisRepositoryLocalService = cmisRepositoryLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _cmisRepositoryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_cmisRepositoryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object getSession(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.getSession(repositoryId);
	}

	public com.liferay.portal.kernel.repository.model.FileEntry toFileEntry(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.toFileEntry(repositoryId, object);
	}

	public com.liferay.portal.kernel.repository.model.FileVersion toFileVersion(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.toFileVersion(repositoryId, object);
	}

	public com.liferay.portal.kernel.repository.model.Folder toFolder(
		long repositoryId, java.lang.Object object)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _cmisRepositoryLocalService.toFolder(repositoryId, object);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CMISRepositoryLocalService getWrappedCMISRepositoryLocalService() {
		return _cmisRepositoryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCMISRepositoryLocalService(
		CMISRepositoryLocalService cmisRepositoryLocalService) {
		_cmisRepositoryLocalService = cmisRepositoryLocalService;
	}

	public CMISRepositoryLocalService getWrappedService() {
		return _cmisRepositoryLocalService;
	}

	public void setWrappedService(
		CMISRepositoryLocalService cmisRepositoryLocalService) {
		_cmisRepositoryLocalService = cmisRepositoryLocalService;
	}

	private CMISRepositoryLocalService _cmisRepositoryLocalService;
}