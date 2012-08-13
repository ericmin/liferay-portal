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
public interface PortletPreferencesFinder {
	public java.util.List<com.liferay.portal.model.PortletPreferences> findByPortletId(
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.model.PortletPreferences> findByC_G_O_O_P_P(
		long companyId, long groupId, long ownerId, int ownerType,
		java.lang.String portletId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException;
}