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

package com.liferay.portal.jndi.pacl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Hashtable;

import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLInitialContextFactoryBuilder
	implements InitialContextFactoryBuilder {

	public InitialContextFactory createInitialContextFactory(
		Hashtable<?, ?> environment) {

		if (_log.isDebugEnabled()) {
			_log.debug("Creating " + PACLInitialContextFactory.class.getName());
		}

		return new PACLInitialContextFactory(
			_initialContextFactoryBuilder, environment);
	}

	public void setInitialContextFactoryBuilder(
		InitialContextFactoryBuilder initialContextFactoryBuilder) {

		_initialContextFactoryBuilder = initialContextFactoryBuilder;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PACLInitialContextFactoryBuilder.class.getName());

	private InitialContextFactoryBuilder _initialContextFactoryBuilder;

}