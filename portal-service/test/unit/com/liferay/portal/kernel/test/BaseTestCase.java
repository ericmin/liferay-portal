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

package com.liferay.portal.kernel.test;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.util.PropsImpl;

/**
 * @author Brian Wing Shun Chan
 */
public class BaseTestCase extends TestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();

		Class<?> clazz = getClass();

		PortalClassLoaderUtil.setClassLoader(clazz.getClassLoader());

		PropsUtil.setProps(new PropsImpl());
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		PortalClassLoaderUtil.setClassLoader(null);
	}

}