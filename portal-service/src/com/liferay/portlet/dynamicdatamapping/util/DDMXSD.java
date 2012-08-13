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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 */
public interface DDMXSD {

	public String getHTML(
			PageContext pageContext, DDMStructure ddmStructure, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, DDMTemplate ddmTemplate, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String namespace, Locale locale)
		throws Exception;

	public String getHTML(PageContext pageContext, String xml, Locale locale)
		throws Exception;

	public JSONArray getJSONArray(Document document) throws JSONException;

	public JSONArray getJSONArray(Element element) throws JSONException;

	public JSONArray getJSONArray(String xml)
		throws DocumentException, JSONException;

}