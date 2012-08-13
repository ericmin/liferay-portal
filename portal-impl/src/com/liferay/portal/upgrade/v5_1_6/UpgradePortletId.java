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

package com.liferay.portal.upgrade.v5_1_6;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradePortletId
	extends com.liferay.portal.upgrade.v5_2_0.UpgradePortletId {

	protected Log getLog() {
		return _log;
	}

	@Override
	protected String[][] getPortletIdsArray() {
		return new String[][] {
			new String[] {
				"7", "1_WAR_biblegatewayportlet"
			},
			new String[] {
				"21", "1_WAR_randombibleverseportlet"
			},
			new String[] {
				"46", "1_WAR_gospelforasiaportlet"
			}
		};
	}

	private static Log _log = LogFactoryUtil.getLog(UpgradePortletId.class);

}