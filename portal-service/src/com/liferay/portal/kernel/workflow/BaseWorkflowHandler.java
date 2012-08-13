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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Bruno Farache
 * @author Marcellus Tavares
 * @author Juan Fernández
 * @author Julio Camarero
 * @author Jorge Ferrer
 */
public abstract class BaseWorkflowHandler implements WorkflowHandler {

	public AssetRenderer getAssetRenderer(long classPK)
		throws PortalException, SystemException {

		AssetRendererFactory assetRendererFactory = getAssetRendererFactory();

		if (assetRendererFactory != null) {
			return assetRendererFactory.getAssetRenderer(
				classPK, AssetRendererFactory.TYPE_LATEST);
		}
		else {
			return null;
		}
	}

	public AssetRendererFactory getAssetRendererFactory() {
		return AssetRendererFactoryRegistryUtil.
			getAssetRendererFactoryByClassName(getClassName());
	}

	public String getIconPath(LiferayPortletRequest liferayPortletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return getIconPath(themeDisplay);
	}

	public String getSummary(long classPK, Locale locale) {
		try {
			AssetRenderer assetRenderer = getAssetRenderer(classPK);

			if (assetRenderer != null) {
				return assetRenderer.getSummary(locale);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	public String getTitle(long classPK, Locale locale) {
		try {
			AssetRenderer assetRenderer = getAssetRenderer(classPK);

			if (assetRenderer != null) {
				return assetRenderer.getTitle(locale);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	public PortletURL getURLEdit(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		try {
			AssetRenderer assetRenderer = getAssetRenderer(classPK);

			if (assetRenderer != null) {
				return assetRenderer.getURLEdit(
					liferayPortletRequest, liferayPortletResponse);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	public String getURLViewInContext(
		long classPK, LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		try {
			AssetRenderer assetRenderer = getAssetRenderer(classPK);

			if (assetRenderer != null) {
				return assetRenderer.getURLViewInContext(
					liferayPortletRequest, liferayPortletResponse,
					noSuchEntryRedirect);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException, SystemException {

		return WorkflowDefinitionLinkLocalServiceUtil.getWorkflowDefinitionLink(
			companyId, groupId, getClassName(), 0, 0);
	}

	public boolean isAssetTypeSearchable() {
		return _ASSET_TYPE_SEARCHABLE;
	}

	public boolean isScopeable() {
		return _SCOPEABLE;
	}

	public boolean isVisible() {
		return _VISIBLE;
	}

	public String render(
		long classPK, RenderRequest renderRequest,
		RenderResponse renderResponse, String template) {

		try {
			AssetRenderer assetRenderer = getAssetRenderer(classPK);

			if (assetRenderer != null) {
				return assetRenderer.render(
					renderRequest, renderResponse, template);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return null;
	}

	public void startWorkflowInstance(
			long companyId, long groupId, long userId, long classPK,
			Object model, Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		WorkflowInstanceLinkLocalServiceUtil.startWorkflowInstance(
			companyId, groupId, userId, getClassName(), classPK,
			workflowContext);
	}

	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/page.png";
	}

	private static final boolean _ASSET_TYPE_SEARCHABLE = true;

	private static final boolean _SCOPEABLE = true;

	private static final boolean _VISIBLE = true;

	private static Log _log = LogFactoryUtil.getLog(BaseWorkflowHandler.class);

}