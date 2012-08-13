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

import com.liferay.portal.servlet.MainServlet;

import java.io.File;

import javax.servlet.ServletException;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/**
 * @author Miguel Pastor
 */
public class MainServletExecutionTestListener
	extends EnvironmentExecutionTestListener {

	@Override
	public void runBeforeClass(TestContext testContext) {
		super.runBeforeClass(testContext);

		MockServletContext mockServletContext = new MockServletContext(
			getResourceBasePath(), new FileSystemResourceLoader());

		MockServletConfig mockServletConfig = new MockServletConfig(
			mockServletContext);

		_mainServlet = new MainServlet();

		try {
			_mainServlet.init(mockServletConfig);
		}
		catch (ServletException e) {
			throw new RuntimeException(
				"The main servlet could not be initialized");
		}
	}

	protected String getResourceBasePath() {
		File file = new File("portal-web/docroot");

		return "file:" + file.getAbsolutePath();
	}

	private MainServlet _mainServlet;

}