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

package com.liferay.portal.bean;

import com.liferay.portal.security.pacl.PACLBeanHandler;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;

import java.lang.Object;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 */
public class VelocityBeanHandler extends PACLBeanHandler {

	public VelocityBeanHandler(Object bean, ClassLoader classLoader) {
		super(bean);

		_classLoader = classLoader;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if ((_classLoader != null) &&
				(_classLoader != contextClassLoader)) {

				PACLClassLoaderUtil.setContextClassLoader(_classLoader);
			}

			return super.invoke(proxy, method, arguments);
		}
		catch (InvocationTargetException ite) {
			return null;
		}
		finally {
			if ((_classLoader != null) &&
				(_classLoader != contextClassLoader)) {

				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	private ClassLoader _classLoader;

}