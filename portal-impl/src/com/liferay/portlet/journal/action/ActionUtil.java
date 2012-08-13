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

package com.liferay.portlet.journal.action;

import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.journal.NoSuchStructureException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFeed;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalArticleServiceUtil;
import com.liferay.portlet.journal.service.JournalFeedServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateServiceUtil;
import com.liferay.portlet.journal.util.JournalUtil;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionUtil {

	public static void getArticle(HttpServletRequest request) throws Exception {
		String cmd = ParamUtil.getString(request, Constants.CMD);

		long groupId = ParamUtil.getLong(request, "groupId");
		long classNameId = ParamUtil.getLong(request, "classNameId");
		long classPK = ParamUtil.getLong(request, "classPK");
		String articleId = ParamUtil.getString(request, "articleId");
		String structureId = ParamUtil.getString(request, "structureId");

		JournalArticle article = null;

		if (!cmd.equals(Constants.ADD) && Validator.isNotNull(articleId)) {
			article = JournalArticleServiceUtil.getLatestArticle(
				groupId, articleId, WorkflowConstants.STATUS_ANY);
		}
		else if ((classNameId > 0) && (classPK > 0)) {
			String className = PortalUtil.getClassName(classNameId);

			article = JournalArticleServiceUtil.getLatestArticle(
				groupId, className, classPK);
		}
		else if (Validator.isNotNull(structureId)) {
			JournalStructure structure = null;

			try {
				structure = JournalStructureServiceUtil.getStructure(
					groupId, structureId);
			}
			catch (NoSuchStructureException nsse1) {
				ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
					WebKeys.THEME_DISPLAY);

				if (groupId == themeDisplay.getCompanyGroupId()) {
					return;
				}

				try {
					structure = JournalStructureServiceUtil.getStructure(
						themeDisplay.getCompanyGroupId(), structureId);
				}
				catch (NoSuchStructureException nsse2) {
					return;
				}
			}

			article = JournalArticleServiceUtil.getArticle(
				groupId, JournalStructure.class.getName(), structure.getId());

			article.setNew(true);

			article.setId(0);
			article.setClassNameId(0);
			article.setClassPK(0);
			article.setArticleId(null);
			article.setVersion(0);
		}

		request.setAttribute(WebKeys.JOURNAL_ARTICLE, article);
	}

	public static void getArticle(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getArticle(request);

		JournalArticle article = (JournalArticle)portletRequest.getAttribute(
			WebKeys.JOURNAL_ARTICLE);

		JournalUtil.addRecentArticle(portletRequest, article);
	}

	public static void getFeed(HttpServletRequest request) throws Exception {
		long groupId = ParamUtil.getLong(request, "groupId");
		String feedId = ParamUtil.getString(request, "feedId");

		JournalFeed feed = null;

		if (Validator.isNotNull(feedId)) {
			feed = JournalFeedServiceUtil.getFeed(groupId, feedId);
		}

		request.setAttribute(WebKeys.JOURNAL_FEED, feed);
	}

	public static void getFeed(PortletRequest portletRequest) throws Exception {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getFeed(request);
	}

	public static void getStructure(HttpServletRequest request)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");
		String structureId = ParamUtil.getString(request, "structureId");

		JournalStructure structure = null;

		if (Validator.isNotNull(structureId)) {
			structure = JournalStructureServiceUtil.getStructure(
				groupId, structureId);
		}

		request.setAttribute(WebKeys.JOURNAL_STRUCTURE, structure);
	}

	public static void getStructure(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getStructure(request);

		JournalStructure structure =
			(JournalStructure)portletRequest.getAttribute(
				WebKeys.JOURNAL_STRUCTURE);

		JournalUtil.addRecentStructure(portletRequest, structure);
	}

	public static void getTemplate(HttpServletRequest request)
		throws Exception {

		long groupId = ParamUtil.getLong(request, "groupId");
		String templateId = ParamUtil.getString(request, "templateId");

		JournalTemplate template = null;

		if (Validator.isNotNull(templateId)) {
			template = JournalTemplateServiceUtil.getTemplate(
				groupId, templateId);
		}

		request.setAttribute(WebKeys.JOURNAL_TEMPLATE, template);
	}

	public static void getTemplate(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getTemplate(request);

		JournalTemplate template = (JournalTemplate)portletRequest.getAttribute(
			WebKeys.JOURNAL_TEMPLATE);

		JournalUtil.addRecentTemplate(portletRequest, template);
	}

}