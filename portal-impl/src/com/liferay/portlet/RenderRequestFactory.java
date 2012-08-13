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

package com.liferay.portlet;

import com.liferay.portal.model.Portlet;

import javax.portlet.PortletContext;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class RenderRequestFactory {

	public static RenderRequestImpl create(
			HttpServletRequest request, Portlet portlet,
			InvokerPortlet invokerPortlet, PortletContext portletContext,
			WindowState windowState, PortletMode portletMode,
			PortletPreferences preferences)
		throws Exception {

		return create(
			request, portlet, invokerPortlet, portletContext, windowState,
			portletMode, preferences, 0);
	}

	public static RenderRequestImpl create(
			HttpServletRequest request, Portlet portlet,
			InvokerPortlet invokerPortlet, PortletContext portletContext,
			WindowState windowState, PortletMode portletMode,
			PortletPreferences preferences, long plid)
		throws Exception {

		RenderRequestImpl renderRequestImpl = new RenderRequestImpl();

		renderRequestImpl.init(
			request, portlet, invokerPortlet, portletContext, windowState,
			portletMode, preferences, plid);

		return renderRequestImpl;
	}

}