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

package com.liferay.portal.kernel.dao.jdbc;

import com.liferay.portal.kernel.io.LimitedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author Shuyang Zhou
 */
public class OutputBlob implements Blob {

	public OutputBlob(InputStream inputStream, long length) {
		if (inputStream == null) {
			throw new IllegalArgumentException("Input stream is null");
		}

		if (length < 0) {
			throw new IllegalArgumentException("Length is less than 0");
		}

		_inputStream = inputStream;
		_length = length;
	}

	public void free() throws SQLException {
		try {
			_inputStream.close();
		}
		catch (IOException ioe) {
			throw new SQLException(ioe.getMessage());
		}

		_inputStream = null;
	}

	public InputStream getBinaryStream() {
		return _inputStream;
	}

	public InputStream getBinaryStream(long pos, long length)
		throws SQLException {

		if (pos < 1) {
			throw new SQLException("Position is less than 1");
		}

		long offset = pos - 1;

		if ((offset >= _length) || ((offset + length) >= _length)) {
			throw new SQLException("Invalid range");
		}

		try {
			return new LimitedInputStream(_inputStream, offset, length);
		}
		catch (IOException ioe) {
			throw new SQLException(ioe.getMessage());
		}
	}

	public byte[] getBytes(long pos, int length) throws SQLException {
		if (pos < 1) {
			throw new SQLException("Position is less than 1");
		}

		if (length < 0) {
			throw new SQLException("Length is less than 0");
		}

		byte[] bytes = new byte[length];

		try {
			int newLength = 0;

			int read = -1;

			while ((newLength < length) &&
				   ((read = _inputStream.read(
					   bytes, newLength, length - newLength)) != -1)) {

				newLength += read;
			}

			if (newLength < length) {
				byte[] newBytes = new byte[newLength];

				System.arraycopy(bytes, 0, newBytes, 0, newLength);

				bytes = newBytes;
			}
		}
		catch (IOException ioe) {
			throw new SQLException(ioe.getMessage());
		}

		return bytes;
	}

	public long length() {
		return _length;
	}

	public long position(Blob pattern, long start) {
		throw new UnsupportedOperationException();
	}

	public long position(byte[] pattern, long start) {
		throw new UnsupportedOperationException();
	}

	public OutputStream setBinaryStream(long pos) {
		throw new UnsupportedOperationException();
	}

	public int setBytes(long pos, byte[] bytes) {
		throw new UnsupportedOperationException();
	}

	public int setBytes(long pos, byte[] bytes, int offset, int length) {
		throw new UnsupportedOperationException();
	}

	public void truncate(long length) {
		throw new UnsupportedOperationException();
	}

	private InputStream _inputStream;
	private long _length;

}