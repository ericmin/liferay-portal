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
 * This class is a wrapper for {@link ListTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ListTypeService
 * @generated
 */
public class ListTypeServiceWrapper implements ListTypeService,
	ServiceWrapper<ListTypeService> {
	public ListTypeServiceWrapper(ListTypeService listTypeService) {
		_listTypeService = listTypeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _listTypeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_listTypeService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.ListType getListType(int listTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _listTypeService.getListType(listTypeId);
	}

	public java.util.List<com.liferay.portal.model.ListType> getListTypes(
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _listTypeService.getListTypes(type);
	}

	public void validate(int listTypeId, long classNameId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_listTypeService.validate(listTypeId, classNameId, type);
	}

	public void validate(int listTypeId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_listTypeService.validate(listTypeId, type);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ListTypeService getWrappedListTypeService() {
		return _listTypeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedListTypeService(ListTypeService listTypeService) {
		_listTypeService = listTypeService;
	}

	public ListTypeService getWrappedService() {
		return _listTypeService;
	}

	public void setWrappedService(ListTypeService listTypeService) {
		_listTypeService = listTypeService;
	}

	private ListTypeService _listTypeService;
}