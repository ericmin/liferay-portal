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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.test.TestCase;

import java.io.ByteArrayInputStream;

/**
 * @author Tina Tian
 */
public class Base64InputStreamTest extends TestCase {

	public void testAvailable() throws Exception {
		byte[] bytes = {'a', 'b', 'c', 'd'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		int returnValue = base64InputStream.available();

		assertEquals(3, returnValue);

		base64InputStream.close();
	}

	public void testDecode() {
		try {
			byte[] bytes = {'a', 'b', 'c', 'd'};

			Base64InputStream base64InputStream = new Base64InputStream(
				new ByteArrayInputStream(bytes));

			byte[] outputBuffer = new byte[3];
			int position = 0;

			assertEquals(
				3, base64InputStream.decode(bytes, outputBuffer, position, 0));
			assertEquals(
				2, base64InputStream.decode(bytes, outputBuffer, position, 1));
			assertEquals(
				1, base64InputStream.decode(bytes, outputBuffer, position, 2));
			assertEquals(
				-1, base64InputStream.decode(bytes, outputBuffer, position, 3));

			base64InputStream.close();
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDecodeUnit() throws Exception {
		byte[] bytes = {
			'a', 'b', 'c', 'd', 'e', 'f', 'h', '=', 'e', 'f', '=', '=', 'e',
			'=', 'e', 'f', '=', 'a'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		byte[] outputBuffer = new byte[3];
		int position = 0;

		assertEquals(3, base64InputStream.decodeUnit(outputBuffer, position));
		assertEquals(2, base64InputStream.decodeUnit(outputBuffer, position));
		assertEquals(1, base64InputStream.decodeUnit(outputBuffer, position));
		assertEquals(-1, base64InputStream.decodeUnit(outputBuffer, position));
		assertEquals(-1, base64InputStream.decodeUnit(outputBuffer, position));
		assertEquals(-1, base64InputStream.decodeUnit(outputBuffer, position));

		base64InputStream.close();
	}

	public void testGetByte() {
		try {
			byte[] bytes = {'a'};

			Base64InputStream base64InputStream = new Base64InputStream(
				new ByteArrayInputStream(bytes));

			assertEquals(0, base64InputStream.getByte('A'));
			assertEquals(0, base64InputStream.getByte('='));
			assertEquals(-1, base64InputStream.getByte('\n'));
			assertEquals(62, base64InputStream.getByte('+'));

			base64InputStream.close();
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testGetEncodedByte() throws Exception {
		try {
			byte[] bytes = {'A', '=', 'B', '\n'};

			Base64InputStream base64InputStream = new Base64InputStream(
				new ByteArrayInputStream(bytes));

			assertEquals(0, base64InputStream.getEncodedByte());
			assertEquals(-2, base64InputStream.getEncodedByte());
			assertEquals(1, base64InputStream.getEncodedByte());
			assertEquals(-1, base64InputStream.getEncodedByte());

			base64InputStream.close();
		}
		catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testRead_0args() throws Exception {
		byte[] bytes = {'a', 'b', 'c', 'd'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		assertEquals(105, base64InputStream.read());

		base64InputStream.read();
		base64InputStream.read();

		assertEquals(-1, base64InputStream.read());

		base64InputStream.close();
	}

	public void testRead_3args() throws Exception {
		byte[] bytes = {
			'a', 'b', 'c', 'd', 'a', 'b', 'c', 'd', 'e', 'f', 'g', '='};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		byte[] buffer = new byte[5];
		int offset = 0;

		assertEquals(1, base64InputStream.read(buffer, offset, 1));
		assertEquals(2, base64InputStream.read(buffer, offset, 2));
		assertEquals(5, base64InputStream.read(buffer, offset, 6));
		assertEquals(-1, base64InputStream.read(buffer, offset, 3));
		assertEquals(-1, base64InputStream.read(buffer, offset, 1));
		assertEquals(-1, base64InputStream.read(buffer, offset, 0));

		base64InputStream.close();
	}

	public void testSkip() throws Exception {
		byte[] bytes = {'a', 'b', 'c', 'd'};

		Base64InputStream base64InputStream = new Base64InputStream(
			new ByteArrayInputStream(bytes));

		assertEquals(3L, base64InputStream.skip(4L));

		base64InputStream.close();
	}

}