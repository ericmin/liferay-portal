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

package com.liferay.portlet.journal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.util.JournalUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleSearch extends SearchContainer<JournalArticle> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("id");
		headerNames.add("title");
		//headerNames.add("version");
		headerNames.add("modified-date");
		headerNames.add("display-date");
		headerNames.add("author");

		orderableHeaders.put("id", "id");
		orderableHeaders.put("name", "title");
		//orderableHeaders.put("version", "version");
		orderableHeaders.put("modified-date", "modified-date");
		orderableHeaders.put("display-date", "display-date");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-web-content-were-found";

	public ArticleSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new ArticleDisplayTerms(portletRequest),
			new ArticleSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		ArticleDisplayTerms displayTerms =
			(ArticleDisplayTerms)getDisplayTerms();
		ArticleSearchTerms searchTerms = (ArticleSearchTerms)getSearchTerms();

		String portletName = portletConfig.getPortletName();

		if (!portletName.equals(PortletKeys.JOURNAL)) {
			displayTerms.setStatus("approved");
			searchTerms.setStatus("approved");
		}

		iteratorURL.setParameter(
			ArticleDisplayTerms.ARTICLE_ID, displayTerms.getArticleId());
		iteratorURL.setParameter(
			ArticleDisplayTerms.CONTENT, displayTerms.getContent());
		iteratorURL.setParameter(
			ArticleDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			ArticleDisplayTerms.GROUP_ID,
			String.valueOf(displayTerms.getGroupId()));
		iteratorURL.setParameter(
			ArticleDisplayTerms.STATUS, displayTerms.getStatus());
		iteratorURL.setParameter(
			ArticleDisplayTerms.STRUCTURE_ID, displayTerms.getStructureId());
		iteratorURL.setParameter(
			ArticleDisplayTerms.TEMPLATE_ID, displayTerms.getTemplateId());
		iteratorURL.setParameter(
			ArticleDisplayTerms.TITLE, displayTerms.getTitle());
		iteratorURL.setParameter(
			ArticleDisplayTerms.TYPE, displayTerms.getType());
		iteratorURL.setParameter(
			ArticleDisplayTerms.VERSION,
			String.valueOf(displayTerms.getVersion()));

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					PortletKeys.JOURNAL, "articles-order-by-col", orderByCol);
				preferences.setValue(
					PortletKeys.JOURNAL, "articles-order-by-type", orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					PortletKeys.JOURNAL, "articles-order-by-col", "id");
				orderByType = preferences.getValue(
					PortletKeys.JOURNAL, "articles-order-by-type", "asc");
			}

			OrderByComparator orderByComparator =
				JournalUtil.getArticleOrderByComparator(
					orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ArticleSearch.class);

}