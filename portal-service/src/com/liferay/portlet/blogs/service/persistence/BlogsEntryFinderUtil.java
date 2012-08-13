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

package com.liferay.portlet.blogs.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class BlogsEntryFinderUtil {
	public static int countByOrganizationId(long organizationId,
		java.util.Date displayDate, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByOrganizationId(organizationId, displayDate, status);
	}

	public static int countByOrganizationIds(
		java.util.List<java.lang.Long> organizationIds,
		java.util.Date displayDate, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByOrganizationIds(organizationIds, displayDate, status);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> findByGroupIds(
		long companyId, long groupId, java.util.Date displayDate, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByGroupIds(companyId, groupId, displayDate, status,
			start, end);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> findByOrganizationId(
		long organizationId, java.util.Date displayDate, int status, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByOrganizationId(organizationId, displayDate, status,
			start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> findByOrganizationIds(
		java.util.List<java.lang.Long> organizationIds,
		java.util.Date displayDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByOrganizationIds(organizationIds, displayDate, status,
			start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.blogs.model.BlogsEntry> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static BlogsEntryFinder getFinder() {
		if (_finder == null) {
			_finder = (BlogsEntryFinder)PortalBeanLocatorUtil.locate(BlogsEntryFinder.class.getName());

			ReferenceRegistry.registerReference(BlogsEntryFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(BlogsEntryFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(BlogsEntryFinderUtil.class,
			"_finder");
	}

	private static BlogsEntryFinder _finder;
}