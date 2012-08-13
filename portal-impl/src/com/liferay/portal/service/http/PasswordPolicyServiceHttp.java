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

package com.liferay.portal.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.PasswordPolicyServiceUtil;

/**
 * <p>
 * This class provides a HTTP utility for the
 * {@link com.liferay.portal.service.PasswordPolicyServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link com.liferay.portal.security.auth.HttpPrincipal} parameter.
 * </p>
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PasswordPolicyServiceSoap
 * @see       com.liferay.portal.security.auth.HttpPrincipal
 * @see       com.liferay.portal.service.PasswordPolicyServiceUtil
 * @generated
 */
public class PasswordPolicyServiceHttp {
	public static com.liferay.portal.model.PasswordPolicy addPasswordPolicy(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.lang.String description, boolean changeable,
		boolean changeRequired, long minAge, boolean checkSyntax,
		boolean allowDictionaryWords, int minAlphanumeric, int minLength,
		int minLowerCase, int minNumbers, int minSymbols, int minUpperCase,
		boolean history, int historyCount, boolean expireable, long maxAge,
		long warningTime, int graceLimit, boolean lockout, int maxFailure,
		long lockoutDuration, long resetFailureCount, long resetTicketMaxAge)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class.getName(),
					"addPasswordPolicy", _addPasswordPolicyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					description, changeable, changeRequired, minAge,
					checkSyntax, allowDictionaryWords, minAlphanumeric,
					minLength, minLowerCase, minNumbers, minSymbols,
					minUpperCase, history, historyCount, expireable, maxAge,
					warningTime, graceLimit, lockout, maxFailure,
					lockoutDuration, resetFailureCount, resetTicketMaxAge);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deletePasswordPolicy(HttpPrincipal httpPrincipal,
		long passwordPolicyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class.getName(),
					"deletePasswordPolicy", _deletePasswordPolicyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.model.PasswordPolicy updatePasswordPolicy(
		HttpPrincipal httpPrincipal, long passwordPolicyId,
		java.lang.String name, java.lang.String description,
		boolean changeable, boolean changeRequired, long minAge,
		boolean checkSyntax, boolean allowDictionaryWords, int minAlphanumeric,
		int minLength, int minLowerCase, int minNumbers, int minSymbols,
		int minUpperCase, boolean history, int historyCount,
		boolean expireable, long maxAge, long warningTime, int graceLimit,
		boolean lockout, int maxFailure, long lockoutDuration,
		long resetFailureCount, long resetTicketMaxAge)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		try {
			MethodKey methodKey = new MethodKey(PasswordPolicyServiceUtil.class.getName(),
					"updatePasswordPolicy", _updatePasswordPolicyParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					passwordPolicyId, name, description, changeable,
					changeRequired, minAge, checkSyntax, allowDictionaryWords,
					minAlphanumeric, minLength, minLowerCase, minNumbers,
					minSymbols, minUpperCase, history, historyCount,
					expireable, maxAge, warningTime, graceLimit, lockout,
					maxFailure, lockoutDuration, resetFailureCount,
					resetTicketMaxAge);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				if (e instanceof com.liferay.portal.kernel.exception.SystemException) {
					throw (com.liferay.portal.kernel.exception.SystemException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.model.PasswordPolicy)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PasswordPolicyServiceHttp.class);
	private static final Class<?>[] _addPasswordPolicyParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class, boolean.class,
			boolean.class, long.class, boolean.class, boolean.class, int.class,
			int.class, int.class, int.class, int.class, int.class, boolean.class,
			int.class, boolean.class, long.class, long.class, int.class,
			boolean.class, int.class, long.class, long.class, long.class
		};
	private static final Class<?>[] _deletePasswordPolicyParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updatePasswordPolicyParameterTypes2 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			boolean.class, boolean.class, long.class, boolean.class,
			boolean.class, int.class, int.class, int.class, int.class, int.class,
			int.class, boolean.class, int.class, boolean.class, long.class,
			long.class, int.class, boolean.class, int.class, long.class,
			long.class, long.class
		};
}