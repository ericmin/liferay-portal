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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.xml.SAXReaderImpl;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Manuel de la Peña
 */
@PrepareForTest({DDMXMLUtil.class, SAXReaderUtil.class})
@RunWith(PowerMockRunner.class)
public class DDMXMLImplTest extends PowerMockito {

	@Before
	public void setUp() {
		spy(SAXReaderUtil.class);

		when(
			SAXReaderUtil.getSAXReader()
		).thenReturn(
			_saxReader
		);

		spy(DDMXMLUtil.class);

		when(
			DDMXMLUtil.getDDMXML()
		).thenReturn(
			_ddmXML
		);
	}

	@After
	public void tearDown() {
		verifyStatic();
	}

	@Test
	public void testUpdateContentDefaultLocale() {
		updateContentDefaultLocale("dynamic-data-mapping-structures.xml", true);
	}

	@Test
	public void testUpdateContentDefaultLocaleWrongFormat() {
		updateContentDefaultLocale(
			"dynamic-data-mapping-structures-wrong-format.xml", false);
	}

	protected boolean checkElementLocale(Element element, String newLocaleId) {
		List<Node> nodes = element.selectNodes("dynamic-element");

		for (Node node : nodes) {
			Element metadataElement = (Element)node.selectSingleNode(
				"meta-data[@locale='" + newLocaleId + "']");

			if (metadataElement == null) {
				return false;
			}

			if (!checkElementLocale((Element)node, newLocaleId)) {
				return false;
			}
		}

		return true;
	}

	protected boolean checkLocale(Element rootElement, String newLocaleId) {
		Attribute avaliableLocalesAttribute = rootElement.attribute(
			"available-locales");

		String avaliableLocalesAttributeValue =
			avaliableLocalesAttribute.getValue();

		if (!avaliableLocalesAttributeValue.contains(newLocaleId)) {
			return false;
		}

		Attribute defaultLocaleAttribute = rootElement.attribute(
			"default-locale");

		if (!newLocaleId.equals(defaultLocaleAttribute.getValue())) {
			return false;
		}

		return checkElementLocale(rootElement, newLocaleId);
	}

	protected String readXML(String fileName) throws IOException {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		return StringUtil.read(inputStream);
	}

	protected void updateContentDefaultLocale(
		String fileName, boolean expectedResult) {

		try {
			String xml = readXML(fileName);

			Document document = SAXReaderUtil.read(xml);

			List<Node> structureNodes = document.selectNodes("//structure");

			for (Node structureNode : structureNodes) {
				String structureXML = structureNode.asXML();

				Document structureDocument = SAXReaderUtil.read(structureXML);

				Element rootElement =
					(Element)structureDocument.selectSingleNode(
						"/structure/root");

				Attribute defaultLocale = rootElement.attribute(
					"default-locale");

				Locale contentDefaultLocale = LocaleUtil.fromLanguageId(
					defaultLocale.getValue());

				Locale availableDefaultLocale = LocaleUtil.fromLanguageId(
					"es_ES");

				String rootXML = rootElement.asXML();

				structureXML = DDMXMLUtil.updateXMLDefaultLocale(
					rootXML, contentDefaultLocale, availableDefaultLocale);

				Document updatedXMLDocument = SAXReaderUtil.read(structureXML);

				rootElement = updatedXMLDocument.getRootElement();

				if (checkLocale(
						rootElement,
						LocaleUtil.toLanguageId(availableDefaultLocale))) {

					Assert.assertTrue(expectedResult);

					return;
				}
			}

			Assert.assertFalse(expectedResult);
		}
		catch (DocumentException de) {
			Assert.fail(de.getMessage());
		}
		catch (IOException ioe) {
			Assert.fail(ioe.getMessage());
		}
	}

	private DDMXMLImpl _ddmXML = new DDMXMLImpl();

	private SAXReaderImpl _saxReader = new SAXReaderImpl();

}