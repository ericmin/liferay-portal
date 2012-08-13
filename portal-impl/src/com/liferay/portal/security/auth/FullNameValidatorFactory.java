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
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Amos Fong
 */
public class FullNameValidatorFactory {

	public static FullNameValidator getInstance() {
		if (_fullNameValidator == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " + PropsValues.USERS_FULL_NAME_VALIDATOR);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_fullNameValidator =
					(FullNameValidator)InstanceFactory.newInstance(
						classLoader, PropsValues.USERS_FULL_NAME_VALIDATOR);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _fullNameValidator.getClass().getName());
		}

		return _fullNameValidator;
	}

	public static void setInstance(FullNameValidator fullNameValidator) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + fullNameValidator.getClass().getName());
		}

		_fullNameValidator = fullNameValidator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		FullNameValidatorFactory.class);

	private static FullNameValidator _fullNameValidator;

}