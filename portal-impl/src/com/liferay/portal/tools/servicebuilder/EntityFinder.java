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

package com.liferay.portal.tools.servicebuilder;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TextFormatter;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class EntityFinder {

	public EntityFinder(
		String name, String returnType, boolean unique, String where,
		boolean dbIndex, List<EntityColumn> columns) {

		_name = name;
		_returnType = returnType;
		_unique = unique;
		_where = where;
		_dbIndex = dbIndex;
		_columns = columns;
	}

	public EntityColumn getColumn(String name) {
		for (EntityColumn column : _columns) {
			if (name.equals(column.getName())) {
				return column;
			}
		}

		return null;
	}

	public List<EntityColumn> getColumns() {
		return _columns;
	}

	public String getHumanConditions(boolean arrayable) {
		if (_columns.size() == 1) {
			return _columns.get(0).getHumanCondition(arrayable);
		}

		Iterator<EntityColumn> itr = _columns.iterator();

		StringBundler sb = new StringBundler();

		while (itr.hasNext()) {
			EntityColumn column = itr.next();

			sb.append(column.getHumanCondition(arrayable));

			if (itr.hasNext()) {
				sb.append(" and ");
			}
		}

		return sb.toString();
	}

	public String getName() {
		return _name;
	}

	public String getNames() {
		return TextFormatter.formatPlural(_name);
	}

	public String getReturnType() {
		return _returnType;
	}

	public String getWhere() {
		return _where;
	}

	public boolean hasArrayableOperator() {
		for (EntityColumn column : _columns) {
			if (column.hasArrayableOperator()) {
				return true;
			}
		}

		return false;
	}

	public boolean hasColumn(String name) {
		return Entity.hasColumn(name, _columns);
	}

	public boolean hasCustomComparator() {
		for (EntityColumn column : _columns) {
			String comparator = column.getComparator();

			if (!comparator.equals(StringPool.EQUAL)) {
				return true;
			}
		}

		return false;
	}

	public boolean isCollection() {
		if ((_returnType != null) && _returnType.equals("Collection")) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isDBIndex() {
		return _dbIndex;
	}

	public boolean isUnique() {
		return _unique;
	}

	private List<EntityColumn> _columns;
	private boolean _dbIndex;
	private String _name;
	private String _returnType;
	private boolean _unique;
	private String _where;

}