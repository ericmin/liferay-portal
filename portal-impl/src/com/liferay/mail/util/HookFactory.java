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

package com.liferay.mail.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class HookFactory {

	public static Hook getInstance() {
		if (_hook == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Instantiate " + PropsValues.MAIL_HOOK_IMPL);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_hook = (Hook)classLoader.loadClass(
					PropsValues.MAIL_HOOK_IMPL).newInstance();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _hook.getClass().getName());
		}

		return _hook;
	}

	public static void setInstance(Hook hook) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + hook.getClass().getName());
		}

		_hook = hook;
	}

	private static Log _log = LogFactoryUtil.getLog(HookFactory.class);

	private static Hook _hook;

}