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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.search.BaseQueryImpl;
import com.liferay.portal.kernel.search.TermRangeQuery;

/**
 * @author Raymond Augé
 */
public class TermRangeQueryImpl extends BaseQueryImpl
	implements TermRangeQuery {

	public TermRangeQueryImpl(
		String field, String lowerTerm, String upperTerm, boolean includesLower,
		boolean includesUpper) {

		_termRangeQuery = new org.apache.lucene.search.TermRangeQuery(
			field, lowerTerm, upperTerm, includesLower, includesUpper);
	}

	public String getField() {
		return _termRangeQuery.getField();
	}

	public String getLowerTerm() {
		return _termRangeQuery.getLowerTerm();
	}

	public Object getTermRangeQuery() {
		return _termRangeQuery;
	}

	public String getUpperTerm() {
		return _termRangeQuery.getUpperTerm();
	}

	@Override
	public Object getWrappedQuery() {
		return getTermRangeQuery();
	}

	public boolean includesLower() {
		return _termRangeQuery.includesLower();
	}

	public boolean includesUpper() {
		return _termRangeQuery.includesUpper();
	}

	@Override
	public String toString() {
		return _termRangeQuery.toString();
	}

	private org.apache.lucene.search.TermRangeQuery _termRangeQuery;

}