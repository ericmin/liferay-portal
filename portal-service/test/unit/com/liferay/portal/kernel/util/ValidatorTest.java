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
 * @author Shinn Lok
 */
public class ValidatorTest extends TestCase {

	public void testIsNull() throws Exception {
		String[] nullStrings = {
			null, "", "  ", "null", " null", "null ", "  null  "
		};

		for (String nullString : nullStrings) {
			assertTrue(Validator.isNull(nullString));
		}

		String[] notNullStrings = {
			"a", "anull", "nulla", " anull", " nulla ","  null  a"
		};

		for (String notNullString : notNullStrings) {
			assertFalse(Validator.isNull(notNullString));
		}
	}

	public void testIsValidEmailAddress() throws Exception {
		String[] validEmailAddresses = {
			"test@liferay.com", "test123@liferay.com", "test.user@liferay.com",
			"test@liferay.com.ch", "test!@liferay.com", "test#@liferay.com",
			"test$@liferay.com", "test%@liferay.com", "test&@liferay.com",
			"test'@liferay.com", "test*@liferay.com", "test+@liferay.com",
			"test-@liferay.com", "test/@liferay.com", "test=@liferay.com",
			"test?@liferay.com", "test^@liferay.com", "test_@liferay.com",
			"test`@liferay.com", "test{@liferay.com", "test|@liferay.com",
			"test{@liferay.com", "test~@liferay.com"
		};

		for (String validEmailAddress : validEmailAddresses) {
			if (!Validator.isEmailAddress(validEmailAddress)) {
				fail(validEmailAddress);
			}
		}

		String[] invalidEmailAddresses = {
			"test", "liferay.com", "@liferay.com", "test(@liferay.com",
			"test)@liferay.com", "test,@liferay.com", ".test@liferay.com",
			"test.@liferay.com", "te..st@liferay.com", "test user@liferay.com",
			"test@-liferay.com", "test@liferay"
		};

		for (String invalidEmailAddress : invalidEmailAddresses) {
			if (Validator.isEmailAddress(invalidEmailAddress)) {
				fail(invalidEmailAddress);
			}
		}
	}

	public void testIsValidHostName() throws Exception {
		String[] validHostNames = {
			"localhost", "127.0.0.1", "10.10.10.1", "abc.com", "9to5.net",
			"liferay.com", "www.liferay.com", "www.liferay.co.uk", "::1",
			"[abcd:1234:ef01:2345:6789:0123:4567]"
		};

		for (String validHostName : validHostNames) {
			if (!Validator.isHostName(validHostName)) {
				fail(validHostName);
			}
		}

		String[] invalidHostNames = {
			"(999.999.999)", "123_456_789_012", "www.$dollar$.com",
			"{abcd:1234:ef01:2345:6789:0123:4567}"
		};

		for (String invalidHostName : invalidHostNames) {
			if (Validator.isHostName(invalidHostName)) {
				fail(invalidHostName);
			}
		}
	}

}