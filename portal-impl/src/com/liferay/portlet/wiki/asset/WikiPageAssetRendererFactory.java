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

package com.liferay.portlet.wiki.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageResource;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil;
import com.liferay.portlet.wiki.service.permission.WikiPagePermission;

/**
 * @author Julio Camarero
 * @author Juan Fernández
 * @author Jorge Ferrer
 * @author Raymond Augé
 * @author Sergio González
 */
public class WikiPageAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String CLASS_NAME = WikiPage.class.getName();

	public static final String TYPE = "wiki";

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		WikiPage page = null;

		try {
			page = WikiPageLocalServiceUtil.getWikiPage(classPK);
		}
		catch (NoSuchPageException nspe) {
			if (type == TYPE_LATEST_APPROVED) {
				page = WikiPageLocalServiceUtil.getPage(classPK);
			}
			else {
				WikiPageResource wikiPageResource =
					WikiPageResourceLocalServiceUtil.getPageResource(classPK);

				page = WikiPageLocalServiceUtil.getPage(
					wikiPageResource.getNodeId(), wikiPageResource.getTitle(),
					null);
			}
		}

		return new WikiPageAssetRenderer(page);
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

		return WikiPagePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Override
	public boolean isLinkable() {
		return _LINKABLE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/pages.png";
	}

	private static final boolean _LINKABLE = true;

}