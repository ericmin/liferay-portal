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

package com.liferay.portal.upgrade.v5_1_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v5_1_7.util.WikiPageResourceTable;
import com.liferay.portal.upgrade.v5_1_7.util.WikiPageTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeWiki extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type WikiPage parentTitle varchar(255) null");
			runSQL(
				"alter_column_type WikiPage redirectTitle varchar(255) null");
		}
		catch (Exception e) {

			// WikiPage

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				WikiPageTable.TABLE_NAME, WikiPageTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(WikiPageTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(WikiPageTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		try {
			runSQL(
				"alter_column_type WikiPageResource title varchar(255) null");
		}
		catch (Exception e) {

			// WikiPageResource

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				WikiPageResourceTable.TABLE_NAME,
				WikiPageResourceTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(WikiPageResourceTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(
				WikiPageResourceTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}
	}

}