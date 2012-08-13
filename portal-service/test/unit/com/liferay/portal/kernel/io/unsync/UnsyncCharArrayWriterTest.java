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
import com.liferay.portal.kernel.util.StringPool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import java.nio.CharBuffer;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class UnsyncCharArrayWriterTest extends TestCase {

	public void testAppendChar() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.append('a');

		assertEquals(1, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);

		unsyncCharArrayWriter.append('b');

		assertEquals(2, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);
	}

	public void testAppendCharSequence() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.append(new StringBuilder("ab"));

		assertEquals(2, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);

		unsyncCharArrayWriter.append(new StringBuilder("cd"));

		assertEquals(4, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);
		assertEquals('c', unsyncCharArrayWriter.buffer[2]);
		assertEquals('d', unsyncCharArrayWriter.buffer[3]);
	}

	public void testConstructor() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		assertEquals(0, unsyncCharArrayWriter.size());
		assertEquals(32, unsyncCharArrayWriter.buffer.length);

		unsyncCharArrayWriter = new UnsyncCharArrayWriter(64);

		assertEquals(0, unsyncCharArrayWriter.size());
		assertEquals(64, unsyncCharArrayWriter.buffer.length);
	}

	public void testReset() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write("test1");

		assertEquals(5, unsyncCharArrayWriter.size());

		unsyncCharArrayWriter.reset();

		assertEquals(0, unsyncCharArrayWriter.size());
	}

	public void testToCharBuffer() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write("test1");

		CharBuffer charBuffer = unsyncCharArrayWriter.toCharBuffer();

		assertEquals(unsyncCharArrayWriter.buffer, charBuffer.array());

		assertEquals(0, charBuffer.position());
		assertEquals(5, charBuffer.limit());
		assertEquals("test1", charBuffer.toString());
	}

	public void testToString() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write("test1");

		assertEquals("test1", unsyncCharArrayWriter.toString());
	}

	public void testWriteChar() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write('a');

		assertEquals(1, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);

		unsyncCharArrayWriter.write('b');

		assertEquals(2, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);
	}

	public void testWriteCharArray() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write("ab".toCharArray());

		assertEquals(2, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);

		unsyncCharArrayWriter.write("cd".toCharArray());

		assertEquals(4, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);
		assertEquals('c', unsyncCharArrayWriter.buffer[2]);
		assertEquals('d', unsyncCharArrayWriter.buffer[3]);
	}

	public void testWriteString() {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write("ab");

		assertEquals(2, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);

		unsyncCharArrayWriter.write("cd");

		assertEquals(4, unsyncCharArrayWriter.size());
		assertEquals('a', unsyncCharArrayWriter.buffer[0]);
		assertEquals('b', unsyncCharArrayWriter.buffer[1]);
		assertEquals('c', unsyncCharArrayWriter.buffer[2]);
		assertEquals('d', unsyncCharArrayWriter.buffer[3]);
	}

	public void testWriteTo() throws IOException {
		UnsyncCharArrayWriter unsyncCharArrayWriter =
			new UnsyncCharArrayWriter();

		unsyncCharArrayWriter.write("abcd");

		CharBuffer charBuffer = CharBuffer.allocate(2);

		int length = unsyncCharArrayWriter.writeTo(charBuffer);

		assertEquals(2, length);
		assertEquals(2, charBuffer.position());
		assertEquals(2, charBuffer.limit());
		charBuffer.position(0);
		assertEquals("ab", charBuffer.toString());

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		length = unsyncCharArrayWriter.writeTo(
			byteArrayOutputStream, StringPool.UTF8);

		assertEquals(4, length);
		assertEquals(4, byteArrayOutputStream.size());
		assertTrue(
			Arrays.equals(
				"abcd".getBytes(), byteArrayOutputStream.toByteArray()));

		StringWriter stringWriter = new StringWriter();

		length = unsyncCharArrayWriter.writeTo(stringWriter);

		assertEquals(4, length);
		assertEquals("abcd", stringWriter.toString());
	}

}