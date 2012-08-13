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

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class MBThreadFinderUtil {
	public static int countByG_U_S(long groupId, long userId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_U_S(groupId, userId, status);
	}

	public static int countByG_C_S(long groupId, long categoryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_C_S(groupId, categoryId, status);
	}

	public static int countByG_U_MD_S(long groupId, long userId,
		java.util.Date modifiedDate, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_U_MD_S(groupId, userId, modifiedDate, status);
	}

	public static int countByG_U_A_S(long groupId, long userId,
		boolean anonymous, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_U_A_S(groupId, userId, anonymous, status);
	}

	public static int countByS_G_U_S(long groupId, long userId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByS_G_U_S(groupId, userId, status);
	}

	public static int countByS_G_U_C_S(long groupId, long userId,
		long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByS_G_U_C_S(groupId, userId, categoryIds, status);
	}

	public static int filterCountByG_C(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterCountByG_C(groupId, categoryId);
	}

	public static int filterCountByG_C_S(long groupId, long categoryId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterCountByG_C_S(groupId, categoryId, status);
	}

	public static int filterCountByS_G_U_C_S(long groupId, long userId,
		long[] categoryIds, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByS_G_U_C_S(groupId, userId, categoryIds, status);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> filterFindByG_C(
		long groupId, long categoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().filterFindByG_C(groupId, categoryId, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> filterFindByG_C_S(
		long groupId, long categoryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByG_C_S(groupId, categoryId, status, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> filterFindByS_G_U_C_S(
		long groupId, long userId, long[] categoryIds, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByS_G_U_C_S(groupId, userId, categoryIds, status,
			start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByNoAssets()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNoAssets();
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByG_U_S(
		long groupId, long userId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_U_S(groupId, userId, status, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByG_C_S(
		long groupId, long categoryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_C_S(groupId, categoryId, status, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByG_U_MD_S(
		long groupId, long userId, java.util.Date modifiedDate, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_U_MD_S(groupId, userId, modifiedDate, status,
			start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByG_U_A_S(
		long groupId, long userId, boolean anonymous, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByG_U_A_S(groupId, userId, anonymous, status, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByS_G_U_S(
		long groupId, long userId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByS_G_U_S(groupId, userId, status, start, end);
	}

	public static java.util.List<com.liferay.portlet.messageboards.model.MBThread> findByS_G_U_C_S(
		long groupId, long userId, long[] categoryIds, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByS_G_U_C_S(groupId, userId, categoryIds, status,
			start, end);
	}

	public static MBThreadFinder getFinder() {
		if (_finder == null) {
			_finder = (MBThreadFinder)PortalBeanLocatorUtil.locate(MBThreadFinder.class.getName());

			ReferenceRegistry.registerReference(MBThreadFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(MBThreadFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(MBThreadFinderUtil.class, "_finder");
	}

	private static MBThreadFinder _finder;
}