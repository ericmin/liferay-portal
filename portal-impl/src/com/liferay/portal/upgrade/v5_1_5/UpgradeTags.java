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
import com.liferay.portal.upgrade.v5_1_5.util.TagsAssetTable;
import com.liferay.portal.upgrade.v5_1_5.util.TagsPropertyTable;
import com.liferay.portal.upgrade.v5_1_5.util.TagsPropertyValueUpgradeColumnImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Kong
 */
public class UpgradeTags extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL("alter_column_type TagsAsset title VARCHAR(255) null");
		}
		catch (Exception e) {

			// TagsAsset

			UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
				TagsAssetTable.TABLE_NAME, TagsAssetTable.TABLE_COLUMNS);

			upgradeTable.setCreateSQL(TagsAssetTable.TABLE_SQL_CREATE);
			upgradeTable.setIndexesSQL(TagsAssetTable.TABLE_SQL_ADD_INDEXES);

			upgradeTable.updateTable();
		}

		updateAssetViewCount();

		// TagsProperty

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			TagsPropertyTable.TABLE_NAME, TagsPropertyTable.TABLE_COLUMNS,
			new TagsPropertyValueUpgradeColumnImpl("value"));

		upgradeTable.setCreateSQL(TagsPropertyTable.TABLE_SQL_CREATE);
		upgradeTable.setIndexesSQL(TagsPropertyTable.TABLE_SQL_ADD_INDEXES);

		upgradeTable.updateTable();
	}

	protected void updateAssetViewCount() throws Exception {
		updateAssetViewCount(
			BookmarksEntry.class.getName(), "BookmarksEntry", "entryId",
			"visits");

		updateAssetViewCount(
			DLFileEntry.class.getName(), "DLFileEntry", "fileEntryId",
			"readCount");
	}

	protected void updateAssetViewCount(
			String className, String tableName, String columnClassPK,
			String columnViewCount)
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(className);

		StringBuilder sb = new StringBuilder();

		sb.append("update TagsAsset set viewCount = (select ");
		sb.append(tableName);
		sb.append(".");
		sb.append(columnViewCount);
		sb.append(" from ");
		sb.append(tableName);
		sb.append(" where TagsAsset.classPK = ");
		sb.append(tableName);
		sb.append(".");
		sb.append(columnClassPK);
		sb.append(") where TagsAsset.classNameId = ");
		sb.append(classNameId);

		runSQL(sb.toString());
	}

}