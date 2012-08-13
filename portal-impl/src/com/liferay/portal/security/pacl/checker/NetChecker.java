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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaDetector;

import java.security.Permission;

import sun.reflect.Reflection;

/**
 * @author Brian Wing Shun Chan
 */
public class NetChecker extends BaseChecker {

	public void afterPropertiesSet() {
	}

	public void checkPermission(Permission permission) {
		String name = permission.getName();

		if (name.equals(NET_PERMISSION_GET_PROXY_SELECTOR)) {
			if (!hasGetProxySelector()) {
				throwSecurityException(_log, "Attempted to get proxy selector");
			}
		}
		else if (name.equals(NET_PERMISSION_SPECIFY_STREAM_HANDLER)) {

			// TODO

		}
	}

	protected boolean hasGetProxySelector() {
		if (JavaDetector.isJDK7()) {
			Class<?> callerClass8 = Reflection.getCallerClass(8);

			String className8 = callerClass8.getName();

			if (className8.startsWith(_CLASS_NAME_SOCKS_SOCKET_IMPL) &&
				CheckerUtil.isAccessControllerDoPrivileged(9)) {

				logGetProxySelector(callerClass8, 8);

				return true;
			}
		}
		else {
			Class<?> callerClass7 = Reflection.getCallerClass(7);

			String className7 = callerClass7.getName();

			if (className7.startsWith(_CLASS_NAME_SOCKS_SOCKET_IMPL) &&
				CheckerUtil.isAccessControllerDoPrivileged(8)) {

				logGetProxySelector(callerClass7, 7);

				return true;
			}
		}

		return false;
	}

	protected void logGetProxySelector(Class<?> callerClass, int frame) {
		if (_log.isInfoEnabled()) {
			_log.info(
				"Allowing frame " + frame + " with caller " + callerClass +
					" to get the proxy selector");
		}
	}

	private static final String _CLASS_NAME_SOCKS_SOCKET_IMPL =
		"java.net.SocksSocketImpl$";

	private static Log _log = LogFactoryUtil.getLog(NetChecker.class);

}