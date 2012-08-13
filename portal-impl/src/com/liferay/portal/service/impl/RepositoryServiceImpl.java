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

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchRepositoryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Repository;
import com.liferay.portal.repository.util.RepositoryFactoryUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.RepositoryServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.service.permission.DLFolderPermission;
import com.liferay.portlet.documentlibrary.service.permission.DLPermission;

/**
 * @author Alexander Chow
 * @author Mika Koivisto
 */
public class RepositoryServiceImpl extends RepositoryServiceBaseImpl {

	public long addRepository(
			long groupId, long classNameId, long parentFolderId, String name,
			String description, String portletId,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_REPOSITORY);

		return repositoryLocalService.addRepository(
			getUserId(), groupId, classNameId, parentFolderId, name,
			description, portletId, typeSettingsProperties, serviceContext);
	}

	public void checkRepository(long repositoryId)
		throws PortalException, SystemException {

		Group group = groupPersistence.fetchByPrimaryKey(repositoryId);

		if (group != null) {
			return;
		}

		try {
			Repository repository = repositoryPersistence.findByPrimaryKey(
				repositoryId);

			DLFolderPermission.check(
				getPermissionChecker(), repository.getGroupId(),
				repository.getDlFolderId(), ActionKeys.VIEW);
		}
		catch (NoSuchRepositoryException nsre) {
			throw new RepositoryException(nsre.getMessage());
		}
	}

	public void deleteRepository(long repositoryId)
		throws PortalException, SystemException {

		Repository repository = repositoryPersistence.findByPrimaryKey(
			repositoryId);

		DLFolderPermission.check(
			getPermissionChecker(), repository.getGroupId(),
			repository.getDlFolderId(), ActionKeys.DELETE);

		repositoryLocalService.deleteRepository(repository.getRepositoryId());
	}

	public LocalRepository getLocalRepositoryImpl(long repositoryId)
		throws PortalException, SystemException {

		checkRepository(repositoryId);

		return repositoryLocalService.getLocalRepositoryImpl(repositoryId);
	}

	public LocalRepository getLocalRepositoryImpl(
			long folderId, long fileEntryId, long fileVersionId)
		throws PortalException, SystemException {

		LocalRepository localRepositoryImpl =
			repositoryLocalService.getLocalRepositoryImpl(
				folderId, fileEntryId, fileVersionId);

		checkRepository(localRepositoryImpl.getRepositoryId());

		return localRepositoryImpl;
	}

	public Repository getRepository(long repositoryId)
		throws PortalException, SystemException {

		return repositoryPersistence.findByPrimaryKey(repositoryId);
	}

	public com.liferay.portal.kernel.repository.Repository getRepositoryImpl(
			long repositoryId)
		throws PortalException, SystemException {

		checkRepository(repositoryId);

		return repositoryLocalService.getRepositoryImpl(repositoryId);
	}

	public com.liferay.portal.kernel.repository.Repository getRepositoryImpl(
			long folderId, long fileEntryId, long fileVersionId)
		throws PortalException, SystemException {

		com.liferay.portal.kernel.repository.Repository repositoryImpl =
			repositoryLocalService.getRepositoryImpl(
				folderId, fileEntryId, fileVersionId);

		checkRepository(repositoryImpl.getRepositoryId());

		return repositoryImpl;
	}

	public String[] getSupportedConfigurations(long classNameId)
		throws SystemException {

		try {
			String repositoryImplClassName = PortalUtil.getClassName(
				classNameId);

			BaseRepository baseRepository = RepositoryFactoryUtil.getInstance(
				repositoryImplClassName);

			return baseRepository.getSupportedConfigurations();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public String[] getSupportedParameters(
			long classNameId, String configuration)
		throws SystemException {

		try {
			String repositoryImplClassName = PortalUtil.getClassName(
				classNameId);

			BaseRepository baseRepository = RepositoryFactoryUtil.getInstance(
				repositoryImplClassName);

			String[] supportedConfigurations =
				baseRepository.getSupportedConfigurations();

			String[][] supportedParameters =
				baseRepository.getSupportedParameters();

			for (int i = 0; i < supportedConfigurations.length; i++) {
				if (supportedConfigurations[i].equals(configuration)) {
					return supportedParameters[i];
				}
			}

			throw new RepositoryException(
				"Configuration not found for repository with class name id " +
					classNameId);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public UnicodeProperties getTypeSettingsProperties(long repositoryId)
		throws PortalException, SystemException {

		checkRepository(repositoryId);

		return repositoryLocalService.getTypeSettingsProperties(repositoryId);
	}

	public void updateRepository(
			long repositoryId, String name, String description)
		throws PortalException, SystemException {

		Repository repository = repositoryPersistence.findByPrimaryKey(
			repositoryId);

		DLFolderPermission.check(
			getPermissionChecker(), repository.getGroupId(),
			repository.getDlFolderId(), ActionKeys.UPDATE);

		repositoryLocalService.updateRepository(
			repositoryId, name, description);
	}

}