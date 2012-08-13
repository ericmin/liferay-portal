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

package com.liferay.portal.upgrade.v5_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeMessageBoards extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {

		// LEP-5761

		while (getMessageIdsCount() > 0) {
			updateMessage();
		}
	}

	protected int getMessageIdsCount() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(7);

			sb.append("select count(*) from ");
			sb.append("MBMessage childMessage ");
			sb.append("inner join MBMessage parentMessage on ");
			sb.append("childMessage.parentMessageId = ");
			sb.append("parentMessage.messageId where ");
			sb.append("parentMessage.categoryId != childMessage.categoryId ");
			sb.append("or parentMessage.threadId != childMessage.threadId");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateMessage() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(8);

			sb.append("select childMessage.messageId, ");
			sb.append("parentMessage.categoryId, parentMessage.threadId ");
			sb.append("from MBMessage childMessage ");
			sb.append("inner join MBMessage parentMessage on ");
			sb.append("childMessage.parentMessageId = ");
			sb.append("parentMessage.messageId where ");
			sb.append("parentMessage.categoryId != childMessage.categoryId ");
			sb.append("or parentMessage.threadId != childMessage.threadId");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long messageId = rs.getLong(1);
				long categoryId = rs.getLong(2);
				long threadId = rs.getLong(3);

				runSQL(
					"update MBMessage set categoryId = " + categoryId +
						", threadId = " + threadId + " where messageId = " +
							messageId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}