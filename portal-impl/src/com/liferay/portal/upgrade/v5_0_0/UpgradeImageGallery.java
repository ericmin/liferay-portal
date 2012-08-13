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
import com.liferay.portal.kernel.upgrade.util.TempUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v5_0_0.util.IGFolderNameColumnImpl;
import com.liferay.portal.upgrade.v5_0_0.util.IGFolderTable;
import com.liferay.portal.upgrade.v5_0_0.util.IGImageNameColumnImpl;
import com.liferay.portal.upgrade.v5_0_0.util.IGImageTable;

/**
 * @author Alexander Chow
 */
public class UpgradeImageGallery extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {

		// IGFolder

		UpgradeColumn groupIdColumn = new TempUpgradeColumnImpl("groupId");

		UpgradeColumn parentFolderIdColumn = new TempUpgradeColumnImpl(
			"parentFolderId");

		IGFolderNameColumnImpl igFolderNameColumn = new IGFolderNameColumnImpl(
			groupIdColumn, parentFolderIdColumn);

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			IGFolderTable.TABLE_NAME, IGFolderTable.TABLE_COLUMNS,
			groupIdColumn, parentFolderIdColumn, igFolderNameColumn);

		upgradeTable.updateTable();

		// IGImage

		UpgradeColumn imageIdColumn = new TempUpgradeColumnImpl("imageId");

		IGImageNameColumnImpl imageNameColumn = new IGImageNameColumnImpl(
			imageIdColumn);

		upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			IGImageTable.TABLE_NAME, IGImageTable.TABLE_COLUMNS, imageIdColumn,
			imageNameColumn);

		upgradeTable.updateTable();
	}

}