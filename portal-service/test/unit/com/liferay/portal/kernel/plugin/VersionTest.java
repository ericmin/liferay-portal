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

package com.liferay.portal.kernel.plugin;

import com.liferay.portal.kernel.test.TestCase;

/**
 * @author Jorge Ferrer
 */
public class VersionTest extends TestCase {

	public void testBugFixNumber() {
		assertPrevious("1.1.0", "1.1.1");
		assertLater("1.1.1", "1.1.0");
		assertLater("1.2.0", "1.1.1");
	}

	public void testMajorNumber() {
		assertPrevious("1.1", "1.1.1");
		assertLater("2", "1.1.1");
		assertLater("2", "1");
	}

	public void testMinorNumber() {
		assertPrevious("1.1", "1.1.1");
		assertLater("1.2", "1.1.1");
		assertLater("1.2", "1.1");
	}

	public void testPlus() {
		assertNotIncludes("1+", "0");
		assertIncludes("1+", "1");
		assertIncludes("1+", "2");
		assertNotIncludes("1.1+", "1.0");
		assertIncludes("1.1+", "1.1");
		assertIncludes("1.1+", "1.10");
		assertIncludes("1.1+", "1.2");
		assertNotIncludes("1.1+", "2");
		assertNotIncludes("1.1.2+", "1.1.1");
		assertIncludes("1.1.2+", "1.1.2");
		assertIncludes("1.1.2+", "1.1.13");
		assertNotIncludes("1.1.2+", "1.2");
	}

	public void testStar() {
		assertIncludes("1.1.*", "1.1.0");
		assertIncludes("1.*", "1.1");
		assertIncludes("*", "1");
		assertIncludes("*", "1.2");
		assertIncludes("*", "1.2.3");
	}

	protected void assertIncludes(String first, String second) {
		Version firstVersion = Version.getInstance(first);
		Version secondVersion = Version.getInstance(second);

		assertTrue(
			first + " does not include " + second,
			firstVersion.includes(secondVersion));
	}

	protected void assertLater(String first, String second) {
		Version firstVersion = Version.getInstance(first);

		assertTrue(
			first + " is not later than " + second,
			firstVersion.isLaterVersionThan(second.toString()));
	}

	protected void assertNotIncludes(String first, String second) {
		Version firstVersion = Version.getInstance(first);
		Version secondVersion = Version.getInstance(second);

		assertFalse(
			first + " includes " + second,
			firstVersion.includes(secondVersion));
	}

	protected void assertPrevious(String first, String second) {
		Version firstVersion = Version.getInstance(first);

		assertTrue(
			first + " is not previous than " + second,
			firstVersion.isPreviousVersionThan(second));
	}

}