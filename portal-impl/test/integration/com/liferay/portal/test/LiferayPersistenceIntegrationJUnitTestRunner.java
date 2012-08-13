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

package com.liferay.portal.test;

import com.liferay.portal.util.InitUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.InitializationError;

/**
 * @author Miguel Pastor
 */
public class LiferayPersistenceIntegrationJUnitTestRunner
	extends LiferayIntegrationJUnitTestRunner {

	public LiferayPersistenceIntegrationJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);
	}

	@Override
	protected void loadSpringContext() {
		List<String> extraConfigLocations = new ArrayList<String>(1);

		extraConfigLocations.add("META-INF/test-persistence-spring.xml");

		InitUtil.initWithSpring(extraConfigLocations);
	}

}