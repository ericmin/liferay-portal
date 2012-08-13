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
 * This class is a wrapper for {@link RepositoryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       RepositoryService
 * @generated
 */
public class RepositoryServiceWrapper implements RepositoryService,
	ServiceWrapper<RepositoryService> {
	public RepositoryServiceWrapper(RepositoryService repositoryService) {
		_repositoryService = repositoryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _repositoryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_repositoryService.setBeanIdentifier(beanIdentifier);
	}

	public long addRepository(long groupId, long classNameId,
		long parentFolderId, java.lang.String name,
		java.lang.String description, java.lang.String portletId,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.addRepository(groupId, classNameId,
			parentFolderId, name, description, portletId,
			typeSettingsProperties, serviceContext);
	}

	public void checkRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_repositoryService.checkRepository(repositoryId);
	}

	public void deleteRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_repositoryService.deleteRepository(repositoryId);
	}

	public com.liferay.portal.kernel.repository.LocalRepository getLocalRepositoryImpl(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getLocalRepositoryImpl(repositoryId);
	}

	public com.liferay.portal.kernel.repository.LocalRepository getLocalRepositoryImpl(
		long folderId, long fileEntryId, long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getLocalRepositoryImpl(folderId, fileEntryId,
			fileVersionId);
	}

	public com.liferay.portal.model.Repository getRepository(long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getRepository(repositoryId);
	}

	public com.liferay.portal.kernel.repository.Repository getRepositoryImpl(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getRepositoryImpl(repositoryId);
	}

	public com.liferay.portal.kernel.repository.Repository getRepositoryImpl(
		long folderId, long fileEntryId, long fileVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getRepositoryImpl(folderId, fileEntryId,
			fileVersionId);
	}

	public java.lang.String[] getSupportedConfigurations(long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getSupportedConfigurations(classNameId);
	}

	public java.lang.String[] getSupportedParameters(long classNameId,
		java.lang.String configuration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getSupportedParameters(classNameId,
			configuration);
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties(
		long repositoryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _repositoryService.getTypeSettingsProperties(repositoryId);
	}

	public void updateRepository(long repositoryId, java.lang.String name,
		java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_repositoryService.updateRepository(repositoryId, name, description);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public RepositoryService getWrappedRepositoryService() {
		return _repositoryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedRepositoryService(RepositoryService repositoryService) {
		_repositoryService = repositoryService;
	}

	public RepositoryService getWrappedService() {
		return _repositoryService;
	}

	public void setWrappedService(RepositoryService repositoryService) {
		_repositoryService = repositoryService;
	}

	private RepositoryService _repositoryService;
}