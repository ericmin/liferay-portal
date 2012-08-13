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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alexander Chow
 */
public class IGFolderNameColumnImpl extends BaseUpgradeColumnImpl {

	public IGFolderNameColumnImpl(
		UpgradeColumn groupIdColumn, UpgradeColumn parentFolderIdColumn) {

		super("name", null);

		_groupIdColumn = groupIdColumn;
		_parentFolderIdColumn = parentFolderIdColumn;
	}

	public Set<String> getDistintNames() {
		return _distinctNames;
	}

	public Object getNewValue(Object oldValue) throws Exception {
		String newName = (String)oldValue;

		while (_distinctNames.contains(_getKey(newName))) {
			_counter++;

			newName = newName + StringPool.SPACE + _counter;
		}

		_distinctNames.add(_getKey(newName));

		return newName;
	}

	private String _getKey(String name) {
		StringBundler sb = new StringBundler(5);

		sb.append(_groupIdColumn.getOldValue());
		sb.append(StringPool.UNDERLINE);
		sb.append(_parentFolderIdColumn.getOldValue());
		sb.append(StringPool.UNDERLINE);
		sb.append(name);

		return sb.toString();
	}

	private int _counter = 0;
	private Set<String> _distinctNames = new HashSet<String>();
	private UpgradeColumn _groupIdColumn;
	private UpgradeColumn _parentFolderIdColumn;

}