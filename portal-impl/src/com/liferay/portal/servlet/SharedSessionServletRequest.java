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

import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdHttpSession;
import com.liferay.portal.kernel.servlet.filters.compoundsessionid.CompoundSessionIdSplitterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Brian Myunghun Kim
 */
public class SharedSessionServletRequest extends HttpServletRequestWrapper {

	public SharedSessionServletRequest(
		HttpServletRequest request, boolean shared) {

		super(request);

		_portalSession = request.getSession();

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter() &&
			!(_portalSession instanceof CompoundSessionIdHttpSession)) {

			_portalSession = new CompoundSessionIdHttpSession(_portalSession);
		}

		_shared = shared;
	}

	@Override
	public HttpSession getSession() {
		checkPortalSession();

		if (_shared || isPortletConfigurationPortlet()) {
			return _portalSession;
		}
		else {
			return getSharedSessionWrapper(_portalSession, super.getSession());
		}
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (create) {
			checkPortalSession();
		}

		if (_shared || isPortletConfigurationPortlet()) {
			return _portalSession;
		}
		else {
			return getSharedSessionWrapper(
				_portalSession, super.getSession(create));
		}
	}

	public HttpSession getSharedSession() {
		return _portalSession;
	}

	protected void checkPortalSession() {
		try {
			_portalSession.isNew();
		}
		catch (IllegalStateException ise) {
			_portalSession = super.getSession(true);
		}
	}

	protected HttpSession getSharedSessionWrapper(
		HttpSession portalSession, HttpSession portletSession) {

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter() &&
			!(portalSession instanceof CompoundSessionIdHttpSession)) {

			portalSession = new CompoundSessionIdHttpSession(portalSession);
		}

		if (CompoundSessionIdSplitterUtil.hasSessionDelimiter() &&
			!(portletSession instanceof CompoundSessionIdHttpSession)) {

			portletSession = new CompoundSessionIdHttpSession(portletSession);
		}

		if (ServerDetector.isJetty()) {
			return new JettySharedSessionWrapper(portalSession, portletSession);
		}
		else {
			return new SharedSessionWrapper(portalSession, portletSession);
		}
	}

	protected boolean isPortletConfigurationPortlet() {
		String namespace = PortalUtil.getPortletNamespace(
			PortletKeys.PORTLET_CONFIGURATION);

		String portletResource = ParamUtil.getString(
			this, namespace + "portletResource");

		if (Validator.isNull(portletResource)) {
			return false;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)this.getAttribute(
			WebKeys.THEME_DISPLAY);

		Portlet portlet = null;

		try {
			portlet = PortletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletResource);
		}
		catch (Exception e) {
		}

		if (portlet == null) {
			return false;
		}

		PortletApp portletApp = portlet.getPortletApp();

		if (portletApp.isWARFile()) {
			return true;
		}

		return false;
	}

	private HttpSession _portalSession;
	private boolean _shared;

}