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
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
public class RequestHeaderAutoLogin implements AutoLogin {

	public String[] login(
		HttpServletRequest request, HttpServletResponse response) {

		String[] credentials = null;

		try {
			long companyId = PortalUtil.getCompanyId(request);

			String screenName = request.getHeader(
				HttpHeaders.LIFERAY_SCREEN_NAME);

			if (Validator.isNull(screenName)) {
				return credentials;
			}

			User user = null;

			if (PrefsPropsUtil.getBoolean(
					companyId, PropsKeys.REQUEST_HEADER_AUTH_IMPORT_FROM_LDAP,
					PropsValues.REQUEST_HEADER_AUTH_IMPORT_FROM_LDAP)) {

				try {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, StringPool.BLANK, screenName);
				}
				catch (Exception e) {
				}
			}

			if (user == null) {
				user = UserLocalServiceUtil.getUserByScreenName(
					companyId, screenName);
			}

			credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.TRUE.toString();

			return credentials;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return credentials;
	}

	private static Log _log = LogFactoryUtil.getLog(
		RequestHeaderAutoLogin.class);

}