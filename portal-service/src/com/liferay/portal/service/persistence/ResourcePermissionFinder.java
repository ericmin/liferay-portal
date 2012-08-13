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

/**
 * @author Brian Wing Shun Chan
 */
public interface ResourcePermissionFinder {
	public int countByR_S(long roleId, int[] scopes)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_N_S_P_R_A(long companyId, java.lang.String name,
		int scope, java.lang.String primKey, long[] roleIds, long actionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.ResourcePermission> findByResource(
		long companyId, long groupId, java.lang.String name,
		java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.ResourcePermission> findByC_P(
		long companyId, java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.ResourcePermission> findByR_S(
		long roleId, int[] scopes, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<java.lang.String> findByC_N_S(long companyId,
		java.lang.String name, int scope)
		throws com.liferay.portal.kernel.exception.SystemException;
}