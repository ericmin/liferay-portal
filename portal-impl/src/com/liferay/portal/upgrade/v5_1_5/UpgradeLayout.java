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

package com.liferay.portal.upgrade.v5_1_5;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Samuel Kong
 */
public class UpgradeLayout extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		String languageId = LocaleUtil.toLanguageId(LocaleUtil.getDefault());

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select plid, typeSettings from Layout where typeSettings " +
					"like '%meta-description=%'");

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");
				String typeSettings = rs.getString("typeSettings");

				UnicodeProperties typeSettingsProperties =
					new UnicodeProperties(true);

				typeSettingsProperties.load(typeSettings);

				String oldMetaDescription = typeSettingsProperties.getProperty(
					"meta-description");
				String newMetaDescription = typeSettingsProperties.getProperty(
					"meta-description_" + languageId);

				if (Validator.isNotNull(oldMetaDescription) &&
					Validator.isNull(newMetaDescription)) {

					typeSettingsProperties.setProperty(
						"meta-description_" + languageId, oldMetaDescription);
				}

				typeSettingsProperties.remove("meta-description");

				String oldMetaKeywords = typeSettingsProperties.getProperty(
					"meta-keywords");
				String newMetaKeywords = typeSettingsProperties.getProperty(
					"meta-keywords_" + languageId);

				if (Validator.isNotNull(oldMetaKeywords) &&
					Validator.isNull(newMetaKeywords)) {

					typeSettingsProperties.setProperty(
						"meta-keywords_" + languageId, oldMetaKeywords);
				}

				typeSettingsProperties.remove("meta-keywords");

				String oldMetaRobots = typeSettingsProperties.getProperty(
					"meta-robots");
				String newMetaRobots = typeSettingsProperties.getProperty(
					"meta-robots_" + languageId);

				if (Validator.isNotNull(oldMetaRobots) &&
					Validator.isNull(newMetaRobots)) {

					typeSettingsProperties.setProperty(
						"meta-robots_" + languageId, oldMetaRobots);
				}

				typeSettingsProperties.remove("meta-robots");

				updateTypeSettings(plid, typeSettingsProperties.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateTypeSettings(long plid, String typeSettings)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update Layout set typeSettings = ? where plid = " + plid);

			ps.setString(1, typeSettings);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

}