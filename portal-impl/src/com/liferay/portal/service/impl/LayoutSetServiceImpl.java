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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.base.LayoutSetServiceBaseImpl;
import com.liferay.portal.service.permission.GroupPermissionUtil;
import com.liferay.portal.service.permission.PortalPermissionUtil;

import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutSetServiceImpl extends LayoutSetServiceBaseImpl {

	/**
	 * @deprecated {@link #updateLayoutSetPrototypeLinkEnabled(long, boolean,
	 *             boolean, String)}
	 */
	public void updateLayoutSetPrototypeLinkEnabled(
			long groupId, boolean privateLayout,
			boolean layoutSetPrototypeLinkEnabled)
		throws PortalException, SystemException {

		updateLayoutSetPrototypeLinkEnabled(
			groupId, privateLayout, layoutSetPrototypeLinkEnabled, null);
	}

	/**
	 * Updates the state of the layout set prototype link.
	 *
	 * <p>
	 * <strong>Important:</strong> Setting
	 * <code>layoutSetPrototypeLinkEnabled</code> to <code>true</code> and
	 * <code>layoutSetPrototypeUuid</code> to <code>null</code> when the layout
	 * set prototype's current uuid is <code>null</code> will result in an
	 * <code>IllegalStateException</code>.
	 * </p>
	 *
	 * @param  groupId the primary key of the group
	 * @param  privateLayout whether the layout set is private to the group
	 * @param  layoutSetPrototypeLinkEnabled whether the layout set prototype is
	 *         link enabled
	 * @param  layoutSetPrototypeUuid the uuid of the layout set prototype to
	 *         link with
	 * @throws PortalException if a portal exception occurred
	 * @throws SystemException if a system exception occurred
	 */
	public void updateLayoutSetPrototypeLinkEnabled(
			long groupId, boolean privateLayout,
			boolean layoutSetPrototypeLinkEnabled,
			String layoutSetPrototypeUuid)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		LayoutSet layoutSet = layoutSetLocalService.getLayoutSet(
			groupId, privateLayout);

		if (layoutSet.isLayoutSetPrototypeLinkEnabled() &&
			!layoutSetPrototypeLinkEnabled) {

			PortalPermissionUtil.check(
				getPermissionChecker(), ActionKeys.UNLINK_LAYOUT_SET_PROTOTYPE);
		}

		layoutSetLocalService.updateLayoutSetPrototypeLinkEnabled(
			groupId, privateLayout, layoutSetPrototypeLinkEnabled,
			layoutSetPrototypeUuid);
	}

	public void updateLogo(
			long groupId, boolean privateLayout, boolean logo,
			InputStream inputStream)
		throws PortalException, SystemException {

		updateLogo(groupId, privateLayout, logo, inputStream, true);
	}

	public void updateLogo(
			long groupId, boolean privateLayout, boolean logo,
			InputStream inputStream, boolean cleanUpStream)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		layoutSetLocalService.updateLogo(
			groupId, privateLayout, logo, inputStream, cleanUpStream);
	}

	public LayoutSet updateLookAndFeel(
			long groupId, boolean privateLayout, String themeId,
			String colorSchemeId, String css, boolean wapTheme)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		pluginSettingLocalService.checkPermission(
			getUserId(), themeId, Plugin.TYPE_THEME);

		return layoutSetLocalService.updateLookAndFeel(
			groupId, privateLayout, themeId, colorSchemeId, css, wapTheme);
	}

	public LayoutSet updateSettings(
			long groupId, boolean privateLayout, String settings)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		return layoutSetLocalService.updateSettings(
			groupId, privateLayout, settings);
	}

	public LayoutSet updateVirtualHost(
			long groupId, boolean privateLayout, String virtualHost)
		throws PortalException, SystemException {

		GroupPermissionUtil.check(
			getPermissionChecker(), groupId, ActionKeys.UPDATE);

		return layoutSetLocalService.updateVirtualHost(
			groupId, privateLayout, virtualHost);
	}

}