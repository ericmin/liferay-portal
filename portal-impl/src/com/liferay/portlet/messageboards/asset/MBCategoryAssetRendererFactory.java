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

package com.liferay.portlet.messageboards.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.permission.MBCategoryPermission;

/**
 * @author Julio Camarero
 * @author Juan Fernández
 * @author Raymond Augé
 * @author Sergio González
 * @author Jonathan Lee
 */
public class MBCategoryAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = MBCategory.class.getName();

	public static final String TYPE = "category";

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		MBCategory category = MBCategoryLocalServiceUtil.getMBCategory(classPK);

		return new MBCategoryAssetRenderer(category);
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		MBCategory category = MBCategoryLocalServiceUtil.getMBCategory(classPK);

		return MBCategoryPermission.contains(
			permissionChecker, category, actionId);
	}

	@Override
	public boolean isCategorizable() {
		return false;
	}

	@Override
	public boolean isSelectable() {
		return _SELECTABLE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/conversation.png";
	}

	private static final boolean _SELECTABLE = false;

}