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

package com.liferay.util.bridges.jsf.sun;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Brian Myunghun Kim
 */
public class WriterWrapper extends Writer {

	public WriterWrapper(Writer writer) {
		_writer = writer;
	}

	@Override
	public void close() throws IOException {
		_writer.close();
	}

	@Override
	public void flush() {
	}

	public void write(char cbuf) throws IOException {
		_writer.write(cbuf);
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		StringBuilder sb = new StringBuilder(len);

		sb.append(cbuf, off, len);

		_writer.write(sb.toString());
	}

	@Override
	public void write(int c) throws IOException {
		_writer.write(c);
	}

	@Override
	public void write(String str) throws IOException {
		_writer.write(str);
	}

	@Override
	public void write(String str, int off, int len) throws IOException {
		_writer.write(str, off, len);
	}

	private Writer _writer;

}