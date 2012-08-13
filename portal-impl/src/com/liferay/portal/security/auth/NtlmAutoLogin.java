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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bruno Farache
 */
public class NtlmAutoLogin implements AutoLogin {

	public String[] login(
		HttpServletRequest request, HttpServletResponse response) {

		String[] credentials = null;

		try {
			long companyId = PortalUtil.getCompanyId(request);

			if (!AuthSettingsUtil.isNtlmEnabled(companyId)) {
				return credentials;
			}

			String screenName = (String)request.getAttribute(
				WebKeys.NTLM_REMOTE_USER);

			if (screenName == null) {
				return credentials;
			}

			request.removeAttribute(WebKeys.NTLM_REMOTE_USER);

			User user = PortalLDAPImporterUtil.importLDAPUserByScreenName(
				companyId, screenName);

			if (user != null) {
				String redirect = ParamUtil.getString(request, "redirect");

				if (Validator.isNotNull(redirect)) {
					request.setAttribute(
						AutoLogin.AUTO_LOGIN_REDIRECT_AND_CONTINUE, redirect);
				}

				credentials = new String[3];

				credentials[0] = String.valueOf(user.getUserId());
				credentials[1] = user.getPassword();
				credentials[2] = Boolean.TRUE.toString();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return credentials;
	}

	private static Log _log = LogFactoryUtil.getLog(NtlmAutoLogin.class);

}