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

package com.liferay.portal.kernel.upgrade.dao.orm;

import com.liferay.portal.kernel.util.ProxyUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Minhchau Dang
 */
public class UpgradeOptimizedConnectionHandler implements InvocationHandler {

	public UpgradeOptimizedConnectionHandler(Connection connection) {
		_connection = connection;
	}

	public Object invoke(Object proxy, Method method, Object[] arguments)
		throws Throwable {

		try {
			String methodName = method.getName();

			if (methodName.equals("prepareStatement") &&
				(arguments.length == 1)) {

				return prepareStatement((String)arguments[0]);
			}

			return method.invoke(_connection, arguments);
		}
		catch (InvocationTargetException ite) {
			throw ite.getTargetException();
		}
	}

	protected PreparedStatement prepareStatement(String sql)
		throws SQLException {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		PreparedStatement preparedStatement = _connection.prepareStatement(
			sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		return (PreparedStatement)ProxyUtil.newProxyInstance(
			classLoader, new Class[] {PreparedStatement.class},
			new UpgradeOptimizedPreparedStatementHandler(preparedStatement));
	}

	private Connection _connection;

}