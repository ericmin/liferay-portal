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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.util.CamelCaseUtil;

import junit.framework.TestCase;

/**
 * @author Igor Spasic
 */
public class CamelCaseUtilTest extends TestCase {

	public void testFromCamelCase() {
		assertEquals("camel-case", CamelCaseUtil.fromCamelCase("camelCase"));
		assertEquals(
			"camel-case-word", CamelCaseUtil.fromCamelCase("camelCASEWord"));
		assertEquals("camel-case", CamelCaseUtil.fromCamelCase("camelCASE"));
	}

	public void testNormalization() {
		assertEquals(
			"camelCase", CamelCaseUtil.normalizeCamelCase("camelCase"));
		assertEquals(
			"camelCaseWord", CamelCaseUtil.normalizeCamelCase("camelCASEWord"));
		assertEquals(
			"camelCase", CamelCaseUtil.normalizeCamelCase("camelCASE"));
	}

	public void testToCamelCase() {
		assertEquals("camelCase", CamelCaseUtil.toCamelCase("camel-case"));
		assertEquals(
			"camelCASEWord", CamelCaseUtil.toCamelCase("camel-CASE-word"));
		assertEquals("camelCASE", CamelCaseUtil.toCamelCase("camel-CASE"));
	}

}