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
public class EmailAddressGeneratorFactory {

	public static EmailAddressGenerator getInstance() {
		if (_emailAddressGenerator == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Instantiate " + PropsValues.USERS_EMAIL_ADDRESS_GENERATOR);
			}

			ClassLoader classLoader =
				PACLClassLoaderUtil.getPortalClassLoader();

			try {
				_emailAddressGenerator =
					(EmailAddressGenerator)InstanceFactory.newInstance(
						classLoader, PropsValues.USERS_EMAIL_ADDRESS_GENERATOR);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Return " + _emailAddressGenerator.getClass().getName());
		}

		return _emailAddressGenerator;
	}

	public static void setInstance(
		EmailAddressGenerator emailAddressGenerator) {

		if (_log.isDebugEnabled()) {
			_log.debug("Set " + emailAddressGenerator.getClass().getName());
		}

		_emailAddressGenerator = emailAddressGenerator;
	}

	private static Log _log = LogFactoryUtil.getLog(
		EmailAddressGeneratorFactory.class);

	private static EmailAddressGenerator _emailAddressGenerator;

}