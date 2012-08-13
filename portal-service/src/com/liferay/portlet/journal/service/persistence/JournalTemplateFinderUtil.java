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

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalTemplateFinderUtil {
	public static int countByKeywords(long companyId, long[] groupIds,
		java.lang.String keywords, java.lang.String structureId,
		java.lang.String structureIdComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupIds, keywords, structureId,
			structureIdComparator);
	}

	public static int countByC_G_T_S_N_D(long companyId, long[] groupIds,
		java.lang.String templateId, java.lang.String structureId,
		java.lang.String structureIdComparator, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_T_S_N_D(companyId, groupIds, templateId,
			structureId, structureIdComparator, name, description, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long[] groupIds,
		java.lang.String keywords, java.lang.String structureId,
		java.lang.String structureIdComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupIds, keywords,
			structureId, structureIdComparator);
	}

	public static int filterCountByC_G_T_S_N_D(long companyId, long[] groupIds,
		java.lang.String templateId, java.lang.String structureId,
		java.lang.String structureIdComparator, java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_T_S_N_D(companyId, groupIds, templateId,
			structureId, structureIdComparator, name, description, andOperator);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalTemplate> filterFindByKeywords(
		long companyId, long[] groupIds, java.lang.String keywords,
		java.lang.String structureId, java.lang.String structureIdComparator,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupIds, keywords,
			structureId, structureIdComparator, start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalTemplate> filterFindByC_G_T_S_N_D(
		long companyId, long[] groupIds, java.lang.String templateId,
		java.lang.String structureId, java.lang.String structureIdComparator,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_T_S_N_D(companyId, groupIds, templateId,
			structureId, structureIdComparator, name, description, andOperator,
			start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalTemplate> findByKeywords(
		long companyId, long[] groupIds, java.lang.String keywords,
		java.lang.String structureId, java.lang.String structureIdComparator,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupIds, keywords, structureId,
			structureIdComparator, start, end, obc);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalTemplate> findByC_G_T_S_N_D(
		long companyId, long[] groupIds, java.lang.String templateId,
		java.lang.String structureId, java.lang.String structureIdComparator,
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_T_S_N_D(companyId, groupIds, templateId,
			structureId, structureIdComparator, name, description, andOperator,
			start, end, obc);
	}

	public static JournalTemplateFinder getFinder() {
		if (_finder == null) {
			_finder = (JournalTemplateFinder)PortalBeanLocatorUtil.locate(JournalTemplateFinder.class.getName());

			ReferenceRegistry.registerReference(JournalTemplateFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(JournalTemplateFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(JournalTemplateFinderUtil.class,
			"_finder");
	}

	private static JournalTemplateFinder _finder;
}