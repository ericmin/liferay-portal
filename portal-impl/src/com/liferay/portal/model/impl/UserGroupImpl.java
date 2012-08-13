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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class UserGroupImpl extends UserGroupBaseImpl {

	public UserGroupImpl() {
	}

	public Group getGroup() throws PortalException, SystemException {
		return GroupLocalServiceUtil.getUserGroupGroup(
			getCompanyId(), getUserGroupId());
	}

	public int getPrivateLayoutsPageCount()
		throws PortalException, SystemException {

		Group group = getGroup();

		return group.getPrivateLayoutsPageCount();
	}

	public int getPublicLayoutsPageCount()
		throws PortalException, SystemException {

		Group group = getGroup();

		return group.getPublicLayoutsPageCount();
	}

	public boolean hasPrivateLayouts() throws PortalException, SystemException {
		if (getPrivateLayoutsPageCount() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPublicLayouts() throws PortalException, SystemException {
		if (getPublicLayoutsPageCount() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

}