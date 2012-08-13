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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brian Wing Shun Chan
 */
public class VerifyUUID extends VerifyProcess {

	public static void verifyModel(String modelName, String pkColumnName)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select " + pkColumnName + " from " + modelName +
					" where uuid_ is null or uuid_ = ''");

			rs = ps.executeQuery();

			while (rs.next()) {
				long pk = rs.getLong(pkColumnName);

				verifyModel(modelName, pkColumnName, pk);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	public static void verifyModel(
			String modelName, String pkColumnName, long pk)
		throws Exception {

		String uuid = PortalUUIDUtil.generate();

		DB db = DBFactoryUtil.getDB();

		db.runSQL(
			"update " + modelName + " set uuid_ = '" + uuid +
				"' where " + pkColumnName + " = " + pk);
	}

	@Override
	protected void doVerify() throws Exception {
		for (String[] model : _MODELS) {
			verifyModel(model[0], model[1]);
		}
	}

	private static final String[][] _MODELS = new String[][] {
		new String[] {
			"DLFileVersion", "fileVersionId"
		},
		new String[] {
			"JournalArticleResource", "resourcePrimKey"
		},
		new String[] {
			"JournalFeed", "id_"
		},
		new String[] {
			"JournalStructure", "id_"
		},
		new String[] {
			"JournalTemplate", "id_"
		},
		new String[] {
			"Layout", "plid"
		},
		new String[] {
			"LayoutPrototype", "layoutPrototypeId"
		},
		new String[] {
			"LayoutSetPrototype", "layoutSetPrototypeId"
		},
		new String[] {
			"WikiPageResource", "resourcePrimKey"
		}
	};

}