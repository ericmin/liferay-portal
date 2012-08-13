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

package com.liferay.portlet.dynamicdatalists.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DDLRecordSetService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DDLRecordSetService
 * @generated
 */
public class DDLRecordSetServiceWrapper implements DDLRecordSetService,
	ServiceWrapper<DDLRecordSetService> {
	public DDLRecordSetServiceWrapper(DDLRecordSetService ddlRecordSetService) {
		_ddlRecordSetService = ddlRecordSetService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ddlRecordSetService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ddlRecordSetService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet addRecordSet(
		long groupId, long ddmStructureId, java.lang.String recordSetKey,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int minDisplayRows, int scope,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.addRecordSet(groupId, ddmStructureId,
			recordSetKey, nameMap, descriptionMap, minDisplayRows, scope,
			serviceContext);
	}

	public void deleteRecordSet(long recordSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ddlRecordSetService.deleteRecordSet(recordSetId);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet getRecordSet(
		long recordSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.getRecordSet(recordSetId);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet updateMinDisplayRows(
		long recordSetId, int minDisplayRows,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.updateMinDisplayRows(recordSetId,
			minDisplayRows, serviceContext);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet updateRecordSet(
		long recordSetId, long ddmStructureId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int minDisplayRows,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.updateRecordSet(recordSetId,
			ddmStructureId, nameMap, descriptionMap, minDisplayRows,
			serviceContext);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet updateRecordSet(
		long groupId, long ddmStructureId, java.lang.String recordSetKey,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int minDisplayRows,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordSetService.updateRecordSet(groupId, ddmStructureId,
			recordSetKey, nameMap, descriptionMap, minDisplayRows,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DDLRecordSetService getWrappedDDLRecordSetService() {
		return _ddlRecordSetService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDDLRecordSetService(
		DDLRecordSetService ddlRecordSetService) {
		_ddlRecordSetService = ddlRecordSetService;
	}

	public DDLRecordSetService getWrappedService() {
		return _ddlRecordSetService;
	}

	public void setWrappedService(DDLRecordSetService ddlRecordSetService) {
		_ddlRecordSetService = ddlRecordSetService;
	}

	private DDLRecordSetService _ddlRecordSetService;
}