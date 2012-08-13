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
import com.liferay.portlet.journal.model.JournalStructure;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class StructureSearch extends SearchContainer<JournalStructure> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("id");
		headerNames.add("name");
		headerNames.add("description");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-structures-were-found";

	public StructureSearch(
		PortletRequest portletRequest, int delta, PortletURL iteratorURL) {

		super(
			portletRequest, new StructureDisplayTerms(portletRequest),
			new StructureSearchTerms(portletRequest), DEFAULT_CUR_PARAM, delta,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		StructureDisplayTerms displayTerms =
			(StructureDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			StructureDisplayTerms.DESCRIPTION, displayTerms.getDescription());
		iteratorURL.setParameter(
			StructureDisplayTerms.GROUP_IDS,
			StringUtil.merge(displayTerms.getGroupIds()));
		iteratorURL.setParameter(
			StructureDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			StructureDisplayTerms.STRUCTURE_ID, displayTerms.getStructureId());
	}

	public StructureSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, DEFAULT_DELTA, iteratorURL);
	}

}