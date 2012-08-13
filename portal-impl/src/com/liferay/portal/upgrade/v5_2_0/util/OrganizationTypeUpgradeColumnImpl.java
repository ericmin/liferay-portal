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

package com.liferay.portal.upgrade.v5_2_0.util;

import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;

/**
 * @author Brian Wing Shun Chan
 */
public class OrganizationTypeUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public OrganizationTypeUpgradeColumnImpl(UpgradeColumn locationColumn) {
		super("type_");

		_locationColumn = locationColumn;
	}

	public Object getNewValue(Object oldValue) throws Exception {
		Boolean type = (Boolean)_locationColumn.getOldValue();

		if (type) {
			return "location";
		}
		else {
			return "regular-organization";
		}
	}

	private UpgradeColumn _locationColumn;

}