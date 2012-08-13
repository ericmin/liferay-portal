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

package com.liferay.portal.apache.bridges.struts;

import com.liferay.portal.kernel.util.ContextPathUtil;

import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

/**
 * @author Michael Young
 */
public class LiferayServletContext implements ServletContext {

	public LiferayServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	public Object getAttribute(String name) {
		return _servletContext.getAttribute(name);
	}

	public Enumeration<String> getAttributeNames() {
		return _servletContext.getAttributeNames();
	}

	public ServletContext getContext(String uriPath) {
		ServletContext servletContext = _servletContext.getContext(uriPath);

		if (servletContext == _servletContext) {
			return this;
		}
		else {
			return servletContext;
		}
	}

	public String getContextPath() {
		return ContextPathUtil.getContextPath(_servletContext);
	}

	public String getInitParameter(String name) {
		return _servletContext.getInitParameter(name);
	}

	public Enumeration<String> getInitParameterNames() {
		return _servletContext.getInitParameterNames();
	}

	public int getMajorVersion() {
		return _servletContext.getMajorVersion();
	}

	public String getMimeType(String file) {
		return _servletContext.getMimeType(file);
	}

	public int getMinorVersion() {
		return _servletContext.getMinorVersion();
	}

	public RequestDispatcher getNamedDispatcher(String name) {
		RequestDispatcher requestDispatcher =
			_servletContext.getNamedDispatcher(name);

		if (requestDispatcher != null) {
			requestDispatcher = new LiferayRequestDispatcher(
				requestDispatcher, name);
		}

		return requestDispatcher;
	}

	public String getRealPath(String path) {
		return _servletContext.getRealPath(path);
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(path);

		if (requestDispatcher != null) {
			requestDispatcher = new LiferayRequestDispatcher(
				requestDispatcher, path);
		}

		return requestDispatcher;
	}

	public URL getResource(String path) throws MalformedURLException {
		return _servletContext.getResource(path);
	}

	public InputStream getResourceAsStream(String path) {
		return _servletContext.getResourceAsStream(path);
	}

	public Set<String> getResourcePaths(String path) {
		return _servletContext.getResourcePaths(path);
	}

	public String getServerInfo() {
		return _servletContext.getServerInfo();
	}

	public Servlet getServlet(String name) {
		return null;
	}

	public String getServletContextName() {
		return _servletContext.getServletContextName();
	}

	public Enumeration<String> getServletNames() {
		return Collections.enumeration(new ArrayList<String>());
	}

	public Enumeration<Servlet> getServlets() {
		return Collections.enumeration(new ArrayList<Servlet>());
	}

	public void log(Exception exception, String message) {
		_servletContext.log(message, exception);
	}

	public void log(String message) {
		_servletContext.log(message);
	}

	public void log(String message, Throwable t) {
		_servletContext.log(message, t);
	}

	public void removeAttribute(String name) {
		_servletContext.removeAttribute(name);
	}

	public void setAttribute(String name, Object value) {
		_servletContext.setAttribute(name, value);
	}

	@Override
	public String toString() {
		return _servletContext.toString();
	}

	private ServletContext _servletContext;

}