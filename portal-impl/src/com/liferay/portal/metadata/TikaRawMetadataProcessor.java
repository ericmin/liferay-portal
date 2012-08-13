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

package com.liferay.portal.metadata;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.DummyWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.WriteOutContentHandler;

import org.xml.sax.ContentHandler;

/**
 * @author Miguel Pastor
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
public class TikaRawMetadataProcessor extends XugglerRawMetadataProcessor {

	public void setParser(Parser parser) {
		_parser = parser;
	}

	protected Metadata extractMetadata(
			InputStream inputStream, Metadata metadata)
		throws IOException {

		if (metadata == null) {
			metadata = new Metadata();
		}

		ParseContext parserContext = new ParseContext();

		parserContext.set(Parser.class, _parser);

		ContentHandler contentHandler = new WriteOutContentHandler(
			new DummyWriter());

		try {
			_parser.parse(inputStream, contentHandler, metadata, parserContext);
		}
		catch (Exception e) {
			_log.error("Unable to parse", e);

			throw new IOException(e.getMessage());
		}

		// Remove potential security risks

		metadata.remove(XMPDM.ABS_PEAK_AUDIO_FILE_PATH.getName());
		metadata.remove(XMPDM.RELATIVE_PEAK_AUDIO_FILE_PATH.getName());

		return metadata;
	}

	@Override
	protected Metadata extractMetadata(
			String extension, String mimeType, File file)
		throws SystemException {

		Metadata metadata = super.extractMetadata(extension, mimeType, file);

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);

			return extractMetadata(inputStream, metadata);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	@Override
	protected Metadata extractMetadata(
			String extension, String mimeType, InputStream inputStream)
		throws SystemException {

		Metadata metadata = super.extractMetadata(
			extension, mimeType, inputStream);

		try {
			return extractMetadata(inputStream, metadata);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TikaRawMetadataProcessor.class);

	private Parser _parser;

}