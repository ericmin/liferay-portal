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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AuthTokenUtil {

	public static void check(HttpServletRequest request)
		throws PortalException {

		getAuthToken().check(request);
	}

	public static AuthToken getAuthToken() {
		PortalRuntimePermission.checkGetBeanProperty(AuthTokenUtil.class);

		return _authToken;
	}

	public static String getToken(HttpServletRequest request) {
		return getAuthToken().getToken(request);
	}

	public static String getToken(
		HttpServletRequest request, long plid, String portletId) {

		return getAuthToken().getToken(request, plid, portletId);
	}

	public void setAuthToken(AuthToken authToken) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_authToken = authToken;
	}

	private static AuthToken _authToken;

}