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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.test.TestCase;

/**
 * @author Michael C. Han
 */
public class PrimitiveIntListTest extends TestCase {

	public void testAdd() {
		int[] expected = new int[] {10, 11, 12};

		PrimitiveIntList primitiveIntList = new PrimitiveIntList();

		for (int i = 0; i < expected.length; i++) {
			primitiveIntList.add(expected[i]);
		}

		assertEquals(expected.length, primitiveIntList.size());

		int[] actual = primitiveIntList.getArray();

		assertEquals(expected.length, actual.length);

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

	public void testAddAll() {
		int[] expected = new int[] {10, 11, 12};

		PrimitiveIntList primitiveIntList = new PrimitiveIntList();

		primitiveIntList.addAll(expected);

		assertEquals(expected.length, primitiveIntList.size());

		int[] actual = primitiveIntList.getArray();

		assertEquals(expected.length, actual.length);

		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}

}