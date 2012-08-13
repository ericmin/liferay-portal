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

package com.liferay.portal.poller;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.PollerHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edward Han
 */
public class PollerRequestHandlerUtil {

	public static PollerHeader getPollerHeader(String pollerRequestString) {
		return getPollerRequestHandler().getPollerHeader(pollerRequestString);
	}

	public static PollerRequestHandler getPollerRequestHandler() {
		return _pollerRequestHandler;
	}

	public static JSONObject processRequest(
			HttpServletRequest request, String pollerRequestString)
		throws Exception {

		return getPollerRequestHandler().processRequest(
			request, pollerRequestString);
	}

	public void setPollerRequestHandler(
		PollerRequestHandler pollerRequestHandler) {

		_pollerRequestHandler = pollerRequestHandler;
	}

	private static PollerRequestHandler _pollerRequestHandler;

}