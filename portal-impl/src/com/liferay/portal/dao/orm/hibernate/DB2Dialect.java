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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Shepherd Ching
 * @author Jian Cao
 */
public class DB2Dialect extends org.hibernate.dialect.DB2Dialect {

	@Override
	public String getLimitString(String sql, boolean hasOffset) {
		if (!sql.startsWith("(")) {
			return super.getLimitString(sql, hasOffset);
		}

		StringBundler sb = new StringBundler(5);

		sb.append("select cursor1.* from (");
		sb.append("select rownumber() over() as rownumber_, cursor2.* from (");
		sb.append(sql);
		sb.append(") as cursor2) as cursor1 where rownumber_");

		if (hasOffset) {
			sb.append(" between ? + 1 and ?");
		}
		else {
			sb.append(" <= ?");
		}

		return sb.toString();
	}

}