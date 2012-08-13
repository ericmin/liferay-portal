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
public interface AssetTagPropertyFinder {
	public int countByG_K(long groupId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTagProperty> findByG_K(
		long groupId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.asset.model.AssetTagProperty> findByG_K(
		long groupId, java.lang.String key, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;
}