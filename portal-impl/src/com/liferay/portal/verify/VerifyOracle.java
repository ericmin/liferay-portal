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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class VerifyOracle extends VerifyProcess {

	protected void alterColumns() throws Exception {
		int buildNumber = getBuildNumber();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select table_name, column_name, data_length from " +
					"user_tab_columns where data_type = 'VARCHAR2' and " +
						"char_used = 'B'");

			rs = ps.executeQuery();

			while (rs.next()) {
				String tableName = rs.getString(1);
				String columnName = rs.getString(2);
				int dataLength = rs.getInt(3);

				if ((buildNumber >= ReleaseInfo.RELEASE_5_2_9_BUILD_NUMBER) &&
					(buildNumber < ReleaseInfo.RELEASE_6_1_20_BUILD_NUMBER)) {

					if (dataLength != 4000) {
						dataLength = dataLength / 4;
					}
				}

				try {
					runSQL(
						"alter table " + tableName + " modify " + columnName +
							" varchar2(" + dataLength + " char)");
				}
				catch (SQLException sqle) {
					if (sqle.getErrorCode() == 1441) {
						if (_log.isWarnEnabled()) {
							StringBundler sb = new StringBundler(6);

							sb.append("Unable to alter length of column ");
							sb.append(columnName);
							sb.append(" for table ");
							sb.append(tableName);
							sb.append("because it contains values that are ");
							sb.append("larger than the new column length");

							_log.warn(sb.toString());
						}
					}
					else {
						throw sqle;
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	@Override
	protected void doVerify() throws Exception {
		DB db = DBFactoryUtil.getDB();

		String dbType = db.getType();

		if (!dbType.equals(DB.TYPE_ORACLE)) {
			return;
		}

		alterColumns();
	}

	private static Log _log = LogFactoryUtil.getLog(VerifyOracle.class);

}