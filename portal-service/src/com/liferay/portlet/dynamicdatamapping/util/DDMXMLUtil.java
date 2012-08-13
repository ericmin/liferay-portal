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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;

import java.io.IOException;

import java.util.Locale;

/**
 * @author Bruno Basto
 * @author Brian Wing Shun Chan
 */
public class DDMXMLUtil {

	public static String formatXML(Document document) throws IOException {
		return getDDMXML().formatXML(document);
	}

	public static String formatXML(String xml)
		throws DocumentException, IOException {

		return getDDMXML().formatXML(xml);
	}

	public static DDMXML getDDMXML() {
		PortalRuntimePermission.checkGetBeanProperty(DDMXMLUtil.class);

		return _ddmXML;
	}

	public static String updateXMLDefaultLocale(
			String xml, Locale contentDefaultLocale,
			Locale contentNewDefaultLocale)
		throws DocumentException, IOException {

		return getDDMXML().updateXMLDefaultLocale(
			xml, contentDefaultLocale, contentNewDefaultLocale);
	}

	public void setDDMXML(DDMXML ddmXML) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ddmXML = ddmXML;
	}

	private static DDMXML _ddmXML;

}