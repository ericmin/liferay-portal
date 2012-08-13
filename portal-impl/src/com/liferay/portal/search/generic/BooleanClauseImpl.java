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

package com.liferay.portal.search.generic;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Query;

/**
 * @author Michael C. Han
 */
public class BooleanClauseImpl implements BooleanClause {

	public BooleanClauseImpl(
		Query query, BooleanClauseOccur booleanClauseOccur) {

		_query = query;
		_booleanClauseOccur = booleanClauseOccur;
	}

	public BooleanClauseOccur getBooleanClauseOccur() {
		return _booleanClauseOccur;
	}

	public Query getQuery() {
		return _query;
	}

	private BooleanClauseOccur _booleanClauseOccur;
	private Query _query;

}