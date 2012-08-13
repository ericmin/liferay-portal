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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.util.InitUtil;
import com.liferay.util.ant.Wsdl2JavaTask;

import java.io.File;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalClientBuilder {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		if (args.length == 4) {
			new PortalClientBuilder(args[0], args[1], args[2], args[3]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public PortalClientBuilder(
		String fileName, String outputDir, String mappingFile, String url) {

		try {
			Document document = SAXReaderUtil.read(new File(fileName));

			Element rootElement = document.getRootElement();

			Iterator<Element> itr = rootElement.elements("service").iterator();

			while (itr.hasNext()) {
				Element serviceElement = itr.next();

				String serviceName = serviceElement.attributeValue("name");

				if (serviceName.startsWith("Plugin_") &&
					!FileUtil.exists(mappingFile)) {

					_writePluginMappingFile(
						mappingFile, serviceElement, serviceName);
				}

				if (serviceName.startsWith("Plugin_") ||
					serviceName.startsWith("Portal_") ||
					serviceName.startsWith("Portlet_")) {

					Wsdl2JavaTask.generateJava(
						url + "/" + serviceName + "?wsdl", outputDir,
						mappingFile);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		File testNamespace = new File(outputDir + "/com/liferay/portal");

		if (testNamespace.exists()) {
			throw new RuntimeException(
				"Please update " + mappingFile + " to namespace " +
					"com.liferay.portal to com.liferay.client.soap.portal");
		}
	}

	private void _writePluginMappingFile(
			String mappingFile, Element serviceElement, String serviceName)
		throws Exception {

		String wsdlTargetNamespace = null;

		List<Element> parameterElements = serviceElement.elements("parameter");

		for (Element parameterElement : parameterElements) {
			String parameterName = parameterElement.attributeValue("name");

			if (parameterName.equals("wsdlTargetNamespace")) {
				wsdlTargetNamespace = parameterElement.attributeValue("value");

				break;
			}
		}

		int pos = wsdlTargetNamespace.indexOf(".service.");

		String soapNamespace = wsdlTargetNamespace.substring(pos + 9);

		String[] soapNamespaceArray = StringUtil.split(
			soapNamespace, CharPool.PERIOD);

		ArrayUtil.reverse(soapNamespaceArray);

		soapNamespace = StringUtil.merge(soapNamespaceArray, StringPool.PERIOD);

		pos = soapNamespace.lastIndexOf(StringPool.PERIOD);

		soapNamespace =
			soapNamespace.substring(0, pos) + ".client.soap" +
				soapNamespace.substring(pos);

		StringBundler sb = new StringBundler(12);

		sb.append("com.liferay.client.soap.portal.kernel.util=");
		sb.append("http://util.kernel.portal.liferay.com\n");

		sb.append("com.liferay.client.soap.portal.model=");
		sb.append("http://model.portal.liferay.com\n");

		sb.append("com.liferay.client.soap.portal.service=");
		sb.append("http://service.portal.liferay.com\n");

		sb.append(soapNamespace);
		sb.append(".model=");
		sb.append("http://model.knowledgebase.liferay.com\n");

		sb.append(soapNamespace);
		sb.append(".service.http=");
		sb.append("urn:http.service.knowledgebase.liferay.com\n");

		FileUtil.write(mappingFile, sb.toString());
	}

}