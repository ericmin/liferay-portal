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

package com.liferay.portlet.journalcontent.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.journal.model.JournalArticleDisplay;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;
import com.liferay.util.portlet.PortletRequestUtil;

import java.io.OutputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Raymond Augé
 */
public class WebContentAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences preferences = actionRequest.getPreferences();

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(actionRequest, "groupId");

		if (groupId < 1) {
			groupId = GetterUtil.getLong(
				preferences.getValue("groupId", StringPool.BLANK));
		}

		String articleId = ParamUtil.getString(actionRequest, "articleId");
		String templateId = ParamUtil.getString(actionRequest, "templateId");

		if (Validator.isNull(articleId)) {
			articleId = GetterUtil.getString(
				preferences.getValue("articleId", StringPool.BLANK));
			templateId = GetterUtil.getString(
				preferences.getValue("templateId", StringPool.BLANK));
		}

		String viewMode = ParamUtil.getString(actionRequest, "viewMode");
		String languageId = LanguageUtil.getLanguageId(actionRequest);
		int page = ParamUtil.getInteger(actionRequest, "page", 1);

		String xmlRequest = PortletRequestUtil.toXML(
			actionRequest, actionResponse);

		if ((groupId > 0) && Validator.isNotNull(articleId)) {
			JournalContentUtil.getDisplay(
				groupId, articleId, templateId, viewMode, languageId,
				themeDisplay, page, xmlRequest);
		}
	}

	@Override
	public void serveResource(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String contentType = ParamUtil.getString(
			resourceRequest, "contentType");

		if (Validator.isNotNull(contentType)) {
			resourceResponse.setContentType(contentType);
		}

		if (resourceRequest.getResourceID() != null) {
			super.serveResource(
				mapping, form, portletConfig, resourceRequest,
				resourceResponse);
		}
		else {
			PortletPreferences preferences = resourceRequest.getPreferences();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long groupId = ParamUtil.getLong(resourceRequest, "groupId");

			if (groupId < 1) {
				groupId = GetterUtil.getLong(
					preferences.getValue("groupId", StringPool.BLANK));
			}

			String articleId = ParamUtil.getString(
				resourceRequest, "articleId");
			String templateId = ParamUtil.getString(
				resourceRequest, "templateId");

			if (Validator.isNull(articleId)) {
				articleId = GetterUtil.getString(
					preferences.getValue("articleId", StringPool.BLANK));
				templateId = GetterUtil.getString(
					preferences.getValue("templateId", StringPool.BLANK));
			}

			String viewMode = ParamUtil.getString(resourceRequest, "viewMode");
			String languageId = LanguageUtil.getLanguageId(resourceRequest);
			int page = ParamUtil.getInteger(resourceRequest, "page", 1);
			String xmlRequest = PortletRequestUtil.toXML(
				resourceRequest, resourceResponse);

			JournalArticleDisplay articleDisplay = null;

			if ((groupId > 0) && Validator.isNotNull(articleId)) {
				articleDisplay = JournalContentUtil.getDisplay(
					groupId, articleId, templateId, viewMode, languageId,
					themeDisplay, page, xmlRequest);
			}

			if (articleDisplay != null) {
				OutputStream os = resourceResponse.getPortletOutputStream();

				try {
					String content = articleDisplay.getContent();

					byte[] bytes = content.getBytes(StringPool.UTF8);

					os.write(bytes);
				}
				finally {
					os.close();
				}
			}
		}
	}

}