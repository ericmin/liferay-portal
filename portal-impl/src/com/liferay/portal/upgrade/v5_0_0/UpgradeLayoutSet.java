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

package com.liferay.portal.upgrade.v5_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v5_0_0.util.LayoutSetTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeLayoutSet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {

		// LayoutSet

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			LayoutSetTable.TABLE_NAME, LayoutSetTable.TABLE_COLUMNS);

		upgradeTable.setCreateSQL(LayoutSetTable.TABLE_SQL_CREATE);
		upgradeTable.setIndexesSQL(LayoutSetTable.TABLE_SQL_ADD_INDEXES);

		upgradeTable.updateTable();
	}

}