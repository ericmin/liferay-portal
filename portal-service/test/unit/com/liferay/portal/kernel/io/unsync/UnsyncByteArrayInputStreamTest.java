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

/**
 * @author Shuyang Zhou
 */
public class UnsyncByteArrayInputStreamTest extends TestCase {

	public void testBlockRead() {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(_BUFFER);

		int size = _SIZE * 2 / 3;

		byte[] buffer = new byte[size];

		int read = unsyncByteArrayInputStream.read(buffer);

		assertEquals(size, read);

		for (int i = 0; i < read; i++) {
			assertEquals(i, buffer[i]);
		}

		read = unsyncByteArrayInputStream.read(buffer);

		assertEquals(_SIZE - size, read);

		for (int i = 0; i < read; i++) {
			assertEquals(i + size, buffer[i]);
		}
	}

	public void testConstructor() {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(_BUFFER);

		assertEquals(_SIZE, unsyncByteArrayInputStream.available());

		unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(
			_BUFFER, _SIZE / 2, _SIZE / 2);

		assertEquals(_SIZE / 2, unsyncByteArrayInputStream.available());
	}

	public void testMarkAndReset() {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(_BUFFER);

		assertEquals(0, unsyncByteArrayInputStream.read());
		assertEquals(1, unsyncByteArrayInputStream.read());

		unsyncByteArrayInputStream.mark(-1);

		assertEquals(_SIZE - 2, unsyncByteArrayInputStream.available());
		assertEquals(2, unsyncByteArrayInputStream.read());
		assertEquals(3, unsyncByteArrayInputStream.read());
		assertEquals(_SIZE - 4, unsyncByteArrayInputStream.available());

		unsyncByteArrayInputStream.reset();

		assertEquals(_SIZE - 2, unsyncByteArrayInputStream.available());
		assertEquals(2, unsyncByteArrayInputStream.read());
		assertEquals(3, unsyncByteArrayInputStream.read());

		assertEquals(_SIZE - 4, unsyncByteArrayInputStream.available());
	}

	public void testMarkSupported() {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(_BUFFER);

		assertTrue(unsyncByteArrayInputStream.markSupported());
	}

	public void testRead() {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(_BUFFER);

		for (int i = 0; i < _SIZE; i++) {
			assertEquals(i, unsyncByteArrayInputStream.read());
		}

		assertEquals(-1, unsyncByteArrayInputStream.read());
	}

	public void testSkip() {
		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(_BUFFER);

		long size = _SIZE * 2 / 3;

		assertEquals(size, unsyncByteArrayInputStream.skip(size));
		assertEquals(_SIZE - size, unsyncByteArrayInputStream.available());
		assertEquals(_SIZE - size, unsyncByteArrayInputStream.skip(size));
		assertEquals(0, unsyncByteArrayInputStream.available());
	}

	private static final byte[] _BUFFER =
		new byte[UnsyncByteArrayInputStreamTest._SIZE];

	private static final int _SIZE = 10;

	static {
		for (int i = 0; i < _SIZE; i++) {
			_BUFFER[i] = (byte)i;
		}
	}

}