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

package com.liferay.portal.webserver;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.documentlibrary.service.BaseDLAppTestCase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Alexander Chow
 */
public abstract class BaseWebServerTestCase extends BaseDLAppTestCase {

	public MockHttpServletResponse service(
			String method, String path, Map<String, String> headers,
			Map<String, String> params, User user, byte[] data)
		throws Exception {

		if (headers == null) {
			headers = new HashMap<String, String>();
		}

		if (params == null) {
			params = new HashMap<String, String>();
		}

		if (user == null) {
			user = TestPropsValues.getUser();
		}

		String requestURI =
			_CONTEXT_PATH + _SERVLET_PATH + _PATH_INFO_PREFACE + path;

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest(method, requestURI);

		mockHttpServletRequest.setAttribute(WebKeys.USER, user);
		mockHttpServletRequest.setContextPath(_CONTEXT_PATH);
		mockHttpServletRequest.setParameters(params);
		mockHttpServletRequest.setPathInfo(_PATH_INFO_PREFACE + path);
		mockHttpServletRequest.setServletPath(_SERVLET_PATH);

		if (data != null) {
			mockHttpServletRequest.setContent(data);

			String contentType = headers.remove(HttpHeaders.CONTENT_TYPE);

			if (contentType != null) {
				mockHttpServletRequest.setContentType(contentType);
			}
			else {
				mockHttpServletRequest.setContentType(ContentTypes.TEXT_PLAIN);
			}
		}

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			mockHttpServletRequest.addHeader(key, value);
		}

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		mockHttpServletResponse.setCharacterEncoding(StringPool.UTF8);

		Servlet httpServlet = getServlet();

		httpServlet.service(mockHttpServletRequest, mockHttpServletResponse);

		return mockHttpServletResponse;
	}

	protected Servlet getServlet() {
		return new WebServerServlet();
	}

	private static final String _CONTEXT_PATH = "/documents";

	private static final String _PATH_INFO_PREFACE = "";

	private static final String _SERVLET_PATH = "";

}