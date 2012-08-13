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

package com.liferay.portal.dao.jdbc.spring;

import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactory;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class SqlUpdateFactoryImpl implements SqlUpdateFactory {

	public SqlUpdate getSqlUpdate(
		DataSource dataSource, String sql, int[] types) {

		return new SqlUpdateImpl(dataSource, sql, types);
	}

}