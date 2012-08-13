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

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Edward Han
 */
public class LDAPUserTransactionThreadLocal {

	public static boolean isOriginatesFromLDAP() {
		return _originatesFromLDAP.get().booleanValue();
	}

	public static void setOriginatesFromLDAP(boolean originatesFromLDAP) {
		_originatesFromLDAP.set(originatesFromLDAP);
	}

	private static ThreadLocal<Boolean> _originatesFromLDAP =
		new InitialThreadLocal<Boolean>(
			LDAPUserTransactionThreadLocal.class + "._originatesFromLDAP",
			false);

}