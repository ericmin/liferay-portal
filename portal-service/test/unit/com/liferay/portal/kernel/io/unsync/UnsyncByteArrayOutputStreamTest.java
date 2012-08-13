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
import java.io.UnsupportedEncodingException;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class UnsyncByteArrayOutputStreamTest extends TestCase {

	public void testBlockWrite() {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		unsyncByteArrayOutputStream.write(_BUFFER);

		assertEquals(_BUFFER_SIZE, unsyncByteArrayOutputStream.size());
		assertTrue(Arrays.equals(
			_BUFFER, unsyncByteArrayOutputStream.toByteArray()));
	}

	public void testConstructor() {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		assertEquals(0, unsyncByteArrayOutputStream.size());

		unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream(64);

		assertEquals(0, unsyncByteArrayOutputStream.size());
	}

	public void testSizeAndReset() {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		assertEquals(0, unsyncByteArrayOutputStream.size());

		unsyncByteArrayOutputStream.write(0);

		assertEquals(1, unsyncByteArrayOutputStream.size());

		unsyncByteArrayOutputStream.write(1);

		assertEquals(2, unsyncByteArrayOutputStream.size());

		unsyncByteArrayOutputStream.reset();

		assertEquals(0, unsyncByteArrayOutputStream.size());

		unsyncByteArrayOutputStream.write(0);

		assertEquals(1, unsyncByteArrayOutputStream.size());

		unsyncByteArrayOutputStream.write(1);

		assertEquals(2, unsyncByteArrayOutputStream.size());
	}

	public void testToByteArray() {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		unsyncByteArrayOutputStream.write(_BUFFER);

		byte[] bytes1 = unsyncByteArrayOutputStream.toByteArray();

		assertTrue(Arrays.equals(_BUFFER, bytes1));

		byte[] bytes2 = unsyncByteArrayOutputStream.toByteArray();

		assertTrue(Arrays.equals(_BUFFER, bytes2));

		assertNotSame(bytes1, bytes2);
	}

	public void testToString() throws UnsupportedEncodingException {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		unsyncByteArrayOutputStream.write(_BUFFER);

		assertEquals(
			new String(_BUFFER), unsyncByteArrayOutputStream.toString());

		String charsetName1 = "UTF-16BE";
		String charsetName2 = "UTF-16LE";

		assertFalse(
			new String(_BUFFER, charsetName1).equals(
				unsyncByteArrayOutputStream.toString(charsetName2)));
		assertEquals(
			new String(_BUFFER, charsetName1),
			unsyncByteArrayOutputStream.toString(charsetName1));
		assertEquals(
			new String(_BUFFER, charsetName2),
			unsyncByteArrayOutputStream.toString(charsetName2));
	}

	public void testUnsafeGetByteArray() {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		unsyncByteArrayOutputStream.write(_BUFFER);

		byte[] bytes1 = unsyncByteArrayOutputStream.unsafeGetByteArray();

		assertTrue(Arrays.equals(_BUFFER, bytes1));
		assertSame(unsyncByteArrayOutputStream.buffer, bytes1);

		byte[] bytes2 = unsyncByteArrayOutputStream.unsafeGetByteArray();

		assertTrue(Arrays.equals(_BUFFER, bytes2));

		assertSame(bytes1, bytes2);
	}

	public void testWrite() {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		for (int i = 0; i < _BUFFER_SIZE; i++) {
			unsyncByteArrayOutputStream.write(i);

			assertEquals(i + 1, unsyncByteArrayOutputStream.size());
		}

		assertTrue(
			Arrays.equals(_BUFFER, unsyncByteArrayOutputStream.toByteArray()));
	}

	public void testWriteTo() throws IOException {
		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		unsyncByteArrayOutputStream.write(_BUFFER);

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		unsyncByteArrayOutputStream.writeTo(byteArrayOutputStream);

		assertTrue(Arrays.equals(_BUFFER, byteArrayOutputStream.toByteArray()));
	}

	private static final byte[] _BUFFER =
		new byte[UnsyncByteArrayOutputStreamTest._BUFFER_SIZE];

	private static final int _BUFFER_SIZE = 64;

	static {
		for (int i = 0; i < _BUFFER_SIZE; i++) {
			_BUFFER[i] = (byte)i;
		}
	}

}