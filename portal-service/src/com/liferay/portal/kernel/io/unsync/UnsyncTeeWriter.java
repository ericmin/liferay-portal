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

package com.liferay.portal.kernel.io.unsync;

import java.io.IOException;
import java.io.Writer;

/**
 * @author Shuyang Zhou
 */
public class UnsyncTeeWriter extends Writer {

	public UnsyncTeeWriter(Writer writer1, Writer writer2) {
		_writer1 = writer1;
		_writer2 = writer2;
	}

	@Override
	public Writer append(char c) throws IOException {
		_writer1.append(c);
		_writer2.append(c);

		return this;
	}

	@Override
	public Writer append(CharSequence charSequence) throws IOException {
		_writer1.append(charSequence);
		_writer2.append(charSequence);

		return this;
	}

	@Override
	public Writer append(CharSequence charSequence, int start, int end)
		throws IOException {

		_writer1.append(charSequence, start, end);
		_writer2.append(charSequence, start, end);

		return this;
	}

	@Override
	public void close() throws IOException {
		_writer1.close();
		_writer2.close();
	}

	@Override
	public void flush() throws IOException {
		_writer1.flush();
		_writer2.flush();
	}

	@Override
	public void write(char[] chars) throws IOException {
		_writer1.write(chars);
		_writer2.write(chars);
	}

	@Override
	public void write(char[] chars, int offset, int length) throws IOException {
		_writer1.write(chars, offset, length);
		_writer2.write(chars, offset, length);
	}

	@Override
	public void write(int c) throws IOException {
		_writer1.write(c);
		_writer2.write(c);
	}

	@Override
	public void write(String string) throws IOException {
		_writer1.write(string);
		_writer2.write(string);
	}

	@Override
	public void write(String string, int offset, int length)
		throws IOException {

		_writer1.write(string, offset, length);
		_writer2.write(string, offset, length);
	}

	private Writer _writer1;
	private Writer _writer2;

}