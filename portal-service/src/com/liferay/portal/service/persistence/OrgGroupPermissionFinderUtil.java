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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class OrgGroupPermissionFinderUtil {
	public static void removeByO_G_R(long organizationId, long groupId,
		long resourceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getFinder().removeByO_G_R(organizationId, groupId, resourceId);
	}

	public static OrgGroupPermissionFinder getFinder() {
		if (_finder == null) {
			_finder = (OrgGroupPermissionFinder)PortalBeanLocatorUtil.locate(OrgGroupPermissionFinder.class.getName());

			ReferenceRegistry.registerReference(OrgGroupPermissionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(OrgGroupPermissionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(OrgGroupPermissionFinderUtil.class,
			"_finder");
	}

	private static OrgGroupPermissionFinder _finder;
}