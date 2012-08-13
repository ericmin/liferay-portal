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

package com.liferay.portal.dao.jdbc.util;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author Shuyang Zhou
 */
public class DataSourceWrapper implements DataSource {

	public DataSourceWrapper(DataSource dataSource) {
		_dataSource = dataSource;
	}

	public Connection getConnection() throws SQLException {
		return _dataSource.getConnection();
	}

	public Connection getConnection(String username, String password)
		throws SQLException {

		return _dataSource.getConnection(username, password);
	}

	public int getLoginTimeout() throws SQLException {
		return _dataSource.getLoginTimeout();
	}

	public PrintWriter getLogWriter() throws SQLException {
		return _dataSource.getLogWriter();
	}

	public Logger getParentLogger() {

		// JDK 7

		throw new UnsupportedOperationException();
	}

	public DataSource getWrappedDataSource() {
		return _dataSource;
	}

	public boolean isWrapperFor(Class<?> clazz) {

		// JDK 6

		return DataSource.class.equals(clazz);
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		_dataSource.setLoginTimeout(seconds);
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		_dataSource.setLogWriter(out);
	}

	public void setWrappedDataSource(DataSource wrappedDataSource) {
		_dataSource = wrappedDataSource;
	}

	public <T> T unwrap(Class<T> clazz) throws SQLException {

		// JDK 6

		if (!DataSource.class.equals(clazz)) {
			throw new SQLException("Invalid class " + clazz);
		}

		return (T)this;
	}

	private volatile DataSource _dataSource;

}