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

package com.liferay.portal.upgrade.v5_0_0.util;

import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alexander Chow
 */
public class IGImageNameColumnImpl extends BaseUpgradeColumnImpl {

	public IGImageNameColumnImpl(UpgradeColumn imageIdColumn) {
		super("name");

		_imageIdColumn = imageIdColumn;
	}

	public Object getNewValue(Object oldValue) throws Exception {
		String oldString = (String)oldValue;

		if (Validator.isNull(oldString)) {
			return _imageIdColumn.getOldValue();
		}
		else {
			return oldString;
		}
	}

	private UpgradeColumn _imageIdColumn;

}