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

package com.liferay.portlet.asset.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Jorge Ferrer
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 */
public interface AssetRendererFactory {

	public static final int TYPE_LATEST = 0;

	public static final int TYPE_LATEST_APPROVED = 1;

	public AssetEntry getAssetEntry(long assetEntryId)
		throws PortalException, SystemException;

	public AssetEntry getAssetEntry(String classNameId, long classPK)
		throws PortalException, SystemException;

	public AssetRenderer getAssetRenderer(long classPK)
		throws PortalException, SystemException;

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException;

	public AssetRenderer getAssetRenderer(long groupId, String urlTitle)
		throws PortalException, SystemException;

	public String getClassName();

	public long getClassNameId();

	public Map<Long, String> getClassTypes(long[] groupIds, Locale locale)
		throws Exception;

	public String getIconPath(PortletRequest portletRequest);

	public String getPortletId();

	public String getType();

	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException, SystemException;

	public boolean hasPermission(
			PermissionChecker permissionChecker, long entryClassPK,
			String actionId)
		throws Exception;

	public boolean isCategorizable();

	public boolean isLinkable();

	public boolean isSelectable();

	public void setClassName(String className);

	public void setPortletId(String portletId);

}