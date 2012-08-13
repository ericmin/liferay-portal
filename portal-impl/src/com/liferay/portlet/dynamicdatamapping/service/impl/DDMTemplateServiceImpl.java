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

package com.liferay.portlet.dynamicdatamapping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMTemplateServiceBaseImpl;
import com.liferay.portlet.dynamicdatamapping.service.permission.DDMPermission;
import com.liferay.portlet.dynamicdatamapping.service.permission.DDMTemplatePermission;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class DDMTemplateServiceImpl extends DDMTemplateServiceBaseImpl {

	public DDMTemplate addTemplate(
			long groupId, long structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String ddmResource = ParamUtil.getString(serviceContext, "ddmResource");

		DDMPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ddmResource, ActionKeys.ADD_TEMPLATE);

		return ddmTemplateLocalService.addTemplate(
			getUserId(), groupId, structureId, nameMap, descriptionMap, type,
			mode, language, script, serviceContext);
	}

	public List<DDMTemplate> copyTemplates(
			long structureId, long newStructureId, String type,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String ddmResource = ParamUtil.getString(serviceContext, "ddmResource");

		DDMPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ddmResource, ActionKeys.ADD_TEMPLATE);

		return ddmTemplateLocalService.copyTemplates(
			getUserId(), structureId, newStructureId, type, serviceContext);
	}

	public void deleteTemplate(long templateId)
		throws PortalException, SystemException {

		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.DELETE);

		ddmTemplateLocalService.deleteTemplate(templateId);
	}

	public DDMTemplate getTemplate(long templateId)
		throws PortalException, SystemException {

		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.VIEW);

		return ddmTemplateLocalService.getTemplate(templateId);
	}

	public List<DDMTemplate> getTemplates(
			long structureId, String type, String mode)
		throws SystemException {

		return ddmTemplatePersistence.findByS_T_M(structureId, type, mode);
	}

	public List<DDMTemplate> search(
			long companyId, long groupId, long structureId, String keywords,
			String type, String mode, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.filterFindByKeywords(
			companyId, groupId, structureId, keywords, type, mode, start, end,
			orderByComparator);
	}

	public List<DDMTemplate> search(
			long companyId, long groupId, long structureId, String name,
			String description, String type, String mode, String language,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmTemplateFinder.filterFindByC_G_S_N_D_T_M_L(
			companyId, groupId, structureId, name, description, type, mode,
			language, andOperator, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long groupId, long structureId, String keywords,
			String type, String mode)
		throws SystemException {

		return ddmTemplateFinder.filterCountByKeywords(
			companyId, groupId, structureId, keywords, type, mode);
	}

	public int searchCount(
			long companyId, long groupId, long structureId, String name,
			String description, String type, String mode, String language,
			boolean andOperator)
		throws SystemException {

		return ddmTemplateFinder.filterCountByC_G_S_N_D_T_M_L(
			companyId, groupId, structureId, name, description, type, mode,
			language, andOperator);
	}

	public DDMTemplate updateTemplate(
			long templateId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type, String mode,
			String language, String script, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMTemplatePermission.check(
			getPermissionChecker(), templateId, ActionKeys.UPDATE);

		return ddmTemplateLocalService.updateTemplate(
			templateId, nameMap, descriptionMap, type, mode, language, script,
			serviceContext);
	}

}