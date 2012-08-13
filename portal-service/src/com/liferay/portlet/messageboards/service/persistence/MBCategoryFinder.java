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

/**
 * @author Brian Wing Shun Chan
 */
public interface MBCategoryFinder {
	public int countByS_G_U_P(long groupId, long userId,
		long[] parentCategoryIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByS_G_U_P(long groupId, long userId,
		long[] parentCategoryIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.messageboards.model.MBCategory> filterFindByS_G_U_P(
		long groupId, long userId, long[] parentCategoryIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.messageboards.model.MBCategory> findByS_G_U_P(
		long groupId, long userId, long[] parentCategoryIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}