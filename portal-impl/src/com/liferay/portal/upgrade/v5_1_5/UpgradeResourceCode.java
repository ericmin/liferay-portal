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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v5_1_5.util.ResourceCodeTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeResourceCode extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type ResourceCode name VARCHAR(255) null");
		}
		catch (Exception e) {

			// ResourceCode

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				ResourceCodeTable.TABLE_NAME, ResourceCodeTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(ResourceCodeTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(ResourceCodeTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}
	}

}