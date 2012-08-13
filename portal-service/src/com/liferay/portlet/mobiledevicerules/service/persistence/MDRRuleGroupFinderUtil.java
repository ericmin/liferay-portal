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

package com.liferay.portlet.mobiledevicerules.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Edward C. Han
 */
public class MDRRuleGroupFinderUtil {
	public static int countByKeywords(long groupId, java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(groupId, keywords);
	}

	public static int countByG_N(long groupId, java.lang.String name,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_N(groupId, name, andOperator);
	}

	public static int countByG_N(long groupId, java.lang.String[] names,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByG_N(groupId, names, andOperator);
	}

	public static java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByKeywords(
		long groupId, java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByKeywords(groupId, keywords, start, end);
	}

	public static java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByG_N(
		long groupId, java.lang.String name, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_N(groupId, name, andOperator);
	}

	public static java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByG_N(
		long groupId, java.lang.String name, boolean andOperator, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_N(groupId, name, andOperator, start, end);
	}

	public static java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup> findByG_N(
		long groupId, java.lang.String[] names, boolean andOperator, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByG_N(groupId, names, andOperator, start, end);
	}

	public static MDRRuleGroupFinder getFinder() {
		if (_finder == null) {
			_finder = (MDRRuleGroupFinder)PortalBeanLocatorUtil.locate(MDRRuleGroupFinder.class.getName());

			ReferenceRegistry.registerReference(MDRRuleGroupFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(MDRRuleGroupFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(MDRRuleGroupFinderUtil.class,
			"_finder");
	}

	private static MDRRuleGroupFinder _finder;
}