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
public class ResourceFinderUtil {
	public static java.util.List<com.liferay.portal.model.Resource> findByContainerResource(
		long codeId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByContainerResource(codeId, classNameId);
	}

	public static java.util.List<com.liferay.portal.model.Resource> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByName(name);
	}

	public static java.util.List<com.liferay.portal.model.Resource> findByNoActions(
		long codeId, java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoActions(codeId, actionId);
	}

	public static java.util.List<com.liferay.portal.model.Resource> findByC_P(
		long companyId, java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByC_P(companyId, primKey);
	}

	public static java.util.List<com.liferay.portal.model.Resource> findByN_S(
		java.lang.String name, int scope)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByN_S(name, scope);
	}

	public static ResourceFinder getFinder() {
		if (_finder == null) {
			_finder = (ResourceFinder)PortalBeanLocatorUtil.locate(ResourceFinder.class.getName());

			ReferenceRegistry.registerReference(ResourceFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ResourceFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ResourceFinderUtil.class, "_finder");
	}

	private static ResourceFinder _finder;
}