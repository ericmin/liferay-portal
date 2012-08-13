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

package com.liferay.portal.upgrade.v5_2_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v5_2_3.util.ResourceTable;

import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeResource extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type Resource_ primKey VARCHAR(255) null");
		}
		catch (SQLException sqle) {

			// Resource

			upgradeTable(
				ResourceTable.TABLE_NAME, ResourceTable.TABLE_COLUMNS,
				ResourceTable.TABLE_SQL_CREATE,
				ResourceTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}