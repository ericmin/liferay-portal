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

package com.liferay.portal.upgrade.v5_1_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v5_1_2.util.CalEventRecurrenceUpgradeColumnImpl;
import com.liferay.portal.upgrade.v5_1_2.util.CalEventTable;

/**
 * @author     Samuel Kong
 * @deprecated
 */
public class UpgradeCalendar extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		UpgradeColumn recurrenceColumn =
			new CalEventRecurrenceUpgradeColumnImpl("recurrence");

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			CalEventTable.TABLE_NAME, CalEventTable.TABLE_COLUMNS,
			recurrenceColumn);

		upgradeTable.updateTable();
	}

}