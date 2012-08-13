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
 */
public class ResourceCodeDependencyManager extends DependencyManager {

	@Override
	public void update(
			long oldPrimaryKeyValue, Object[] oldColumnValues,
			Object[] oldExtraColumnValues, long newPrimaryKeyValue,
			Object[] newColumnValues, Object[] newExtraColumnValues)
		throws Exception {

		long codeId = newPrimaryKeyValue;

		long resourceId = getResourceId(codeId);

		deleteDuplicateData("Resource_", codeId);

		if (resourceId > 0) {
			DependencyManager resourceDependencyManager =
				new ResourceDependencyManager();

			resourceDependencyManager.setPrimaryKeyName("resourceId");

			resourceDependencyManager.update(resourceId);
		}
	}

	protected long getResourceId(long codeId) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select resourceId from Resource_ where codeId = ?");

			ps.setLong(1, codeId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourceId = rs.getLong("resourceId");

				return resourceId;
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return 0;
	}

}