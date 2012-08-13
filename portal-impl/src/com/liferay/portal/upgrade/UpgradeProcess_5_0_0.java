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
import com.liferay.portal.upgrade.v5_0_0.UpgradeImageGallery;
import com.liferay.portal.upgrade.v5_0_0.UpgradeLayoutSet;
import com.liferay.portal.upgrade.v5_0_0.UpgradeSchema;
import com.liferay.portal.upgrade.v5_0_0.UpgradeSoftwareCatalog;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeProcess_5_0_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_5_0_0_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);
		upgrade(UpgradeImageGallery.class);
		upgrade(UpgradeLayoutSet.class);
		upgrade(UpgradeSoftwareCatalog.class);
	}

}