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
public class LayoutSetBranchFinderUtil {
	public static com.liferay.portal.model.LayoutSetBranch findByMaster(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.NoSuchLayoutSetBranchException,
			com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByMaster(groupId, privateLayout);
	}

	public static LayoutSetBranchFinder getFinder() {
		if (_finder == null) {
			_finder = (LayoutSetBranchFinder)PortalBeanLocatorUtil.locate(LayoutSetBranchFinder.class.getName());

			ReferenceRegistry.registerReference(LayoutSetBranchFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(LayoutSetBranchFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(LayoutSetBranchFinderUtil.class,
			"_finder");
	}

	private static LayoutSetBranchFinder _finder;
}