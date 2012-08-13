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

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.util.xml.XMLFormatter;

import java.io.IOException;

import java.util.Locale;

/**
 * @author Bruno Basto
 * @author Brian Wing Shun Chan
 */
public class DDMXMLImpl implements DDMXML {

	public String formatXML(Document document) throws IOException {
		return document.formattedString(_XML_INDENT);
	}

	public String formatXML(String xml) throws DocumentException, IOException {

		// This is only supposed to format your xml, however, it will also
		// unwantingly change &#169; and other characters like it into their
		// respective readable versions

		xml = StringUtil.replace(xml, "&#", "[$SPECIAL_CHARACTER$]");

		try {
			xml = XMLFormatter.toString(xml, _XML_INDENT);
		}
		catch (org.dom4j.DocumentException de) {
			throw new DocumentException(de.getMessage());
		}

		xml = StringUtil.replace(xml, "[$SPECIAL_CHARACTER$]", "&#");

		return xml;
	}

	public String updateXMLDefaultLocale(
			String xml, Locale contentDefaultLocale,
			Locale contentNewDefaultLocale)
		throws DocumentException, IOException {

		if (LocaleUtil.equals(contentDefaultLocale, contentNewDefaultLocale)) {
			return xml;
		}

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		Attribute availableLocalesAttribute = rootElement.attribute(
			_AVAILABLE_LOCALES);

		String contentNewDefaultLanguageId = LocaleUtil.toLanguageId(
			contentNewDefaultLocale);

		String availableLocalesAttributeValue =
			availableLocalesAttribute.getValue();

		if (!availableLocalesAttributeValue.contains(
				contentNewDefaultLanguageId)) {

			StringBundler sb = new StringBundler(3);

			sb.append(availableLocalesAttribute.getValue());
			sb.append(StringPool.COMMA);
			sb.append(contentNewDefaultLanguageId);

			availableLocalesAttribute.setValue(sb.toString());
		}

		Attribute defaultLocaleAttribute = rootElement.attribute(
			_DEFAULT_LOCALE);

		defaultLocaleAttribute.setValue(contentNewDefaultLanguageId);

		fixElementsDefaultLocale(
			rootElement, contentDefaultLocale, contentNewDefaultLocale);

		return document.formattedString();
	}

	protected void fixElementsDefaultLocale(
		Element element, Locale contentDefaultLocale,
		Locale contentNewDefaultLocale) {

		for (Element dynamicElementElement :
				element.elements(_DYNAMIC_ELEMENT)) {

			Element importMetaDataElement =
				(Element)dynamicElementElement.selectSingleNode(
					"meta-data[@locale='" + contentNewDefaultLocale.toString() +
						"']");

			if (importMetaDataElement == null) {
				Element metaDataElement =
					(Element)dynamicElementElement.selectSingleNode(
						"meta-data[@locale='" +
							contentDefaultLocale.toString() + "']");

				Element copiedMetadataElement = metaDataElement.createCopy();

				Attribute localeAttribute = copiedMetadataElement.attribute(
					_LOCALE);

				String contentNewDefaultLanguageId = LocaleUtil.toLanguageId(
					contentNewDefaultLocale);

				localeAttribute.setValue(contentNewDefaultLanguageId);

				dynamicElementElement.add(copiedMetadataElement);
			}

			fixElementsDefaultLocale(
				dynamicElementElement, contentDefaultLocale,
				contentNewDefaultLocale);
		}
	}

	private static final String _AVAILABLE_LOCALES = "available-locales";

	private static final String _DEFAULT_LOCALE = "default-locale";

	private static final String _DYNAMIC_ELEMENT = "dynamic-element";

	private static final String _LOCALE = "locale";

	private static final String _XML_INDENT = "  ";

}