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

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPreferencesThreadLocal {

	public static boolean isStrict() {
		return _strict.get().booleanValue();
	}

	public static void setStrict(boolean strict) {
		_strict.set(strict);
	}

	private static ThreadLocal<Boolean> _strict =
		new InitialThreadLocal<Boolean>(
			PortletPreferencesThreadLocal.class + "._strict", false);

}