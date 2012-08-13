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

package com.liferay.portal.dao.jdbc.pacl;

import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.lang.PortalSecurityManagerThreadLocal;
import com.liferay.portal.security.pacl.PACLPolicy;

import java.lang.Object;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLConnectionHandler implements InvocationHandler {

	public PACLConnectionHandler(Connection connection, PACLPolicy paclPolicy) {
		_connection = connection;
		_paclPolicy = paclPolicy;
	}

	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			if (methodName.equals("equals")) {
				if (proxy == arguments[0]) {
					return true;
				}
				else {
					return false;
				}
			}
			else if (methodName.equals("hashCode")) {
				return System.identityHashCode(proxy);
			}
			else if (methodName.equals("prepareCall") ||
					 methodName.equals("prepareStatement")) {

				String sql = (String)arguments[0];

				if (!_paclPolicy.hasSQL(sql)) {
					throw new SecurityException(
						"Attempted to execute unapproved SQL " + sql);
				}
			}

			boolean enabled = PortalSecurityManagerThreadLocal.isEnabled();

			Object returnValue = null;

			try {
				PortalSecurityManagerThreadLocal.setEnabled(false);

				returnValue = method.invoke(_connection, arguments);
			}
			finally {
				PortalSecurityManagerThreadLocal.setEnabled(enabled);
			}

			if (methodName.equals("createStatement") ||
				methodName.equals("prepareCall") ||
				methodName.equals("prepareStatement")) {

				Statement statement = (Statement)returnValue;

				return ProxyUtil.newProxyInstance(
					_paclPolicy.getClassLoader(),
					getInterfaces(returnValue.getClass()),
					new PACLStatementHandler(statement, _paclPolicy));
			}

			return returnValue;
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected Class<?>[] getInterfaces(Class<?> returnType) {
		List<Class<?>> interfaceClasses = new ArrayList<Class<?>>();

		interfaceClasses.add(Statement.class);

		if (!CallableStatement.class.isAssignableFrom(returnType)) {
			interfaceClasses.add(CallableStatement.class);
		}
		else if (!PreparedStatement.class.isAssignableFrom(returnType)) {
			interfaceClasses.add(PreparedStatement.class);
		}

		return interfaceClasses.toArray(new Class<?>[interfaceClasses.size()]);
	}

	private Connection _connection;
	private PACLPolicy _paclPolicy;

}