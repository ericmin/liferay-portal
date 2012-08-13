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
import com.liferay.portal.upgrade.v5_2_3.util.CalEventTable;

import java.sql.SQLException;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeCalendar extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type CalEvent remindBy INTEGER");
		}
		catch (SQLException sqle) {

			// CalEvent

			upgradeTable(
				CalEventTable.TABLE_NAME, CalEventTable.TABLE_COLUMNS,
				CalEventTable.TABLE_SQL_CREATE,
				CalEventTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}