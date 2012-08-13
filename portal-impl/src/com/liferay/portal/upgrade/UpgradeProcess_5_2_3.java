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

package com.liferay.portal.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.upgrade.v5_2_3.UpgradeBookmarks;
import com.liferay.portal.upgrade.v5_2_3.UpgradeCalendar;
import com.liferay.portal.upgrade.v5_2_3.UpgradeDocumentLibrary;
import com.liferay.portal.upgrade.v5_2_3.UpgradeDuplicates;
import com.liferay.portal.upgrade.v5_2_3.UpgradeGroup;
import com.liferay.portal.upgrade.v5_2_3.UpgradeImageGallery;
import com.liferay.portal.upgrade.v5_2_3.UpgradeLayout;
import com.liferay.portal.upgrade.v5_2_3.UpgradeMessageBoards;
import com.liferay.portal.upgrade.v5_2_3.UpgradeResource;
import com.liferay.portal.upgrade.v5_2_3.UpgradeResourceCode;
import com.liferay.portal.upgrade.v5_2_3.UpgradeRole;
import com.liferay.portal.upgrade.v5_2_3.UpgradeSchema;
import com.liferay.portal.upgrade.v5_2_3.UpgradeSoftwareCatalog;
import com.liferay.portal.upgrade.v5_2_3.UpgradeTags;
import com.liferay.portal.upgrade.v5_2_3.UpgradeUser;
import com.liferay.portal.upgrade.v5_2_3.UpgradeWiki;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeProcess_5_2_3 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_5_2_3_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);
		upgrade(UpgradeBookmarks.class);
		upgrade(UpgradeCalendar.class);
		upgrade(UpgradeDocumentLibrary.class);
		upgrade(UpgradeGroup.class);
		upgrade(UpgradeImageGallery.class);
		upgrade(UpgradeLayout.class);
		upgrade(UpgradeResource.class);
		upgrade(UpgradeResourceCode.class);
		upgrade(UpgradeRole.class);
		upgrade(UpgradeSoftwareCatalog.class);
		upgrade(UpgradeTags.class);
		upgrade(UpgradeUser.class);
		upgrade(UpgradeWiki.class);
		upgrade(UpgradeDuplicates.class);
		upgrade(UpgradeMessageBoards.class);
		upgrade(DropIndexes.class);
	}

}