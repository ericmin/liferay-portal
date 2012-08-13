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

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.test.TestCase;

/**
 * @author Shuyang Zhou
 */
public class IncreasableEntryTest extends TestCase {

	public void testGettingKey() {
		IncreasableEntry<String, Integer> increasableEntry =
			new IntegerIncreasableEntry("test", 0);

		assertEquals("test", increasableEntry.getKey());
		assertEquals("test", increasableEntry.getKey());
		assertEquals("test", increasableEntry.getKey());
	}

	public void testIncreaseAndGet() {
		IncreasableEntry<String, Integer> increasableEntry =
			new IntegerIncreasableEntry("test", 0);

		// Simple increase

		assertTrue(increasableEntry.increase(1));

		// Simple get

		assertEquals(1, (int)increasableEntry.getValue());

		increasableEntry = new IntegerIncreasableEntry("test", 0);

		// Continue get

		assertEquals(0, (int)increasableEntry.getValue());
		assertEquals(0, (int)increasableEntry.getValue());
		assertEquals(0, (int)increasableEntry.getValue());

		increasableEntry = new IntegerIncreasableEntry("test", 0);

		// Continue increase

		assertTrue(increasableEntry.increase(1));
		assertTrue(increasableEntry.increase(2));
		assertTrue(increasableEntry.increase(3));

		// Check value

		assertEquals(6, (int)increasableEntry.getValue());

		// Increase after get

		increasableEntry = new IntegerIncreasableEntry("test", 0);

		assertEquals(0, (int)increasableEntry.getValue());
		assertFalse(increasableEntry.increase(1));
		assertEquals(0, (int)increasableEntry.getValue());
	}

	private class IntegerIncreasableEntry
		extends IncreasableEntry<String, Integer> {

		public IntegerIncreasableEntry(String key, Integer value) {
			super(key, value);
		}

		@Override
		public Integer doIncrease(Integer originalValue, Integer deltaValue) {
			return originalValue + deltaValue;
		}

	}

}