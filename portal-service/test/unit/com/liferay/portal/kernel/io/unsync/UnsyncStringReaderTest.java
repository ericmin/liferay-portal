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

import java.io.IOException;

import java.util.Arrays;

/**
 * @author Shuyang Zhou
 */
public class UnsyncStringReaderTest extends TestCase {

	public void testBlockRead() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(
			"abcdefg");

		char[] chars = new char[4];

		assertEquals(4, unsyncStringReader.read(chars));
		assertEquals(4, unsyncStringReader.index);
		assertTrue(Arrays.equals("abcd".toCharArray(), chars));

		assertEquals(3, unsyncStringReader.read(chars));
		assertEquals('e', chars[0]);
		assertEquals('f', chars[1]);
		assertEquals('g', chars[2]);

		assertEquals(-1, unsyncStringReader.read(chars));
	}

	public void testClose() {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(
			"abcdefg");

		unsyncStringReader.close();

		assertTrue(unsyncStringReader.string == null);

		try {
			unsyncStringReader.mark(0);

			fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.read();

			fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.read(new char[5]);

			fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.ready();

			fail();
		}
		catch (IOException ioe) {
		}

		try {
			unsyncStringReader.reset();

			fail();
		}
		catch (IOException ioe) {
		}

		unsyncStringReader.close();
	}

	public void testConstructor() {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		assertEquals("abc", unsyncStringReader.string);
		assertEquals(3, unsyncStringReader.stringLength);

		unsyncStringReader = new UnsyncStringReader("defg");

		assertEquals("defg", unsyncStringReader.string);
		assertEquals(4, unsyncStringReader.stringLength);
	}

	public void testMarkAndReset() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		assertEquals('a', unsyncStringReader.read());

		unsyncStringReader.mark(-1);

		assertEquals('b', unsyncStringReader.read());
		assertEquals('c', unsyncStringReader.read());
		assertEquals(-1, unsyncStringReader.read());

		unsyncStringReader.reset();

		assertEquals('b', unsyncStringReader.read());
		assertEquals('c', unsyncStringReader.read());
		assertEquals(-1, unsyncStringReader.read());
	}

	public void testMarkSupported() {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		assertTrue(unsyncStringReader.markSupported());
	}

	public void testRead() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader("abc");

		assertEquals('a', unsyncStringReader.read());
		assertEquals('b', unsyncStringReader.read());
		assertEquals('c', unsyncStringReader.read());
		assertEquals(-1, unsyncStringReader.read());
	}

	public void testSkip() throws IOException {
		UnsyncStringReader unsyncStringReader = new UnsyncStringReader(
			"abcdef");

		assertEquals('a', unsyncStringReader.read());
		assertEquals(2, unsyncStringReader.skip(2));
		assertEquals('d', unsyncStringReader.read());
		assertEquals(2, unsyncStringReader.skip(3));
		assertEquals(-1, unsyncStringReader.read());
	}

}