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

package com.liferay.util.xml;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.xml.descriptor.XMLDescriptor;

import java.io.File;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Jorge Ferrer
 */
public class XMLMergerRunner {

	public static void main(String[] args)
		throws ClassNotFoundException, DocumentException,
			   IllegalAccessException, InstantiationException, IOException {

		if ((args != null) && (args.length == 4)) {
			XMLMergerRunner runner = new XMLMergerRunner(args[3]);

			runner.mergeAndSave(args[0], args[1], args[2]);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public XMLMergerRunner(String descriptorClassName) {
		if (Validator.isNotNull(descriptorClassName)) {
			_descriptorClassName = descriptorClassName;
		}
	}

	public void mergeAndSave(File masterFile, File slaveFile, File mergedFile)
		throws ClassNotFoundException, DocumentException,
			   IllegalAccessException, InstantiationException, IOException {

		String xml1 = FileUtil.read(masterFile);
		String xml2 = FileUtil.read(slaveFile);

		String mergedXml = _merge(xml1, xml2);

		FileUtil.write(mergedFile, mergedXml);
	}

	public void mergeAndSave(
			String masterFile, String slaveFile, String mergedFile)
		throws ClassNotFoundException, DocumentException,
			   IllegalAccessException, InstantiationException, IOException {

		mergeAndSave(
			new File(masterFile), new File(slaveFile), new File(mergedFile));
	}

	private String _documentToString(Document doc, String docType)
		throws IOException {

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		OutputFormat format = OutputFormat.createPrettyPrint();

		format.setIndent("\t");
		format.setLineSeparator("\n");

		XMLWriter writer = new XMLWriter(unsyncByteArrayOutputStream, format);

		writer.write(doc);

		String xml = unsyncByteArrayOutputStream.toString();

		int pos = xml.indexOf("<?");

		String header = xml.substring(pos, xml.indexOf("?>", pos) + 2);

		xml = StringUtil.replace(xml, header, "");
		xml = header + "\n" + docType + "\n" + xml;

		return xml;
	}

	private String _merge(String masterXml, String slaveXml)
		throws ClassNotFoundException, DocumentException,
			   IllegalAccessException, InstantiationException, IOException {

		int pos = masterXml.indexOf("<!DOCTYPE");

		String masterDoctype = "";

		if (pos >= 0) {
			masterDoctype = masterXml.substring(
				pos, masterXml.indexOf(">", pos) + 1);
			masterXml = StringUtil.replace(masterXml, masterDoctype, "");
		}

		pos = slaveXml.indexOf("<!DOCTYPE");

		String slaveDoctype = "";

		if (pos >= 0) {
			slaveDoctype = slaveXml.substring(
				pos, slaveXml.indexOf(">", pos) + 1);
			slaveXml = StringUtil.replace(slaveXml, slaveDoctype, "");
		}

		String doctype = null;

		if (Validator.isNotNull(masterDoctype)) {
			doctype = masterDoctype;
		}
		else {
			doctype = slaveDoctype;
		}

		SAXReader reader = new SAXReader();

		Document masterDoc = reader.read(new UnsyncStringReader(masterXml));
		Document slaveDoc = reader.read(new UnsyncStringReader(slaveXml));

		XMLDescriptor descriptor = null;

		if (_descriptorClassName.equals(_AUTO_DESCRIPTOR)) {
			descriptor = XMLTypeDetector.determineType(doctype, masterDoc);
		}
		else {
			descriptor = (XMLDescriptor)Class.forName(
				_descriptorClassName).newInstance();
		}

		XMLMerger merger = new XMLMerger(descriptor);

		Document mergedDoc = merger.merge(masterDoc, slaveDoc);

		return _documentToString(mergedDoc, doctype);
	}

	private static final String _AUTO_DESCRIPTOR = "auto";

	private String _descriptorClassName = _AUTO_DESCRIPTOR;

}