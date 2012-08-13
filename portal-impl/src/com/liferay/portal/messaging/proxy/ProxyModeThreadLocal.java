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

package com.liferay.portal.messaging.proxy;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

/**
 * @author Shuyang Zhou
 */
public class ProxyModeThreadLocal {

	public static boolean isForceSync() {
		return _forceSync.get();
	}

	public static void setForceSync(boolean forceSync) {
		_forceSync.set(forceSync);
	}

	private static ThreadLocal<Boolean> _forceSync =
		new AutoResetThreadLocal<Boolean>(
			ProxyModeThreadLocal.class + "_forceSync", Boolean.FALSE);

}