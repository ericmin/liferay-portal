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
 * @author Shuyang Zhou
 */
public class TextFormatterTest extends TestCase {

	public void testFormatA() {
		String original = "Web Search";
		String expected = "WEB_SEARCH";

		String actual = TextFormatter.format(original, TextFormatter.A);

		assertEquals(expected, actual);
	}

	public void testFormatB() {
		String original = "Web Search";
		String expected = "websearch";

		String actual = TextFormatter.format(original, TextFormatter.B);

		assertEquals(expected, actual);
	}

	public void testFormatC() {
		String original = "Web Search";
		String expected = "web_search";

		String actual = TextFormatter.format(original, TextFormatter.C);

		assertEquals(expected, actual);
	}

	public void testFormatD() {
		String original = "Web Search";
		String expected = "WebSearch";

		String actual = TextFormatter.format(original, TextFormatter.D);

		assertEquals(expected, actual);
	}

	public void testFormatE() {
		String original = "Web Search";
		String expected = "web search";

		String actual = TextFormatter.format(original, TextFormatter.E);

		assertEquals(expected, actual);
	}

	public void testFormatF() {
		String original = "Web Search";
		String expected = "webSearch";

		String actual = TextFormatter.format(original, TextFormatter.F);

		assertEquals(expected, actual);
	}

	public void testFormatG() {
		String original = "formatId";
		String expected = "FormatId";

		String actual = TextFormatter.format(original, TextFormatter.G);

		assertEquals(expected, actual);
	}

	public void testFormatH() {
		String original = "formatId";
		String expected = "format id";

		String actual = TextFormatter.format(original, TextFormatter.H);

		assertEquals(expected, actual);
	}

	public void testFormatI() {
		String original = "FormatId";
		String expected = "formatId";

		String actual = TextFormatter.format(original, TextFormatter.I);

		assertEquals(expected, actual);
	}

	public void testFormatJ() {
		String original = "format-id";
		String expected = "Format Id";

		String actual = TextFormatter.format(original, TextFormatter.J);

		assertEquals(expected, actual);
	}

	public void testFormatK() {
		String original = "formatId";
		String expected = "format-id";

		String actual = TextFormatter.format(original, TextFormatter.K);

		assertEquals(expected, actual);
	}

	public void testFormatL() {
		String original = "FormatId";
		String expected = "formatId";

		String actual = TextFormatter.format(original, TextFormatter.L);

		assertEquals(expected, actual);

		original = "FOrmatId";
		expected = "FOrmatId";

		actual = TextFormatter.format(original, TextFormatter.L);

		assertEquals(expected, actual);
	}

	public void testFormatM() {
		String original = "format-id";
		String expected = "formatId";

		String actual = TextFormatter.format(original, TextFormatter.M);

		assertEquals(expected, actual);
	}

	public void testFormatN() {
		String original = "format-id";
		String expected = "format_id";

		String actual = TextFormatter.format(original, TextFormatter.N);

		assertEquals(expected, actual);
	}

	public void testFormatO() {
		String original = "format_id";
		String expected = "format-id";

		String actual = TextFormatter.format(original, TextFormatter.O);

		assertEquals(expected, actual);
	}

	public void testFormatP() {
		String original = "formatID";
		String expected = "format-id";

		String actual = TextFormatter.format(original, TextFormatter.P);

		assertEquals(expected, actual);
	}

}