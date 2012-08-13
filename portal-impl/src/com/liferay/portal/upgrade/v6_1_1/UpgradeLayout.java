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

package com.liferay.portal.upgrade.v6_1_1;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jorge Ferrer
 */
public class UpgradeLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateSourcePrototypeLayoutUuid();
	}

	protected long getLayoutPrototypeGroupId(String layoutPrototypeUuid)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select groupId from Group_ where classPK = (select " +
					"layoutPrototypeId from LayoutPrototype where uuid_ = ?)");

			ps.setString(1, layoutPrototypeUuid);

			rs = ps.executeQuery();

			while (rs.next()) {
				long groupId = rs.getLong("groupId");

				return groupId;
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return 0;
	}

	protected boolean isGroupPrivateLayout(
			long groupId, String sourcePrototypeLayoutUuid)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select count(*) from Layout where uuid_ = ? and groupId = ? " +
					"and privateLayout = ?");

			ps.setString(1, sourcePrototypeLayoutUuid);
			ps.setLong(2, groupId);
			ps.setBoolean(3, true);

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return false;
	}

	protected void updateSourcePrototypeLayoutUuid() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			// Get pages with a sourcePrototypeLayoutUuid that have a page
			// template. If the layoutUuid points to a page template, remove
			// it. Otherwise, it points to a site template page, so leave it.

			StringBundler sb = new StringBundler(6);

			sb.append("select plid, layoutPrototypeUuid, ");
			sb.append("sourcePrototypeLayoutUuid from Layout where ");
			sb.append("layoutPrototypeUuid is not null and ");
			sb.append("layoutPrototypeUuid != '' and ");
			sb.append("sourcePrototypeLayoutUuid is not null and ");
			sb.append("sourcePrototypeLayoutUuid != ''");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");
				String layoutPrototypeUuid = rs.getString(
					"layoutPrototypeUuid");
				String sourcePrototypeLayoutUuid = rs.getString(
					"sourcePrototypeLayoutUuid");

				long groupId = getLayoutPrototypeGroupId(layoutPrototypeUuid);

				if (groupId == 0) {
					continue;
				}

				if (isGroupPrivateLayout(groupId, sourcePrototypeLayoutUuid)) {
					runSQL(
						"update Layout set sourcePrototypeLayoutUuid = null " +
							"where plid = " + plid);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}