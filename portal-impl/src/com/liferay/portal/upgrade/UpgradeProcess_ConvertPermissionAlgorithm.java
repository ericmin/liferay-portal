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

import com.liferay.portal.convert.ConvertPermissionAlgorithm;
import com.liferay.portal.convert.ConvertProcess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ReleaseInfo;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeProcess_ConvertPermissionAlgorithm extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_6_0_0_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		ConvertProcess convertProcess = new ConvertPermissionAlgorithm();

		convertProcess.setParameterValues(
			new String[] {Boolean.FALSE.toString()});

		convertProcess.convert();
	}

}