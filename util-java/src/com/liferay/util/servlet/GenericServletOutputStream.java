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

package com.liferay.util.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class GenericServletOutputStream extends ServletOutputStream {

	public GenericServletOutputStream(OutputStream outputStream) {
		_outputStream = outputStream;
	}

	@Override
	public void write(byte[] bytes) throws IOException {
		_outputStream.write(bytes);
	}

	@Override
	public void write(byte[] bytes, int offset, int length) throws IOException {
		_outputStream.write(bytes, offset, length);
	}

	@Override
	public void write(int b) throws IOException {
		_outputStream.write(b);
	}

	private OutputStream _outputStream;

}