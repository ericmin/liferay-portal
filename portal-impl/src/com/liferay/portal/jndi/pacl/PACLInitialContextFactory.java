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
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.security.lang.PortalSecurityManagerThreadLocal;
import com.liferay.portal.security.pacl.PACLClassUtil;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLPolicyManager;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLInitialContextFactory implements InitialContextFactory {

	public PACLInitialContextFactory(
		InitialContextFactoryBuilder initialContextFactoryBuilder,
		Hashtable<?, ?> environment) {

		_initialContextFactoryBuilder = initialContextFactoryBuilder;

		if (environment != null) {
			_environment = new Hashtable<Object, Object>(environment);
		}
	}

	public Context getInitialContext(Hashtable<?, ?> environment)
		throws NamingException {

		try {
			return doGetInitialContext(environment);
		}
		catch (NamingException ne) {
			throw ne;
		}
		catch (Exception e) {
			NamingException ne = new NamingException();

			ne.initCause(e);

			throw ne;
		}
	}

	protected Context doGetInitialContext(Hashtable<?, ?> environment)
		throws Exception {

		InitialContextFactory initialContextFactory = null;

		if (_initialContextFactoryBuilder != null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Use " + _initialContextFactoryBuilder.getClass() +
						" to instantiate initial context factory");
			}

			initialContextFactory =
				_initialContextFactoryBuilder.createInitialContextFactory(
					environment);
		}
		else {
			if (environment == null) {
				environment = _environment;
			}

			String initialContextFactoryClassName = null;

			if (environment != null) {
				initialContextFactoryClassName = (String)environment.get(
					Context.INITIAL_CONTEXT_FACTORY);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Environment initial context factory " +
							initialContextFactoryClassName);
				}
			}

			if (initialContextFactoryClassName == null) {
				initialContextFactoryClassName = System.getProperty(
					Context.INITIAL_CONTEXT_FACTORY);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"System initial context factory " +
							initialContextFactoryClassName);
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Instantiating " + initialContextFactoryClassName);
			}

			initialContextFactory =
				(InitialContextFactory)InstanceFactory.newInstance(
					initialContextFactoryClassName);
		}

		Context context = initialContextFactory.getInitialContext(environment);

		if (!PACLPolicyManager.isActive() ||
			!PortalSecurityManagerThreadLocal.isEnabled()) {

			return context;
		}

		PACLPolicy paclPolicy = PACLClassUtil.getPACLPolicy(
			false, _log.isDebugEnabled());

		if ((paclPolicy == null) || !paclPolicy.isActive()) {
			return context;
		}

		return new PACLContext(context, paclPolicy);
	}

	private static Log _log = LogFactoryUtil.getLog(
		PACLInitialContextFactory.class.getName());

	private Hashtable<?, ?> _environment;
	private InitialContextFactoryBuilder _initialContextFactoryBuilder;

}