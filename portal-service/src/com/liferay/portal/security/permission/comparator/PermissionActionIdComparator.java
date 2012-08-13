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

package com.liferay.portal.security.permission.comparator;

import com.liferay.portal.model.Permission;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Brian Wing Shun Chan
 */
public class PermissionActionIdComparator
	implements Comparator<Object>, Serializable {

	public int compare(Object obj1, Object obj2) {
		String actionId1 = null;

		if (obj1 instanceof String) {
			actionId1 = (String)obj1;
		}
		else {
			Permission permission1 = (Permission)obj1;

			actionId1 = permission1.getActionId();
		}

		String actionId2 = null;

		if (obj2 instanceof String) {
			actionId2 = (String)obj2;
		}
		else {
			Permission permission2 = (Permission)obj2;

			actionId2 = permission2.getActionId();
		}

		return actionId1.compareTo(actionId2);
	}

}