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

import javax.servlet.ServletInputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class ServletInputStreamWrapper extends ServletInputStream {

	public ServletInputStreamWrapper(ServletInputStream is) {
		_is = is;
	}

	@Override
	public int available() throws IOException {
		return _is.available();
	}

	@Override
	public void close() throws IOException {
		_is.close();
	}

	@Override
	public void mark(int readlimit) {
		_is.mark(readlimit);
	}

	@Override
	public boolean markSupported() {
		return _is.markSupported();
	}

	@Override
	public int read() throws IOException {
		return _is.read();
	}

	@Override
	public int read(byte[] b) throws IOException {
		return _is.read(b);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return _is.read(b, off, len);
	}

	@Override
	public int readLine(byte[] b, int off, int len) throws IOException {
		return _is.readLine(b, off, len);
	}

	@Override
	public void reset() throws IOException {
		_is.reset();
	}

	@Override
	public long skip(long n) throws IOException {
		return _is.skip(n);
	}

	private ServletInputStream _is;

}