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

package com.liferay.portal.upgrade.v5_2_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.TempUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.upgrade.v5_2_0.util.OrganizationTable;
import com.liferay.portal.upgrade.v5_2_0.util.OrganizationTypeUpgradeColumnImpl;
import com.liferay.portal.util.PortalInstances;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

/**
 * @author Jorge Ferrer
 * @author Edward Shin
 */
public class UpgradeOrganization extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		UpgradeColumn locationColumn = new TempUpgradeColumnImpl(
			"location", new Integer(Types.BOOLEAN));

		UpgradeColumn typeColumn = new OrganizationTypeUpgradeColumnImpl(
			locationColumn);

		Object[][] organizationColumns1 =
			{{"location", new Integer(Types.BOOLEAN)}};
		Object[][] organizationColumns2 =
			OrganizationTable.TABLE_COLUMNS.clone();

		Object[][] organizationColumns = ArrayUtil.append(
			organizationColumns1, organizationColumns2);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			OrganizationTable.TABLE_NAME, organizationColumns, locationColumn,
			typeColumn);

		upgradeTable.updateTable();

		updateLocationResources();
	}

	protected long getCodeId(long companyId, String name, int scope)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select codeId from ResourceCode where companyId = ? and " +
					"name = ? and scope = ?");

			ps.setLong(1, companyId);
			ps.setString(2, name);
			ps.setInt(3, scope);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("codeId");
			}

			return 0;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateCodeId(long companyId, int scope) throws Exception {
		long oldCodeId = getCodeId(
			companyId, "com.liferay.portal.model.Location", scope);
		long newCodeId = getCodeId(
			companyId, "com.liferay.portal.model.Organization", scope);

		runSQL(
			"update Resource_ set codeId = " + newCodeId + " where codeId = " +
				oldCodeId);

		runSQL("delete from ResourceCode where codeId = " + oldCodeId);
	}

	protected void updateLocationResources() throws Exception {
		long[] companyIds = PortalInstances.getCompanyIdsBySQL();

		for (long companyId : companyIds) {
			for (int scope : ResourceConstants.SCOPES) {
				updateCodeId(companyId, scope);
			}
		}
	}

}