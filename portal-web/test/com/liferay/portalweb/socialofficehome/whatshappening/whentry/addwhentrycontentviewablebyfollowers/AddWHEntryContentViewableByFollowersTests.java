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

package com.liferay.portalweb.socialofficehome.whatshappening.whentry.addwhentrycontentviewablebyfollowers;

import com.liferay.portalweb.portal.BaseTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Brian Wing Shun Chan
 */
public class AddWHEntryContentViewableByFollowersTests extends BaseTestSuite {
	public static Test suite() {
		TestSuite testSuite = new TestSuite();
		testSuite.addTestSuite(AddSOUserTest.class);
		testSuite.addTestSuite(AddWHEntryContentViewableByFollowersTest.class);
		testSuite.addTestSuite(ViewWHEntryContentViewableByFollowersTest.class);
		testSuite.addTestSuite(SignOutTest.class);
		testSuite.addTestSuite(SOUs_SignInTest.class);
		testSuite.addTestSuite(SOUs_ViewWHContentViewableByFollowersTest.class);
		testSuite.addTestSuite(SOUs_AddFollowingCCUserTest.class);
		testSuite.addTestSuite(SOUs_ViewFollowingWHContentViewableByFollowersTest.class);
		testSuite.addTestSuite(SignOutTest.class);
		testSuite.addTestSuite(SOSignInTest.class);
		testSuite.addTestSuite(TearDownWHEntryContentTest.class);
		testSuite.addTestSuite(TearDownUserTest.class);

		return testSuite;
	}
}