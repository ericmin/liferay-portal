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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.journal.model.JournalTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class TemplateSearch extends SearchContainer<JournalTemplate> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("id");
		headerNames.add("name");
		headerNames.add("description");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-templates-were-found";

	public TemplateSearch(
		PortletRequest portletRequest, int delta, PortletURL iteratorURL) {

		super(
			portletRequest, new TemplateDisplayTerms(portletRequest),
			new TemplateSearchTerms(portletRequest), DEFAULT_CUR_PARAM, delta,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		TemplateDisplayTerms displayTerms =
			(TemplateDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TemplateDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			TemplateDisplayTerms.GROUP_IDS,
			StringUtil.merge(displayTerms.getGroupIds()));
		iteratorURL.setParameter(
			TemplateDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			TemplateDisplayTerms.STRUCTURE_ID, displayTerms.getStructureId());
		iteratorURL.setParameter(
			TemplateDisplayTerms.TEMPLATE_ID, displayTerms.getTemplateId());
	}

	public TemplateSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, DEFAULT_DELTA, iteratorURL);
	}

}