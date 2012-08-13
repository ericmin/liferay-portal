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
import com.liferay.portal.model.Permission;

/**
 * @author Alexander Chow
 */
public class PermissionComparator extends OrderByComparator {

	public static final String ORDER_BY_DESC = "Permission_.permissionId DESC";

	public static final String[] ORDER_BY_FIELDS = {"permissionId"};

	@Override
	public int compare(Object obj1, Object obj2) {
		Permission perm1 = (Permission)obj1;
		Permission perm2 = (Permission)obj2;

		long permissionId1 = perm1.getPermissionId();
		long permissionId2 = perm2.getPermissionId();

		if (permissionId1 > permissionId2) {
			return -1;
		}
		else if (permissionId1 < permissionId2) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public String getOrderBy() {
		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return false;
	}

}