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
 * This class is a wrapper for {@link DLFileEntryTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DLFileEntryTypeService
 * @generated
 */
public class DLFileEntryTypeServiceWrapper implements DLFileEntryTypeService,
	ServiceWrapper<DLFileEntryTypeService> {
	public DLFileEntryTypeServiceWrapper(
		DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _dlFileEntryTypeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dlFileEntryTypeService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntryType addFileEntryType(
		long groupId, java.lang.String name, java.lang.String description,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.addFileEntryType(groupId, name,
			description, ddmStructureIds, serviceContext);
	}

	public void deleteFileEntryType(long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.deleteFileEntryType(fileEntryTypeId);
	}

	public com.liferay.portlet.documentlibrary.model.DLFileEntryType getFileEntryType(
		long fileEntryTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryType(fileEntryTypeId);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> getFileEntryTypes(
		long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypes(groupIds);
	}

	public int getFileEntryTypesCount(long[] groupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.getFileEntryTypesCount(groupIds);
	}

	public java.util.List<com.liferay.portlet.documentlibrary.model.DLFileEntryType> search(
		long companyId, long[] groupIds, java.lang.String keywords,
		boolean includeBasicFileEntryType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.search(companyId, groupIds, keywords,
			includeBasicFileEntryType, start, end, orderByComparator);
	}

	public int searchCount(long companyId, long[] groupIds,
		java.lang.String keywords, boolean includeBasicFileEntryType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dlFileEntryTypeService.searchCount(companyId, groupIds,
			keywords, includeBasicFileEntryType);
	}

	public void updateFileEntryType(long fileEntryTypeId,
		java.lang.String name, java.lang.String description,
		long[] ddmStructureIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_dlFileEntryTypeService.updateFileEntryType(fileEntryTypeId, name,
			description, ddmStructureIds, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DLFileEntryTypeService getWrappedDLFileEntryTypeService() {
		return _dlFileEntryTypeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDLFileEntryTypeService(
		DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	public DLFileEntryTypeService getWrappedService() {
		return _dlFileEntryTypeService;
	}

	public void setWrappedService(DLFileEntryTypeService dlFileEntryTypeService) {
		_dlFileEntryTypeService = dlFileEntryTypeService;
	}

	private DLFileEntryTypeService _dlFileEntryTypeService;
}