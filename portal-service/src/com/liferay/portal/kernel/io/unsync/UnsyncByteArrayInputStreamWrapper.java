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

import javax.servlet.ServletInputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class UnsyncByteArrayInputStreamWrapper extends ServletInputStream {

	public UnsyncByteArrayInputStreamWrapper(
		UnsyncByteArrayInputStream unsyncByteArrayInputStream) {

		_unsyncByteArrayInputStream = unsyncByteArrayInputStream;
	}

	@Override
	public int available() {
		return _unsyncByteArrayInputStream.available();
	}

	@Override
	public void close() throws IOException {
		_unsyncByteArrayInputStream.close();
	}

	@Override
	public void mark(int readLimit) {
		_unsyncByteArrayInputStream.mark(readLimit);
	}

	@Override
	public boolean markSupported() {
		return _unsyncByteArrayInputStream.markSupported();
	}

	@Override
	public int read() {
		return _unsyncByteArrayInputStream.read();
	}

	@Override
	public int read(byte[] bytes) {
		return _unsyncByteArrayInputStream.read(bytes);
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		return _unsyncByteArrayInputStream.read(bytes, offset, length);
	}

	@Override
	public int readLine(byte[] bytes, int offset, int length) {
		return _unsyncByteArrayInputStream.read(bytes, offset, length);
	}

	@Override
	public void reset() {
		_unsyncByteArrayInputStream.reset();
	}

	@Override
	public long skip(long skip) {
		return _unsyncByteArrayInputStream.skip(skip);
	}

	private UnsyncByteArrayInputStream _unsyncByteArrayInputStream;

}