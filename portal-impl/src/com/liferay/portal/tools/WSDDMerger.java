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

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.util.InitUtil;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Brian Wing Shun Chan
 */
public class WSDDMerger {

	public static void main(String[] args) {
		InitUtil.initWithSpring();

		new WSDDMerger(args[0], args[1]);
	}

	public static void merge(String source, String destination)
		throws DocumentException, IOException {

		// Source

		File sourceFile = new File(source);

		Document doc = SAXReaderUtil.read(sourceFile);

		Element root = doc.getRootElement();

		List<Element> sourceServices = root.elements("service");

		if (sourceServices.size() == 0) {
			return;
		}

		// Destination

		File destinationFile = new File(destination);

		doc = SAXReaderUtil.read(destinationFile);

		root = doc.getRootElement();

		Map<String, Element> servicesMap = new TreeMap<String, Element>();

		Iterator<Element> itr = root.elements("service").iterator();

		while (itr.hasNext()) {
			Element service = itr.next();

			String name = service.attributeValue("name");

			servicesMap.put(name, service);

			service.detach();
		}

		itr = sourceServices.iterator();

		while (itr.hasNext()) {
			Element service = itr.next();

			String name = service.attributeValue("name");

			servicesMap.put(name, service);

			service.detach();
		}

		for (Map.Entry<String, Element> entry : servicesMap.entrySet()) {
			Element service = entry.getValue();

			root.add(service);
		}

		String content = doc.formattedString();

		content = StringUtil.replace(content, "\"/>", "\" />");

		FileUtil.write(destination, content, true);
	}

	public WSDDMerger(String source, String destination) {
		try {
			merge(source, destination);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}