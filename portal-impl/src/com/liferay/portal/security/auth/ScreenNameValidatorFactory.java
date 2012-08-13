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
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class ScreenNameValidatorFactory {

	public static ScreenNameValidator getInstance() {
		if (_screenNameValidator == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " + PropsValues.USERS_SCREEN_NAME_VALIDATOR);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_screenNameValidator =
					(ScreenNameValidator)classLoader.loadClass(
						PropsValues.USERS_SCREEN_NAME_VALIDATOR).newInstance();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _screenNameValidator.getClass().getName());
		}

		return _screenNameValidator;
	}

	public static void setInstance(ScreenNameValidator screenNameValidator) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + screenNameValidator.getClass().getName());
		}

		_screenNameValidator = screenNameValidator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ScreenNameValidatorFactory.class);

	private static ScreenNameValidator _screenNameValidator;

}