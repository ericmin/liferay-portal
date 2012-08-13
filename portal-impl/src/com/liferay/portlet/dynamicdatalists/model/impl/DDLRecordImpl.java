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

package com.liferay.portlet.dynamicdatalists.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DDLRecordImpl extends DDLRecordBaseImpl {

	public DDLRecordImpl() {
	}

	public Field getField(String fieldName) throws PortalException {
		Fields fields = getFields();

		return fields.get(fieldName);
	}

	public Serializable getFieldDataType(String fieldName)
		throws PortalException, SystemException {

		DDLRecordSet recordSet = getRecordSet();

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		return ddmStructure.getFieldDataType(fieldName);
	}

	public Fields getFields() throws PortalException {
		return StorageEngineUtil.getFields(getDDMStorageId());
	}

	public Serializable getFieldType(String fieldName) throws Exception {
		DDLRecordSet recordSet = getRecordSet();

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		return ddmStructure.getFieldType(fieldName);
	}

	public Serializable getFieldValue(String fieldName) throws PortalException {
		Field field = getField(fieldName);

		return field.getValue();
	}

	public DDLRecordVersion getLatestRecordVersion()
		throws PortalException, SystemException {

		return DDLRecordLocalServiceUtil.getLatestRecordVersion(getRecordId());
	}

	public DDLRecordSet getRecordSet() throws PortalException, SystemException {
		return DDLRecordSetLocalServiceUtil.getRecordSet(getRecordSetId());
	}

	public DDLRecordVersion getRecordVersion()
		throws PortalException, SystemException {

		return getRecordVersion(getVersion());
	}

	public DDLRecordVersion getRecordVersion(String version)
		throws PortalException, SystemException {

		return DDLRecordLocalServiceUtil.getRecordVersion(
			getRecordId(), version);
	}

	public int getStatus() throws PortalException, SystemException {
		DDLRecordVersion recordVersion = getRecordVersion();

		return recordVersion.getStatus();
	}

}