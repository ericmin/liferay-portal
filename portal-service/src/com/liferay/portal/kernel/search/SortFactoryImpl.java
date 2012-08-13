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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Hugo Huijser
 */
public class SortFactoryImpl implements SortFactory {

	public Sort create(String fieldName, boolean reverse) {
		return new Sort(fieldName, reverse);
	}

	public Sort create(String fieldName, int type, boolean reverse) {
		return new Sort(fieldName, type, reverse);
	}

	public Sort[] getDefaultSorts() {
		return _DEFAULT_SORTS;
	}

	public Sort getSort(Class<?> clazz, String orderByCol, String orderByType) {
		Indexer indexer = IndexerRegistryUtil.getIndexer(clazz);

		String sortField = indexer.getSortField(orderByCol);

		if (Validator.isNull(orderByType)) {
			orderByType = "asc";
		}

		return new Sort(
			sortField, Sort.STRING_TYPE, !orderByType.equalsIgnoreCase("asc"));
	}

	public Sort[] toArray(List<Sort> sorts) {
		if ((sorts == null) || sorts.isEmpty()) {
			return new Sort[0];
		}

		Sort[] sortsArray = new Sort[sorts.size()];

		for (int i = 0; i < sorts.size(); i++) {
			sortsArray[i] = sorts.get(i);
		}

		return sortsArray;
	}

	private static final Sort[] _DEFAULT_SORTS = new Sort[] {
		new Sort(null, Sort.SCORE_TYPE, false),
		new Sort(Field.MODIFIED_DATE, Sort.LONG_TYPE, true)
	};

}