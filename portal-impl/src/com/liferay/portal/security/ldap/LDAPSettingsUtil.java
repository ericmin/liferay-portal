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

package com.liferay.portal.security.ldap;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Properties;

/**
 * @author Edward Han
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class LDAPSettingsUtil {

	public static String getAuthSearchFilter(
			long ldapServerId, long companyId, String emailAddress,
			String screenName, String userId)
		throws SystemException {

		String postfix = getPropertyPostfix(ldapServerId);

		String filter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_AUTH_SEARCH_FILTER + postfix);

		if (_log.isDebugEnabled()) {
			_log.debug("Search filter before transformation " + filter);
		}

		filter = StringUtil.replace(
			filter,
			new String[] {
				"@company_id@", "@email_address@", "@screen_name@", "@user_id@"
			},
			new String[] {
				String.valueOf(companyId), emailAddress, screenName, userId
			});

		if (_log.isDebugEnabled()) {
			_log.debug("Search filter after transformation " + filter);
		}

		return filter;
	}

	public static Properties getContactExpandoMappings(
			long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		Properties contactExpandoMappings = PropertiesUtil.load(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_CONTACT_CUSTOM_MAPPINGS + postfix));

		LogUtil.debug(_log, contactExpandoMappings);

		return contactExpandoMappings;
	}

	public static Properties getContactMappings(
			long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		Properties contactMappings = PropertiesUtil.load(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_CONTACT_MAPPINGS + postfix));

		LogUtil.debug(_log, contactMappings);

		return contactMappings;
	}

	public static Properties getGroupMappings(long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		Properties groupMappings = PropertiesUtil.load(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_GROUP_MAPPINGS + postfix));

		LogUtil.debug(_log, groupMappings);

		return groupMappings;
	}

	public static String getPropertyPostfix(long ldapServerId) {
		return StringPool.PERIOD + ldapServerId;
	}

	public static Properties getUserExpandoMappings(
			long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		Properties userExpandoMappings = PropertiesUtil.load(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_USER_CUSTOM_MAPPINGS + postfix));

		LogUtil.debug(_log, userExpandoMappings);

		return userExpandoMappings;
	}

	public static Properties getUserMappings(long ldapServerId, long companyId)
		throws Exception {

		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

		Properties userMappings = PropertiesUtil.load(
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_USER_MAPPINGS + postfix));

		LogUtil.debug(_log, userMappings);

		return userMappings;
	}

	public static boolean isExportEnabled(long companyId)
		throws SystemException {

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_EXPORT_ENABLED,
				PropsValues.LDAP_EXPORT_ENABLED)) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isExportGroupEnabled(long companyId)
		throws SystemException {

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_EXPORT_GROUP_ENABLED,
				PropsValues.LDAP_EXPORT_GROUP_ENABLED)) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isImportEnabled(long companyId)
		throws SystemException {

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_IMPORT_ENABLED,
				PropsValues.LDAP_IMPORT_ENABLED)) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isImportOnStartup(long companyId)
		throws SystemException {

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_IMPORT_ON_STARTUP)) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isPasswordPolicyEnabled(long companyId)
		throws SystemException {

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_PASSWORD_POLICY_ENABLED,
				PropsValues.LDAP_PASSWORD_POLICY_ENABLED)) {

			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LDAPSettingsUtil.class);

}