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

package com.liferay.portal.servlet.filters.gzip;

import com.liferay.portal.util.PropsValues;

import java.io.IOException;
import java.io.OutputStream;

import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

/**
 * @author Shuyang Zhou
 * @author Raymond Augé
 */
public class GZipServletOutputStream extends ServletOutputStream {

	public GZipServletOutputStream(OutputStream outputStream)
		throws IOException {

		_gZipOutputStream = new GZIPOutputStream(outputStream) {

			{
				def.setLevel(PropsValues.GZIP_COMPRESSION_LEVEL);
			}

		};
	}

	@Override
	public void close() throws IOException {
		_gZipOutputStream.close();
	}

	@Override
	public void flush() throws IOException {
		_gZipOutputStream.flush();
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		_gZipOutputStream.write(bytes);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		_gZipOutputStream.write(bytes, offset, length);
	}

	@Override
	public void write(int b) throws IOException {
		_gZipOutputStream.write(b);
	}

	private GZIPOutputStream _gZipOutputStream;

}