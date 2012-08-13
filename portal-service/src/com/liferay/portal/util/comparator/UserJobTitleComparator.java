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

package com.liferay.portal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;

/**
 * @author Brian Wing Shun Chan
 */
public class UserJobTitleComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"jobTitle ASC, lastName ASC, firstName ASC, middleName ASC";

	public static final String ORDER_BY_DESC =
		"jobTitle DESC, lastName DESC, firstName DESC, middleName DESC";

	public static final String[] ORDER_BY_FIELDS = {
		"jobTitle", "lastName", "firstName", "middleName"
	};

	public UserJobTitleComparator() {
		this(false);
	}

	public UserJobTitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		User user1 = (User)obj1;
		User user2 = (User)obj2;

		int value = user1.getJobTitle().compareTo(user2.getJobTitle());

		if (value == 0) {
			value = user1.getLastName().compareTo(user2.getLastName());
		}

		if (value == 0) {
			value = user1.getFirstName().compareTo(user2.getFirstName());
		}

		if (value == 0) {
			value = user1.getMiddleName().compareTo(user2.getMiddleName());
		}

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