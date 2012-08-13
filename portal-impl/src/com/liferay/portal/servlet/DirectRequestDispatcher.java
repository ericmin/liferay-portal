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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
public class DirectRequestDispatcher implements RequestDispatcher {

	public DirectRequestDispatcher(Servlet servlet, String queryString) {
		_servlet = servlet;

		String[] parameters = StringUtil.split(queryString, CharPool.AMPERSAND);

		if (parameters.length > 0) {
			_parameters = new HashMap<String, String[]>();

			for (String parameter : parameters) {
				String[] parameterParts = StringUtil.split(
					parameter, CharPool.EQUAL);

				String name = parameterParts[0];
				String value = StringPool.BLANK;

				if (parameterParts.length == 2) {
					value = parameterParts[1];
				}

				String[] values = _parameters.get(name);

				if (values == null) {
					_parameters.put(name, new String[] {value});
				}
				else {
					String[] newValues = new String[values.length + 1];

					System.arraycopy(values, 0, newValues, 0, values.length);

					newValues[newValues.length - 1] = value;

					_parameters.put(name, newValues);
				}
			}
		}
	}

	public void forward(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		if (_parameters != null) {
			servletRequest = new DynamicServletRequest(
				(HttpServletRequest)servletRequest, _parameters);
		}

		_servlet.service(servletRequest, servletResponse);
	}

	public void include(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		if (_parameters != null) {
			servletRequest = new DynamicServletRequest(
				(HttpServletRequest)servletRequest, _parameters);
		}

		_servlet.service(servletRequest, servletResponse);
	}

	private Map<String, String[]> _parameters;
	private Servlet _servlet;

}