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

import com.liferay.portal.kernel.test.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class UnsyncBufferedOutputStreamTest extends TestCase {

	public void testBlockWrite() throws IOException {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		UnsyncBufferedOutputStream unsyncBufferedOutputStream =
			new UnsyncBufferedOutputStream(byteArrayOutputStream, _SIZE * 2);

		assertEquals(_SIZE * 2, unsyncBufferedOutputStream.buffer.length);

		unsyncBufferedOutputStream.write(_BUFFER);

		for (int i = 0; i < _SIZE; i++) {
			assertEquals(i, unsyncBufferedOutputStream.buffer[i]);
		}

		unsyncBufferedOutputStream.write(_BUFFER);

		for (int i = _SIZE; i < _SIZE * 2; i++) {
			assertEquals(i - _SIZE, unsyncBufferedOutputStream.buffer[i]);
		}

		unsyncBufferedOutputStream.write(100);

		assertEquals(100, unsyncBufferedOutputStream.buffer[0]);
		assertEquals(_SIZE * 2, byteArrayOutputStream.size());
	}

	public void testConstructor() {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		UnsyncBufferedOutputStream unsyncBufferedOutputStream =
			new UnsyncBufferedOutputStream(byteArrayOutputStream);

		assertEquals(8192, unsyncBufferedOutputStream.buffer.length);

		unsyncBufferedOutputStream = new UnsyncBufferedOutputStream(
			byteArrayOutputStream, 10);

		assertEquals(10, unsyncBufferedOutputStream.buffer.length);

		try {
			new UnsyncBufferedOutputStream(byteArrayOutputStream, 0);
		}
		catch (IllegalArgumentException iae) {
		}

		try {
			new UnsyncBufferedOutputStream(byteArrayOutputStream, -1);
		}
		catch (IllegalArgumentException iae) {
		}
	}

	public void testWrite() throws IOException {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		UnsyncBufferedOutputStream unsyncBufferedOutputStream =
			new UnsyncBufferedOutputStream(byteArrayOutputStream, _SIZE * 2);

		assertEquals(_SIZE * 2, unsyncBufferedOutputStream.buffer.length);

		for (int i = 0; i < _SIZE; i++) {
			unsyncBufferedOutputStream.write(i);

			assertEquals(i, unsyncBufferedOutputStream.buffer[i]);
		}

		unsyncBufferedOutputStream.flush();

		assertTrue(Arrays.equals(_BUFFER, byteArrayOutputStream.toByteArray()));
	}

	private static final byte[] _BUFFER =
		new byte[UnsyncBufferedOutputStreamTest._SIZE];

	private static final int _SIZE = 10;

	static {
		for (int i = 0; i < _SIZE; i++) {
			_BUFFER[i] = (byte)i;
		}
	}

}