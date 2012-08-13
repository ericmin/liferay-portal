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

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * @author Miguel Pastor
 */
public class LiferayIntegrationJUnitTestRunner extends BlockJUnit4ClassRunner {

	public LiferayIntegrationJUnitTestRunner(Class<?> clazz)
		throws InitializationError {

		super(clazz);

		if (System.getProperty("external-properties") == null) {
			System.setProperty("external-properties", "portal-test.properties");
		}

		loadSpringContext();

		_testContextHandler = new TestContextHandler(clazz);
	}

	protected void loadSpringContext() {
		InitUtil.initWithSpring();
	}

	@Override
	protected Statement withAfterClasses(Statement statement) {
		Statement withAfterClassesStatement = super.withAfterClasses(statement);

		return new RunAfterTestClassesCallback(
			withAfterClassesStatement, _testContextHandler);
	}

	/**
	 * @deprecated
	 */
	@Override
	protected Statement withAfters(
		FrameworkMethod frameworkMethod, Object instance, Statement statement) {

		Statement withAftersStatement = super.withAfters(
			frameworkMethod, instance, statement);

		return new RunAfterTestMethodCallback(
			instance, frameworkMethod.getMethod(), withAftersStatement,
			_testContextHandler);
	}

	@Override
	protected Statement withBeforeClasses(Statement statement) {
		Statement withBeforeClassesStatement = super.withBeforeClasses(
			statement);

		return new RunBeforeTestClassesCallback(
			withBeforeClassesStatement, _testContextHandler);
	}

	/**
	 * @deprecated
	 */
	@Override
	protected Statement withBefores(
		FrameworkMethod frameworkMethod, Object instance, Statement statement) {

		Statement withBeforesStatement = super.withBefores(
			frameworkMethod, instance, statement);

		return new RunBeforeTestMethodCallback(
			instance, frameworkMethod.getMethod(), withBeforesStatement,
			_testContextHandler);
	}

	private TestContextHandler _testContextHandler;

}