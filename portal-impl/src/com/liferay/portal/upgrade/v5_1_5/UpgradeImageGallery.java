/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.upgrade.v5_1_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * <a href="UpgradeImageGallery.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class UpgradeImageGallery extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("update IGImage set groupId = (select groupId from ");
		sb.append("IGFolder where IGFolder.folderId = IGImage.folderId)");

		runSQL(sb.toString());
	}

}