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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class DLSyncFinderUtil {
	public static java.util.List<com.liferay.portlet.documentlibrary.model.DLSync> filterFindByC_M_R(
		long companyId, java.util.Date modifiedDate, long repositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_M_R(companyId, modifiedDate, repositoryId);
	}

	public static DLSyncFinder getFinder() {
		if (_finder == null) {
			_finder = (DLSyncFinder)PortalBeanLocatorUtil.locate(DLSyncFinder.class.getName());

			ReferenceRegistry.registerReference(DLSyncFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DLSyncFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DLSyncFinderUtil.class, "_finder");
	}

	private static DLSyncFinder _finder;
}