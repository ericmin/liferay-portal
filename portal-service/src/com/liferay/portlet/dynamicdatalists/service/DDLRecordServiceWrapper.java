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
 * This class is a wrapper for {@link DDLRecordService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DDLRecordService
 * @generated
 */
public class DDLRecordServiceWrapper implements DDLRecordService,
	ServiceWrapper<DDLRecordService> {
	public DDLRecordServiceWrapper(DDLRecordService ddlRecordService) {
		_ddlRecordService = ddlRecordService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ddlRecordService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ddlRecordService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecord addRecord(
		long groupId, long recordSetId, int displayIndex,
		com.liferay.portlet.dynamicdatamapping.storage.Fields fields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordService.addRecord(groupId, recordSetId, displayIndex,
			fields, serviceContext);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecord addRecord(
		long groupId, long recordSetId, int displayIndex,
		java.util.Map<java.lang.String, java.io.Serializable> fieldsMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordService.addRecord(groupId, recordSetId, displayIndex,
			fieldsMap, serviceContext);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecord getRecord(
		long recordId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordService.getRecord(recordId);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecord updateRecord(
		long recordId, boolean majorVersion, int displayIndex,
		com.liferay.portlet.dynamicdatamapping.storage.Fields fields,
		boolean mergeFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordService.updateRecord(recordId, majorVersion,
			displayIndex, fields, mergeFields, serviceContext);
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecord updateRecord(
		long recordId, int displayIndex,
		java.util.Map<java.lang.String, java.io.Serializable> fieldsMap,
		boolean mergeFields,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ddlRecordService.updateRecord(recordId, displayIndex,
			fieldsMap, mergeFields, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DDLRecordService getWrappedDDLRecordService() {
		return _ddlRecordService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDDLRecordService(DDLRecordService ddlRecordService) {
		_ddlRecordService = ddlRecordService;
	}

	public DDLRecordService getWrappedService() {
		return _ddlRecordService;
	}

	public void setWrappedService(DDLRecordService ddlRecordService) {
		_ddlRecordService = ddlRecordService;
	}

	private DDLRecordService _ddlRecordService;
}