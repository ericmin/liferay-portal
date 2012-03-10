/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portalweb.socialofficeprofile.mycontacts.mycontact.filtermcallcontacts;

import com.liferay.portalweb.portal.BaseTestSuite;
import com.liferay.portalweb.portal.controlpanel.users.user.signin.SignOutSOTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class FilterMCAllContactsTests extends BaseTestSuite {
	public static Test suite() {
		TestSuite testSuite = new TestSuite();
		testSuite.addTestSuite(AddUserSOFriendTest.class);
		testSuite.addTestSuite(AddUserSOCoworkerTest.class);
		testSuite.addTestSuite(AddUserSOFollowerTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOFr_NewPasswordTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOFr_SignInTest.class);
		testSuite.addTestSuite(SOFr_AddAsFriendCCActionsTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOCo_NewPasswordTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOCo_SignInTest.class);
		testSuite.addTestSuite(SOCo_AddAsCoworkerCCActionsTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOFo_NewPasswordTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOFo_SignInTest.class);
		testSuite.addTestSuite(SOFo_AddAsFollowerCCActionsTest.class);
		testSuite.addTestSuite(SignOutSOTest.class);
		testSuite.addTestSuite(SOSignInTest.class);
		testSuite.addTestSuite(ConfirmNotificationsMultipleTest.class);
		testSuite.addTestSuite(FilterMCAllContactsTest.class);
		testSuite.addTestSuite(TearDownSOUserTest.class);

		return testSuite;
	}
}