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

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspWriter;

/**
 * @author Shuyang Zhou
 */
public class TrimNewLinesJspWriter extends JspWriter {

	public TrimNewLinesJspWriter(Writer writer) {
		super(NO_BUFFER, false);

		_writer = writer;
	}

	@Override
	public void clear() throws IOException {
		throw new IOException();
	}

	@Override
	public void clearBuffer() {
	}

	@Override
	public void close() throws IOException {
		_writer.close();
	}

	@Override
	public void flush() throws IOException {
		_writer.flush();
	}

	@Override
	public int getRemaining() {
		return 0;
	}

	@Override
	public void newLine() throws IOException {
		if (!_lastNewLine) {
			_writer.write(_LINE_SEPARATOR);

			_lastNewLine = true;
		}
	}

	@Override
	public void print(boolean b) throws IOException {
		if (b) {
			_writer.write(StringPool.TRUE);
		}
		else {
			_writer.write(StringPool.FALSE);
		}

		_lastNewLine = false;
	}

	@Override
	public void print(char c) throws IOException {
		boolean newLine = false;

		if ((c == CharPool.NEW_LINE) || (c == CharPool.RETURN)) {
			newLine = true;
		}

		if (!_lastNewLine || !newLine) {
			_writer.write(c);
		}

		if (newLine) {
			_lastNewLine = true;
		}
	}

	@Override
	public void print(char[] chars) throws IOException {
		_writer.write(chars);

		_lastNewLine = false;
	}

	@Override
	public void print(double d) throws IOException {
		_writer.write(String.valueOf(d));

		_lastNewLine = false;
	}

	@Override
	public void print(float f) throws IOException {
		_writer.write(String.valueOf(f));

		_lastNewLine = false;
	}

	@Override
	public void print(int i) throws IOException {
		_writer.write(String.valueOf(i));

		_lastNewLine = false;
	}

	@Override
	public void print(long l) throws IOException {
		_writer.write(String.valueOf(l));

		_lastNewLine = false;
	}

	@Override
	public void print(Object object) throws IOException {
		_writer.write(String.valueOf(object));

		_lastNewLine = false;
	}

	@Override
	public void print(String string) throws IOException {
		if (string == null) {
			string = StringPool.NULL;
		}
		else {
			string = trim(string);
		}

		if (string.length() > 0) {
			_writer.write(string);

			_lastNewLine = false;
		}
	}

	@Override
	public void println() throws IOException {
		if (!_lastNewLine) {
			_writer.write(_LINE_SEPARATOR);

			_lastNewLine = true;
		}
	}

	@Override
	public void println(boolean b) throws IOException {
		if (b) {
			_writer.write(StringPool.TRUE);
		}
		else {
			_writer.write(StringPool.FALSE);
		}

		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(char c) throws IOException {
		_writer.write(c);
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(char[] chars) throws IOException {
		_writer.write(chars);
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(double d) throws IOException {
		_writer.write(String.valueOf(d));
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(float f) throws IOException {
		_writer.write(String.valueOf(f));
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(int i) throws IOException {
		_writer.write(String.valueOf(i));
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(long l) throws IOException {
		_writer.write(String.valueOf(l));
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(Object object) throws IOException {
		_writer.write(String.valueOf(object));
		_writer.write(_LINE_SEPARATOR);

		_lastNewLine = true;
	}

	@Override
	public void println(String string) throws IOException {
		if (string == null) {
			string = StringPool.NULL;
		}
		else {
			string = trim(string);
		}

		if (string.length() > 0) {
			_writer.write(string);

			_lastNewLine = true;
		}
	}

	@Override
	public void write(char[] chars) throws IOException {
		_writer.write(chars);

		_lastNewLine = false;
	}

	@Override
	public void write(char[] chars, int offset, int length) throws IOException {
		_writer.write(chars, offset, length);

		_lastNewLine = false;
	}

	@Override
	public void write(int c) throws IOException {
		boolean newLine = false;

		if ((c == CharPool.NEW_LINE) || (c == CharPool.RETURN)) {
			newLine = true;
		}

		if (!_lastNewLine || !newLine) {
			_writer.write(c);
		}

		if (newLine) {
			_lastNewLine = true;
		}
	}

	@Override
	public void write(String string) throws IOException {
		if (string.length() > 0) {
			_writer.write(string);

			_lastNewLine = false;
		}
	}

	@Override
	public void write(String string, int offset, int length)
		throws IOException {

		string = trim(string.substring(offset, offset + length));

		if (string.length() > 0) {
			_writer.write(string);

			_lastNewLine = false;
		}
	}

	protected String trim(String string) {
		int length = string.length();

		int start = length;

		for (int i = 0; i < length; i++) {
			char c = string.charAt(i);

			if ((c != CharPool.NEW_LINE) && (c != CharPool.RETURN)) {
				start = i;

				break;
			}
		}

		int end = 0;

		for (int i = length - 1; i >= 0 ; i--) {
			char c = string.charAt(i);

			if ((c != CharPool.NEW_LINE) && (c != CharPool.RETURN)) {
				end = i + 1;

				break;
			}
		}

		if (end > start) {
			return string.substring(start, end);
		}
		else {
			return StringPool.BLANK;
		}
	}

	private static final String _LINE_SEPARATOR = System.getProperty(
		"line.separator");

	private boolean _lastNewLine;
	private Writer _writer;

}