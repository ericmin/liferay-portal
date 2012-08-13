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

package com.liferay.portlet.journal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class LocaleTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		return s;
	}

	@Override
	public String onScript(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		s = StringUtil.replace(s, "@language_id@", _requestedLocale);

		return s;
	}

	@Override
	public String onXml(String s) {
		if (_log.isDebugEnabled()) {
			_log.debug("onXml");
		}

		s = replace(s);

		return s;
	}

	protected void replace(Element root) {
		List<Element> elements = root.elements();

		int listIndex = elements.size() - 1;

		while (listIndex >= 0) {
			Element element = elements.get(listIndex);

			String languageId = element.attributeValue(
				"language-id", getLanguageId());

			if (!languageId.equalsIgnoreCase(getLanguageId())) {
				root.remove(element);
			}
			else {
				replace(element);
			}

			listIndex--;
		}
	}

	protected String replace(String xml) {
		if (xml == null) {
			return xml;
		}

		_requestedLocale = getLanguageId();

		try {
			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			String defaultLanguageId = LocaleUtil.toLanguageId(
				LocaleUtil.getDefault());

			String[] availableLocales = StringUtil.split(
				rootElement.attributeValue(
					"available-locales", defaultLanguageId));

			String defaultLocale = rootElement.attributeValue(
				"default-locale", defaultLanguageId);

			boolean supportedLocale = false;

			for (String availableLocale : availableLocales) {
				if (availableLocale.equalsIgnoreCase(getLanguageId())) {
					supportedLocale = true;

					break;
				}
			}

			if (!supportedLocale) {
				setLanguageId(defaultLocale);
			}

			replace(rootElement);

			xml = DDMXMLUtil.formatXML(document);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return xml;
	}

	private static Log _log = LogFactoryUtil.getLog(
		LocaleTransformerListener.class);

	private String _requestedLocale = StringPool.BLANK;

}