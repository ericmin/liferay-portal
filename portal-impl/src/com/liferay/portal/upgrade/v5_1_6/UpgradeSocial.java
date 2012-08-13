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

package com.liferay.portal.upgrade.v5_1_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.DateUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v5_1_6.util.SocialActivityTable;
import com.liferay.portal.upgrade.v5_1_6.util.SocialRelationTable;
import com.liferay.portal.upgrade.v5_1_6.util.SocialRequestTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeSocial extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {

		// SocialActivity

		UpgradeColumn createDateColumn = new DateUpgradeColumnImpl(
			"createDate");
		UpgradeColumn modifiedDateColumn = new DateUpgradeColumnImpl(
			"modifiedDate");

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			SocialActivityTable.TABLE_NAME, SocialActivityTable.TABLE_COLUMNS,
			createDateColumn);

		upgradeTable.setCreateSQL(SocialActivityTable.TABLE_SQL_CREATE);
		upgradeTable.setIndexesSQL(SocialActivityTable.TABLE_SQL_ADD_INDEXES);

		upgradeTable.updateTable();

		// SocialRelation

		upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			SocialRelationTable.TABLE_NAME, SocialRelationTable.TABLE_COLUMNS,
			createDateColumn);

		upgradeTable.setCreateSQL(SocialRelationTable.TABLE_SQL_CREATE);
		upgradeTable.setIndexesSQL(SocialRelationTable.TABLE_SQL_ADD_INDEXES);

		upgradeTable.updateTable();

		// SocialRequest

		upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			SocialRequestTable.TABLE_NAME, SocialRequestTable.TABLE_COLUMNS,
			createDateColumn, modifiedDateColumn);

		upgradeTable.setCreateSQL(SocialRequestTable.TABLE_SQL_CREATE);
		upgradeTable.setIndexesSQL(SocialRequestTable.TABLE_SQL_ADD_INDEXES);

		upgradeTable.updateTable();
	}

}