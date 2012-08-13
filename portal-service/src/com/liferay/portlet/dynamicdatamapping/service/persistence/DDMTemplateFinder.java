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

/**
 * @author Brian Wing Shun Chan
 */
public interface DDMTemplateFinder {
	public int countByKeywords(long companyId, long groupId, long structureId,
		java.lang.String keywords, java.lang.String type, java.lang.String mode)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_G_S_N_D_T_M_L(long companyId, long groupId,
		long structureId, java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByC_G_S_N_D_T_M_L(long companyId, long groupId,
		long structureId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.String[] types,
		java.lang.String[] modes, java.lang.String[] languages,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByKeywords(long companyId, long groupId,
		long structureId, java.lang.String keywords, java.lang.String type,
		java.lang.String mode)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByC_G_S_N_D_T_M_L(long companyId, long groupId,
		long structureId, java.lang.String name, java.lang.String description,
		java.lang.String type, java.lang.String mode,
		java.lang.String language, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int filterCountByC_G_S_N_D_T_M_L(long companyId, long groupId,
		long structureId, java.lang.String[] names,
		java.lang.String[] descriptions, java.lang.String[] types,
		java.lang.String[] modes, java.lang.String[] languages,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> filterFindByKeywords(
		long companyId, long groupId, long structureId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> filterFindByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String mode, java.lang.String language, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> filterFindByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId,
		java.lang.String[] names, java.lang.String[] descriptions,
		java.lang.String[] types, java.lang.String[] modes,
		java.lang.String[] languages, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> findByKeywords(
		long companyId, long groupId, long structureId,
		java.lang.String keywords, java.lang.String type,
		java.lang.String mode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> findByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId, java.lang.String name,
		java.lang.String description, java.lang.String type,
		java.lang.String mode, java.lang.String language, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portlet.dynamicdatamapping.model.DDMTemplate> findByC_G_S_N_D_T_M_L(
		long companyId, long groupId, long structureId,
		java.lang.String[] names, java.lang.String[] descriptions,
		java.lang.String[] types, java.lang.String[] modes,
		java.lang.String[] languages, boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;
}