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

package com.liferay.portlet.dynamicdatalists.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;

import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 */
public interface DDL {

	public void addAllReservedEls(
		Element rootElement, Map<String, String> tokens,
		DDLRecordSet recordSet);

	public JSONObject getRecordJSONObject(DDLRecord record) throws Exception;

	public JSONObject getRecordJSONObject(
			DDLRecord record, boolean latestRecordVersion)
		throws Exception;

	public JSONArray getRecordSetJSONArray(DDLRecordSet recordSet)
		throws Exception;

	public JSONArray getRecordsJSONArray(DDLRecordSet recordSet)
		throws Exception;

	public JSONArray getRecordsJSONArray(List<DDLRecord> records)
		throws Exception;

	public JSONArray getRecordsJSONArray(
			List<DDLRecord> records, boolean latestRecordVersion)
		throws Exception;

	public String getTemplateContent(
			long ddmTemplateId, DDLRecordSet recordSet,
			ThemeDisplay themeDisplay, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception;

	public void sendRecordFileUpload(
			HttpServletRequest request, HttpServletResponse response,
			DDLRecord record, String fieldName)
		throws Exception;

	public void sendRecordFileUpload(
			HttpServletRequest request, HttpServletResponse response,
			long recordId, String fieldName)
		throws Exception;

	public DDLRecord updateRecord(
			long recordId, long recordSetId, boolean mergeFields,
			boolean checkPermission, ServiceContext serviceContext)
		throws Exception;

	public DDLRecord updateRecord(
			long recordId, long recordSetId, boolean mergeFields,
			ServiceContext serviceContext)
		throws Exception;

	public String uploadRecordFieldFile(
			DDLRecord record, String fieldName, ServiceContext serviceContext)
		throws Exception;

}