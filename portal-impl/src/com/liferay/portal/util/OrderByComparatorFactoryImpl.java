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

package com.liferay.portal.util;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderByComparatorFactoryImpl implements OrderByComparatorFactory {

	public OrderByComparator create(String tableName, Object... columns) {
		if ((columns.length == 0) || ((columns.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Columns length is not an even number");
		}

		return new DefaultOrderByComparator(tableName, columns);
	}

	protected class DefaultOrderByComparator extends OrderByComparator {

		@Override
		public int compare(Object object1, Object object2) {
			for (int i = 0; i < _columns.length; i += 2) {
				String columnName = String.valueOf(_columns[i]);
				boolean columnAscending = Boolean.valueOf(
					String.valueOf(_columns[i + 1]));

				Class<?> columnClass = BeanPropertiesUtil.getObjectTypeSilent(
					object1, columnName);

				Object columnInstance = null;

				try {
					columnInstance = columnClass.newInstance();
				}
				catch (Exception e) {
				}

				Object columnValue1 = BeanPropertiesUtil.getObjectSilent(
					object1, columnName);
				Object columnValue2 = BeanPropertiesUtil.getObjectSilent(
					object2, columnName);

				if (columnInstance instanceof Date) {
					Date columnValueDate1 = (Date)columnValue1;
					Date columnValueDate2 = (Date)columnValue2;

					int value = DateUtil.compareTo(
						columnValueDate1, columnValueDate2);

					if (value == 0) {
						continue;
					}

					if (columnAscending) {
						return value;
					}
					else {
						return -value;
					}
				}
				else if (columnInstance instanceof Comparable<?>) {
					Comparable<Object> columnValueComparable1 =
						(Comparable<Object>)columnValue1;
					Comparable<Object> columnValueComparable2 =
						(Comparable<Object>)columnValue2;

					int value = columnValueComparable1.compareTo(
						columnValueComparable2);

					if (value == 0) {
						continue;
					}

					if (columnAscending) {
						return value;
					}
					else {
						return -value;
					}
				}
			}

			return 0;
		}

		@Override
		public String getOrderBy() {
			StringBundler sb = new StringBundler();

			for (int i = 0; i < _columns.length; i += 2) {
				if (i != 0) {
					sb.append(StringPool.COMMA);
				}

				sb.append(_tableName);
				sb.append(StringPool.PERIOD);

				String columnName = String.valueOf(_columns[i]);
				boolean columnAscending = Boolean.valueOf(
					String.valueOf(_columns[i + 1]));

				sb.append(columnName);

				if (columnAscending) {
					sb.append(_ORDER_BY_ASC);
				}
				else {
					sb.append(_ORDER_BY_DESC);
				}
			}

			return sb.toString();
		}

		@Override
		public boolean isAscending(String field) {
			String orderBy = getOrderBy();

			if (orderBy == null) {
				return false;
			}

			int x = orderBy.indexOf(
				StringPool.PERIOD + field + StringPool.SPACE);

			if (x == -1) {
				return false;
			}

			int y = orderBy.indexOf(_ORDER_BY_ASC, x);

			if (y == -1) {
				return false;
			}

			int z = orderBy.indexOf(_ORDER_BY_DESC, x);

			if ((z >= 0) && (z < y)) {
				return false;
			}
			else {
				return true;
			}
		}

		private DefaultOrderByComparator(String tableName, Object... columns) {
			_tableName = tableName;
			_columns = columns;
		}

		private static final String _ORDER_BY_ASC = " ASC";

		private static final String _ORDER_BY_DESC = " DESC";

		private Object[] _columns;
		private String _tableName;

	}

}