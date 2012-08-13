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

package com.liferay.portlet.asset.service.persistence;

/**
 * @author Brian Wing Shun Chan
 */
public interface AssetCategoryFinder {
	public int countByG_C_N(long groupId, long classNameId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_N_V(long groupId, java.lang.String name,
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByG_N_P(long groupId, java.lang.String name,
		java.lang.String[] categoryProperties)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByG_N_V(long groupId, java.lang.String name,
		long vocabularyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> filterFindByG_N_V(
		long groupId, java.lang.String name, long vocabularyId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByEntryId(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.Long> findByG_L(
		java.lang.Long parentCategoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.asset.model.AssetCategory findByG_N(
		long groupId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portlet.asset.NoSuchCategoryException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByG_N_V(
		long groupId, java.lang.String name, long vocabularyId, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByG_N_P(
		long groupId, java.lang.String name,
		java.lang.String[] categoryProperties)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetCategory> findByG_N_P(
		long groupId, java.lang.String name,
		java.lang.String[] categoryProperties, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}