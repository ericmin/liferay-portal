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
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.NoSuchTemplateException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DDLRecordSetImpl extends DDLRecordSetBaseImpl {

	public DDLRecordSetImpl() {
	}

	public DDMStructure getDDMStructure()
		throws PortalException, SystemException {

		return DDMStructureLocalServiceUtil.getStructure(getDDMStructureId());
	}

	public DDMStructure getDDMStructure(long detailDDMTemplateId)
		throws PortalException, SystemException {

		DDMStructure ddmStructure = getDDMStructure();

		if (detailDDMTemplateId > 0) {
			try {
				DDMTemplate ddmTemplate =
					DDMTemplateLocalServiceUtil.getTemplate(
						detailDDMTemplateId);

				ddmStructure.setXsd(ddmTemplate.getScript());
			}
			catch (NoSuchTemplateException nste) {
			}
		}

		return ddmStructure;
	}

	public List<DDLRecord> getRecords() throws SystemException {
		return DDLRecordLocalServiceUtil.getRecords(getRecordSetId());
	}

	public List<Fields> getRecordsFieldsList()
		throws PortalException, SystemException {

		List<Fields> fieldsList = new ArrayList<Fields>();

		for (DDLRecord record : getRecords()) {
			fieldsList.add(record.getFields());
		}

		return fieldsList;
	}

}