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

package com.liferay.util.bridges.jsf.sun;

import java.io.InputStream;

import java.net.URL;

import java.security.Principal;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;

import javax.servlet.ServletContext;

/**
 * @author Neil Griffin
 */
public class LiferayServletContextAdapter extends ExternalContext {

	public LiferayServletContextAdapter(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Override
	public void dispatch(String string) {
	}

	@Override
	public String encodeActionURL(String string) {
		return null;
	}

	@Override
	public String encodeNamespace(String string) {
		return null;
	}

	@Override
	public String encodeResourceURL(String string) {
		return null;
	}

	@Override
	public Map<String, Object> getApplicationMap() {
		if (_applicationMap == null) {
			_applicationMap = new LiferayApplicationMap(_servletContext);
		}

		return _applicationMap;
	}

	@Override
	public String getAuthType() {
		return null;
	}

	@Override
	public Object getContext() {
		return null;
	}

	@Override
	public String getInitParameter(String string) {
		return null;
	}

	@Override
	public Map<String, String> getInitParameterMap() {
		return null;
	}

	@Override
	public String getRemoteUser() {
		return null;
	}

	@Override
	public Object getRequest() {
		return null;
	}

	@Override
	public String getRequestContextPath() {
		return null;
	}

	@Override
	public Map<String, Object> getRequestCookieMap() {
		return null;
	}

	@Override
	public Map<String, String> getRequestHeaderMap() {
		return null;
	}

	@Override
	public Map<String, String[]> getRequestHeaderValuesMap() {
		return null;
	}

	@Override
	public Locale getRequestLocale() {
		return null;
	}

	@Override
	public Iterator<Locale> getRequestLocales() {
		return null;
	}

	@Override
	public Map<String, Object> getRequestMap() {
		return null;
	}

	@Override
	public Map<String, String> getRequestParameterMap() {
		return null;
	}

	@Override
	public Iterator<String> getRequestParameterNames() {
		return null;
	}

	@Override
	public Map<String, String[]> getRequestParameterValuesMap() {
		return null;
	}

	@Override
	public String getRequestPathInfo() {
		return null;
	}

	@Override
	public String getRequestServletPath() {
		return null;
	}

	@Override
	public URL getResource(String string) {
		return null;
	}

	@Override
	public InputStream getResourceAsStream(String string) {
		return null;
	}

	@Override
	public Set<String> getResourcePaths(String string) {
		return null;
	}

	@Override
	public Object getResponse() {
		return null;
	}

	@Override
	public Object getSession(boolean b) {
		return null;
	}

	@Override
	public Map<String, Object> getSessionMap() {
		return null;
	}

	@Override
	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public boolean isUserInRole(String string) {
		return false;
	}

	@Override
	public void log(String string) {
	}

	@Override
	public void log(String string, Throwable throwable) {
	}

	@Override
	public void redirect(String string) {
	}

	private LiferayApplicationMap _applicationMap;
	private ServletContext _servletContext;

}