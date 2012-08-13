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

package com.liferay.portlet.dynamicdatalists.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.base.DDLRecordSetServiceBaseImpl;
import com.liferay.portlet.dynamicdatalists.service.permission.DDLPermission;
import com.liferay.portlet.dynamicdatalists.service.permission.DDLRecordSetPermission;

import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class DDLRecordSetServiceImpl extends DDLRecordSetServiceBaseImpl {

	public DDLRecordSet addRecordSet(
			long groupId, long ddmStructureId, String recordSetKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int minDisplayRows, int scope, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_RECORD_SET);

		return ddlRecordSetLocalService.addRecordSet(
			getUserId(), groupId, ddmStructureId, recordSetKey, nameMap,
			descriptionMap, minDisplayRows, scope, serviceContext);
	}

	public void deleteRecordSet(long recordSetId)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), recordSetId, ActionKeys.DELETE);

		ddlRecordSetLocalService.deleteRecordSet(recordSetId);
	}

	public DDLRecordSet getRecordSet(long recordSetId)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), recordSetId, ActionKeys.VIEW);

		return ddlRecordSetLocalService.getRecordSet(recordSetId);
	}

	public DDLRecordSet updateMinDisplayRows(
			long recordSetId, int minDisplayRows, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), recordSetId, ActionKeys.UPDATE);

		return ddlRecordSetLocalService.updateMinDisplayRows(
			recordSetId, minDisplayRows, serviceContext);
	}

	public DDLRecordSet updateRecordSet(
			long recordSetId, long ddmStructureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int minDisplayRows,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), recordSetId, ActionKeys.UPDATE);

		return ddlRecordSetLocalService.updateRecordSet(
			recordSetId, ddmStructureId, nameMap, descriptionMap,
			minDisplayRows, serviceContext);
	}

	public DDLRecordSet updateRecordSet(
			long groupId, long ddmStructureId, String recordSetKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int minDisplayRows, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), groupId, recordSetKey, ActionKeys.UPDATE);

		return ddlRecordSetLocalService.updateRecordSet(
			groupId, ddmStructureId, recordSetKey, nameMap, descriptionMap,
			minDisplayRows, serviceContext);
	}

}