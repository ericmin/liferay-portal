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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.upgrade.v5_1_5.util.UserTable;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeUser extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type User_ greeting VARCHAR(255) null");
		}
		catch (Exception e) {

			// User_

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				UserTable.TABLE_NAME, UserTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(UserTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(UserTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		StringBundler sb = new StringBundler(9);

		sb.append("update User_ set firstName = (select Contact_.firstName ");
		sb.append("from Contact_ where Contact_.contactId = ");
		sb.append("User_.contactId), middleName = (select ");
		sb.append("Contact_.middleName from Contact_ where ");
		sb.append("Contact_.contactId = User_.contactId), lastName = ");
		sb.append("(select Contact_.lastName from Contact_ where ");
		sb.append("Contact_.contactId = User_.contactId), jobTitle = (select ");
		sb.append("Contact_.jobTitle from Contact_ where ");
		sb.append("Contact_.contactId = User_.contactId)");

		runSQL(sb.toString());
	}

}