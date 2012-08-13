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

package com.liferay.portal.upgrade.v5_1_5.util;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class MBDiscussionDependencyManager extends DependencyManager {

	@Override
	public void update(
			long oldPrimaryKeyValue, Object[] oldColumnValues,
			Object[] oldExtraColumnValues, long newPrimaryKeyValue,
			Object[] newColumnValues, Object[] newExtraColumnValues)
		throws Exception {

		long threadId = 0;

		for (int i = 0; i < columns.length; i++) {
			if (columns[i][0].equals("threadId")) {
				threadId = (Long)newColumnValues[i];
			}
		}

		if ((threadId == 0) && (extraColumns != null)) {
			for (int i = 0; i < extraColumns.length; i++) {
				if (extraColumns[i][0].equals("threadId")) {
					threadId = (Long)newExtraColumnValues[i];
				}
			}
		}

		if (isDuplicateThread(threadId)) {
			deleteDuplicateData("MBMessage", "threadId", threadId);
			deleteDuplicateData("MBMessageFlag", "threadId", threadId);
			deleteDuplicateData("MBThread", "threadId", threadId);
		}
	}

	protected boolean isDuplicateThread(long threadId) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select count(*) from MBDiscussion where threadId = ?");

			ps.setLong(1, threadId);

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return false;
				}
			}

			return true;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}