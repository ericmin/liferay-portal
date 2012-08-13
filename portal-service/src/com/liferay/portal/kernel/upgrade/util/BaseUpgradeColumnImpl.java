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

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseUpgradeColumnImpl implements UpgradeColumn {

	public BaseUpgradeColumnImpl(String name) {
		this(name, null);
	}

	public BaseUpgradeColumnImpl(String name, Integer oldColumnType) {
		_name = name;
		_oldColumnType = oldColumnType;
	}

	public String getName() {
		return _name;
	}

	public Integer getNewColumnType(Integer defaultType) {
		return defaultType;
	}

	public Object getNewValue() {
		return _newValue;
	}

	public Integer getOldColumnType(Integer defaultType) {
		if (_oldColumnType == null) {
			return defaultType;
		}
		else {
			return _oldColumnType;
		}
	}

	public Object getOldValue() {
		return _oldValue;
	}

	public long increment() throws SystemException {
		DB db = DBFactoryUtil.getDB();

		return db.increment();
	}

	public boolean isApplicable(String name) {
		if (_name.equals(name)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setNewValue(Object newValue) {
		_newValue = newValue;
	}

	public void setOldValue(Object oldValue) {
		_oldValue = oldValue;
	}

	private String _name;
	private Object _newValue;
	private Integer _oldColumnType;
	private Object _oldValue;

}