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
 * @author Alexander Chow
 */
public class UnicodePropertiesTest extends TestCase {

	public void testLength() throws Exception {
		String key = "hello";
		String value = "world";

		UnicodeProperties props = new UnicodeProperties();

		props.setProperty(key, value);
		props.remove(key);

		assertEquals(0, props.getToStringLength());
	}

	public void testSetNullProperty() throws Exception {
		UnicodeProperties props = new UnicodeProperties();

		int hashCode = props.hashCode();

		props.setProperty(null, "value");

		assertEquals(
			"setProperty() of null key must not change properties", hashCode,
			props.hashCode());

		props.setProperty("key", null);
		props.setProperty("key", "value");
		props.setProperty("key", null);

		assertEquals(
			"setProperty() of null value must remove entry", hashCode,
			props.hashCode());
	}

}