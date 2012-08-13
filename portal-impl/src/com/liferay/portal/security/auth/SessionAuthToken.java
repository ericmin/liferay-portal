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

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.Encryptor;
import com.liferay.util.PwdGenerator;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Amos Fong
 */
public class SessionAuthToken implements AuthToken {

	public void check(HttpServletRequest request) throws PrincipalException {
		if (isIgnoreAction(request) || isIgnorePortlet(request)) {
			return;
		}

		String requestAuthenticationToken = ParamUtil.getString(
			request, "p_auth");

		String sessionAuthenticationToken = getSessionAuthenticationToken(
			request, _PORTAL);

		String propertiesAuthenticatonTokenSharedSecret = Encryptor.digest(
			PropsValues.AUTH_TOKEN_SHARED_SECRET);

		String requestAuthenticatonTokenSharedSecret = ParamUtil.getString(
			request, "p_auth_secret");

		if (!requestAuthenticationToken.equals(sessionAuthenticationToken) &&
			!requestAuthenticatonTokenSharedSecret.equals(
				propertiesAuthenticatonTokenSharedSecret)) {

			throw new PrincipalException("Invalid authentication token");
		}
	}

	public String getToken(HttpServletRequest request) {
		return getSessionAuthenticationToken(request, _PORTAL);
	}

	public String getToken(
		HttpServletRequest request, long plid, String portletId) {

		return getSessionAuthenticationToken(
			request, PortletPermissionUtil.getPrimaryKey(plid, portletId));
	}

	protected String getSessionAuthenticationToken(
		HttpServletRequest request, String key) {

		HttpSession session = request.getSession();

		String tokenKey = WebKeys.AUTHENTICATION_TOKEN.concat(key);

		String sessionAuthenticationToken = (String)session.getAttribute(
			tokenKey);

		if (Validator.isNull(sessionAuthenticationToken)) {
			sessionAuthenticationToken = PwdGenerator.getPassword();

			session.setAttribute(tokenKey, sessionAuthenticationToken);
		}

		return sessionAuthenticationToken;
	}

	protected boolean isIgnoreAction(HttpServletRequest request) {
		long companyId = PortalUtil.getCompanyId(request);

		String ppid = ParamUtil.getString(request, "p_p_id");

		String portletNamespace = PortalUtil.getPortletNamespace(ppid);

		String strutsAction = ParamUtil.getString(
			request, portletNamespace + "struts_action");

		return isIgnoreAction(companyId, ppid, strutsAction);
	}

	protected boolean isIgnoreAction(
		long companyId, String ppid, String strutsAction) {

		Set<String> authTokenIgnoreActions =
			PortalUtil.getAuthTokenIgnoreActions();

		if (!authTokenIgnoreActions.contains(strutsAction)) {
			return false;
		}

		try {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				companyId, ppid);

			if (portlet == null) {
				return false;
			}

			String strutsPath = strutsAction.substring(
				1, strutsAction.lastIndexOf(CharPool.SLASH));

			if (strutsPath.equals(portlet.getStrutsPath()) ||
				strutsPath.equals(portlet.getParentStrutsPath())) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	protected boolean isIgnorePortlet(HttpServletRequest request) {
		String ppid = ParamUtil.getString(request, "p_p_id");

		return isIgnorePortlet(ppid);
	}

	protected boolean isIgnorePortlet(String portletId) {
		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		Set<String> authTokenIgnorePortlets =
			PortalUtil.getAuthTokenIgnorePortlets();

		return authTokenIgnorePortlets.contains(rootPortletId);
	}

	private static final String _PORTAL = "PORTAL";

}