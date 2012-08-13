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

package com.liferay.portal.kernel.portlet;

import java.io.IOException;
import java.io.Writer;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletSecurityException;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletURLWrapper implements PortletURL {

	public PortletURLWrapper(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void addProperty(String key, String value) {
		_portletURL.addProperty(key, value);
	}

	public Map<String, String[]> getParameterMap() {
		return _portletURL.getParameterMap();
	}

	public PortletMode getPortletMode() {
		return _portletURL.getPortletMode();
	}

	public WindowState getWindowState() {
		return _portletURL.getWindowState();
	}

	public void removePublicRenderParameter(String name) {
		_portletURL.removePublicRenderParameter(name);
	}

	public void setParameter(String name, String value) {
		_portletURL.setParameter(name, value);
	}

	public void setParameter(String name, String[] values) {
		_portletURL.setParameter(name, values);
	}

	public void setParameters(Map<String, String[]> parameters) {
		_portletURL.setParameters(parameters);
	}

	public void setPortletMode(PortletMode portletMode)
		throws PortletModeException {

		_portletURL.setPortletMode(portletMode);
	}

	public void setProperty(String key, String value) {
		_portletURL.setProperty(key, value);
	}

	public void setSecure(boolean secure) throws PortletSecurityException {
		_portletURL.setSecure(secure);
	}

	public void setWindowState(WindowState windowState)
		throws WindowStateException {

		_portletURL.setWindowState(windowState);
	}

	@Override
	public String toString() {
		return _portletURL.toString();
	}

	public void write(Writer writer) throws IOException {
		_portletURL.write(writer);
	}

	public void write(Writer writer, boolean escapeXML) throws IOException {
		_portletURL.write(writer, escapeXML);
	}

	private PortletURL _portletURL;

}