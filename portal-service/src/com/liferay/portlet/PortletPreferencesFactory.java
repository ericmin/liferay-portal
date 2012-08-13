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

package com.liferay.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletPreferencesIds;

import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PreferencesValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 */
public interface PortletPreferencesFactory {

	public PortletPreferences fromDefaultXML(String xml) throws SystemException;

	public PortletPreferences fromXML(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws SystemException;

	public PortalPreferences fromXML(
			long companyId, long ownerId, int ownerType, String xml)
		throws SystemException;

	public PortletPreferences getLayoutPortletSetup(
			Layout layout, String portletId)
		throws SystemException;

	public PortalPreferences getPortalPreferences(HttpServletRequest request)
		throws SystemException;

	public PortalPreferences getPortalPreferences(
			HttpSession session, long companyId, long userId, boolean signedIn)
		throws SystemException;

	public PortalPreferences getPortalPreferences(
			long companyId, long userId, boolean signedIn)
		throws SystemException;

	public PortalPreferences getPortalPreferences(PortletRequest portletRequest)
		throws SystemException;

	public PortletPreferences getPortletPreferences(
			HttpServletRequest request, String portletId)
		throws PortalException, SystemException;

	public PortletPreferencesIds getPortletPreferencesIds(
			HttpServletRequest request, Layout selLayout, String portletId)
		throws PortalException, SystemException;

	public PortletPreferencesIds getPortletPreferencesIds(
			HttpServletRequest request, String portletId)
		throws PortalException, SystemException;

	public PortletPreferencesIds getPortletPreferencesIds(
			long scopeGroupId, long userId, Layout layout, String portletId,
			boolean modeEditGuest)
		throws PortalException, SystemException;

	public PortletPreferences getPortletSetup(
			HttpServletRequest request, String portletId)
		throws PortalException, SystemException;

	public PortletPreferences getPortletSetup(
			HttpServletRequest request, String portletId,
			String defaultPreferences)
		throws PortalException, SystemException;

	public PortletPreferences getPortletSetup(
			Layout layout, String portletId, String defaultPreferences)
		throws SystemException;

	public PortletPreferences getPortletSetup(
			long scopeGroupId, Layout layout, String portletId,
			String defaultPreferences)
		throws SystemException;

	public PortletPreferences getPortletSetup(PortletRequest portletRequest)
		throws PortalException, SystemException;

	public PortletPreferences getPortletSetup(
			PortletRequest portletRequest, String portletId)
		throws PortalException, SystemException;

	public Map<Long, PortletPreferences> getPortletSetupMap(
			long companyId, long groupId, long ownerId, int ownerType,
			String portletId, boolean privateLayout)
		throws SystemException;

	public PortletPreferences getPreferences(HttpServletRequest request);

	public PreferencesValidator getPreferencesValidator(Portlet portlet);

	public PortletPreferences getStrictLayoutPortletSetup(
			Layout layout, String portletId)
		throws SystemException;

	public String toXML(PortalPreferences portalPreferences);

	public String toXML(PortletPreferences portletPreferences);

}