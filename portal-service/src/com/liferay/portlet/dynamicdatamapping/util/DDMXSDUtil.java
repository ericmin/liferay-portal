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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 */
public class DDMXSDUtil {

	public static DDMXSD getDDMXSD() {
		PortalRuntimePermission.checkGetBeanProperty(DDMXSDUtil.class);

		return _ddmXSD;
	}

	public static String getHTML(
			PageContext pageContext, DDMStructure ddmStructure, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			pageContext, ddmStructure, fields, namespace, readOnly, locale);
	}

	public static String getHTML(
			PageContext pageContext, DDMTemplate ddmTemplate, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			pageContext, ddmTemplate, fields, namespace, readOnly, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Fields fields, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(pageContext, xml, fields, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String namespace, boolean readOnly, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(
			pageContext, xml, fields, namespace, readOnly, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String namespace, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(pageContext, xml, fields, namespace, locale);
	}

	public static String getHTML(
			PageContext pageContext, String xml, Locale locale)
		throws Exception {

		return getDDMXSD().getHTML(pageContext, xml, locale);
	}

	public static JSONArray getJSONArray(Document document)
		throws JSONException {

		return getDDMXSD().getJSONArray(document);
	}

	public static JSONArray getJSONArray(String xml)
		throws DocumentException, JSONException {

		return getDDMXSD().getJSONArray(xml);
	}

	public void setDDMXSD(DDMXSD ddmXSD) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmXSD = ddmXSD;
	}

	private static DDMXSD _ddmXSD;

}