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

package com.liferay.portal.kernel.util;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jorge Ferrer
 */
public class ProgressTracker {

	public static final String PERCENT =
		ProgressTracker.class.getName() + "_PERCENT";

	public ProgressTracker(HttpServletRequest request, String progressId) {
		_request = request;
		_progressId = progressId;
	}

	public ProgressTracker(PortletRequest portletRequest, String progressId) {
		_portletRequest = portletRequest;
		_progressId = progressId;
	}

	public void finish() {
		if (_request != null) {
			HttpSession session = _request.getSession();

			session.removeAttribute(PERCENT + _progressId);
		}
		else {
			PortletSession portletSession = _portletRequest.getPortletSession();

			portletSession.removeAttribute(
				PERCENT + _progressId, PortletSession.APPLICATION_SCOPE);
		}
	}

	public int getProgress() {
		if (_request != null) {
			HttpSession session = _request.getSession();

			return (Integer)session.getAttribute(PERCENT + _progressId);
		}
		else {
			PortletSession portletSession = _portletRequest.getPortletSession();

			return (Integer)portletSession.getAttribute(PERCENT + _progressId);
		}
	}

	public void start() {
		updateProgress(1);
	}

	public void updateProgress(int percentage) {
		if (_request != null) {
			HttpSession session = _request.getSession();

			session.setAttribute(
				PERCENT + _progressId, new Integer(percentage));
		}
		else {
			PortletSession portletSession = _portletRequest.getPortletSession();

			portletSession.setAttribute(
				PERCENT + _progressId, new Integer(percentage),
				PortletSession.APPLICATION_SCOPE);
		}
	}

	private PortletRequest _portletRequest;
	private String _progressId;
	private HttpServletRequest _request;

}