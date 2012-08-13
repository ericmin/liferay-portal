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

package com.liferay.portlet.dynamicdatamapping.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class DDMTemplateFinderUtil {
	public static int countByKeywords(long companyId, long groupId,
		long structureId, java.lang.String keywords, java.lang.String type,
		java.lang.String mode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByKeywords(companyId, groupId, structureId, keywords,
			type, mode);
	}

	public static int countByC_G_S_N_D_T_M_L(long companyId, long groupId,
		long structureId, java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_S_N_D_T_M_L(companyId, groupId, structureId,
			name, description, type, mode, language, andOperator);
	}

	public static int countByC_G_S_N_D_T_M_L(long companyId, long groupId,
		long structureId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.String[] types,
		java.lang.String[] modes, java.lang.String[] languages,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByC_G_S_N_D_T_M_L(companyId, groupId, structureId,
			names, descriptions, types, modes, languages, andOperator);
	}

	public static int filterCountByKeywords(long companyId, long groupId,
		long structureId, java.lang.String keywords, java.lang.String type,
		java.lang.String mode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByKeywords(companyId, groupId, structureId,
			keywords, type, mode);
	}

	public static int filterCountByC_G_S_N_D_T_M_L(long companyId,
		long groupId, long structureId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String mode, java.lang.String language, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_S_N_D_T_M_L(companyId, groupId,
			structureId, name, description, type, mode, language, andOperator);
	}

	public static int filterCountByC_G_S_N_D_T_M_L(long companyId,
		long groupId, long structureId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.String[] types,
		java.lang.String[] modes, java.lang.String[] languages,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterCountByC_G_S_N_D_T_M_L(companyId, groupId,
			structureId, names, descriptions, types, modes, languages,
			andOperator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> filterFindByKeywords(
		long companyId, long groupId, long structureId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByKeywords(companyId, groupId, structureId,
			keywords, type, mode, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> filterFindByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String mode, java.lang.String language, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_S_N_D_T_M_L(companyId, groupId,
			structureId, name, description, type, mode, language, andOperator,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> filterFindByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId,
		java.lang.String[] names, java.lang.String[] descriptions,
		java.lang.String[] types, java.lang.String[] modes,
		java.lang.String[] languages, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .filterFindByC_G_S_N_D_T_M_L(companyId, groupId,
			structureId, names, descriptions, types, modes, languages,
			andOperator, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> findByKeywords(
		long companyId, long groupId, long structureId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupId, structureId, keywords,
			type, mode, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> findByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String mode, java.lang.String language, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_S_N_D_T_M_L(companyId, groupId, structureId,
			name, description, type, mode, language, andOperator, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> findByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId,
		java.lang.String[] names, java.lang.String[] descriptions,
		java.lang.String[] types, java.lang.String[] modes,
		java.lang.String[] languages, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByC_G_S_N_D_T_M_L(companyId, groupId, structureId,
			names, descriptions, types, modes, languages, andOperator, start,
			end, orderByComparator);
	}

	public static DDMTemplateFinder getFinder() {
		if (_finder == null) {
			_finder = (DDMTemplateFinder)PortalBeanLocatorUtil.locate(DDMTemplateFinder.class.getName());

			ReferenceRegistry.registerReference(DDMTemplateFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(DDMTemplateFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(DDMTemplateFinderUtil.class,
			"_finder");
	}

	private static DDMTemplateFinder _finder;
}