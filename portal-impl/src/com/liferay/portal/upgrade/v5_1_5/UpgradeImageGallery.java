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

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeImageGallery extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("update IGImage set groupId = (select groupId from ");
		sb.append("IGFolder where IGFolder.folderId = IGImage.folderId)");

		runSQL(sb.toString());
	}

}