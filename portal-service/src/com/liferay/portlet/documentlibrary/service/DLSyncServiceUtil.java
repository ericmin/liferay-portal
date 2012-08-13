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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the d l sync remote service. This utility wraps {@link com.liferay.portlet.documentlibrary.service.impl.DLSyncServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLSyncService
 * @see com.liferay.portlet.documentlibrary.service.base.DLSyncServiceBaseImpl
 * @see com.liferay.portlet.documentlibrary.service.impl.DLSyncServiceImpl
 * @generated
 */
public class DLSyncServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.documentlibrary.service.impl.DLSyncServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.portlet.documentlibrary.model.DLSyncUpdate getDLSyncUpdate(
		long companyId, long repositoryId, java.util.Date lastAccessDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getDLSyncUpdate(companyId, repositoryId, lastAccessDate);
	}

	public static java.io.InputStream getFileDeltaAsStream(long fileEntryId,
		java.lang.String sourceVersion, java.lang.String destinationVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getFileDeltaAsStream(fileEntryId, sourceVersion,
			destinationVersion);
	}

	public static com.liferay.portal.kernel.repository.model.FileEntry updateFileEntry(
		long fileEntryId, java.lang.String sourceFileName,
		java.lang.String mimeType, java.lang.String title,
		java.lang.String description, java.lang.String changeLog,
		boolean majorVersion, java.io.InputStream deltaInputStream, long size,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFileEntry(fileEntryId, sourceFileName, mimeType,
			title, description, changeLog, majorVersion, deltaInputStream,
			size, serviceContext);
	}

	public static DLSyncService getService() {
		if (_service == null) {
			_service = (DLSyncService)PortalBeanLocatorUtil.locate(DLSyncService.class.getName());

			ReferenceRegistry.registerReference(DLSyncServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(DLSyncService service) {
	}

	private static DLSyncService _service;
}