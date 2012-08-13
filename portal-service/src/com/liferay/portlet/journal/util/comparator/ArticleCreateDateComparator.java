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

package com.liferay.portlet.journal.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.journal.model.JournalArticle;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleCreateDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "JournalArticle.createDate ASC";

	public static final String ORDER_BY_DESC = "JournalArticle.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public ArticleCreateDateComparator() {
		this(false);
	}

	public ArticleCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		JournalArticle article1 = (JournalArticle)obj1;
		JournalArticle article2 = (JournalArticle)obj2;

		int value = DateUtil.compareTo(
			article1.getCreateDate(), article2.getCreateDate());

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}