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

import com.liferay.portal.dao.jdbc.util.DataSourceWrapper;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.lang.PortalSecurityManagerThreadLocal;
import com.liferay.portal.security.pacl.PACLClassUtil;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLPolicyManager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLDataSource extends DataSourceWrapper {

	public PACLDataSource(DataSource dataSource) {
		super(dataSource);

		_dataSource = dataSource;

		_log.debug("Loading " + PACLConnectionHandler.class.getName());
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = _dataSource.getConnection();

		if (!PACLPolicyManager.isActive() ||
			!PortalSecurityManagerThreadLocal.isCheckSQL() ||
			!PortalSecurityManagerThreadLocal.isEnabled()) {

			return connection;
		}

		PACLPolicy paclPolicy = PACLClassUtil.getPACLPolicy(
			false, _log.isDebugEnabled());

		if ((paclPolicy == null) || !paclPolicy.isActive()) {
			return connection;
		}

		return (Connection)ProxyUtil.newProxyInstance(
			paclPolicy.getClassLoader(), new Class<?>[] {Connection.class},
			new PACLConnectionHandler(connection, paclPolicy));
	}

	private static Log _log = LogFactoryUtil.getLog(
		PACLDataSource.class.getName());

	private DataSource _dataSource;

}