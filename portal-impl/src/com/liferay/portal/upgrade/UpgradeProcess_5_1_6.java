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
import com.liferay.portal.upgrade.v5_1_6.UpgradeBlogs;
import com.liferay.portal.upgrade.v5_1_6.UpgradePortletId;
import com.liferay.portal.upgrade.v5_1_6.UpgradeSocial;

/**
 * @author Douglas Wong
 */
public class UpgradeProcess_5_1_6 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_5_1_6_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeBlogs.class);
		upgrade(UpgradePortletId.class);
		upgrade(UpgradeSocial.class);
	}

}