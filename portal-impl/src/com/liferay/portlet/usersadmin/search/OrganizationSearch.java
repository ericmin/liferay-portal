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

package com.liferay.portlet.usersadmin.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class OrganizationSearch extends SearchContainer<Organization> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("name");
		headerNames.add("parent-organization");
		headerNames.add("type");
		headerNames.add("city");
		headerNames.add("region");
		headerNames.add("country");

		orderableHeaders.put("name", "name");
		orderableHeaders.put("type", "type");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-organizations-were-found";

	public OrganizationSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, DEFAULT_CUR_PARAM, iteratorURL);
	}

	public OrganizationSearch(
		PortletRequest portletRequest, String curParam,
		PortletURL iteratorURL) {

		super(
			portletRequest, new OrganizationDisplayTerms(portletRequest),
			new OrganizationSearchTerms(portletRequest), curParam,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		OrganizationDisplayTerms displayTerms =
			(OrganizationDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OrganizationDisplayTerms.CITY, displayTerms.getCity());
		iteratorURL.setParameter(
			OrganizationDisplayTerms.COUNTRY_ID,
			String.valueOf(displayTerms.getCountryId()));
		iteratorURL.setParameter(
			OrganizationDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			OrganizationDisplayTerms.PARENT_ORGANIZATION_ID,
			String.valueOf(displayTerms.getParentOrganizationId()));
		iteratorURL.setParameter(
			OrganizationDisplayTerms.REGION_ID,
			String.valueOf(displayTerms.getRegionId()));
		iteratorURL.setParameter(
			OrganizationDisplayTerms.STREET, displayTerms.getStreet());
		iteratorURL.setParameter(
			OrganizationDisplayTerms.ZIP, displayTerms.getZip());

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
					PortletKeys.USERS_ADMIN, "organizations-order-by-col",
					orderByCol);
				preferences.setValue(
					PortletKeys.USERS_ADMIN, "organizations-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					PortletKeys.USERS_ADMIN, "organizations-order-by-col",
					"name");
				orderByType = preferences.getValue(
					PortletKeys.USERS_ADMIN, "organizations-order-by-type",
					"asc");
			}

			OrderByComparator orderByComparator =
				UsersAdminUtil.getOrganizationOrderByComparator(
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

	private static Log _log = LogFactoryUtil.getLog(OrganizationSearch.class);

}