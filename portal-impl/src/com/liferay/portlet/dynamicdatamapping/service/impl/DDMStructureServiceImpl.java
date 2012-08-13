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
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMStructureServiceBaseImpl;
import com.liferay.portlet.dynamicdatamapping.service.permission.DDMPermission;
import com.liferay.portlet.dynamicdatamapping.service.permission.DDMStructurePermission;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Bruno Basto
 * @author Marcellus Tavares
 */
public class DDMStructureServiceImpl extends DDMStructureServiceBaseImpl {

	public DDMStructure addStructure(
			long groupId, long classNameId, String structureKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsd, String storageType, int type,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String ddmResource = ParamUtil.getString(serviceContext, "ddmResource");

		DDMPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ddmResource, ActionKeys.ADD_STRUCTURE);

		return ddmStructureLocalService.addStructure(
			getUserId(), groupId, classNameId, structureKey, nameMap,
			descriptionMap, xsd, storageType, type, serviceContext);
	}

	public DDMStructure copyStructure(
			long structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String ddmResource = ParamUtil.getString(serviceContext, "ddmResource");

		DDMPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ddmResource, ActionKeys.ADD_STRUCTURE);

		return ddmStructureLocalService.copyStructure(
			getUserId(), structureId, nameMap, descriptionMap, serviceContext);
	}

	public void deleteStructure(long structureId)
		throws PortalException, SystemException {

		DDMStructurePermission.check(
			getPermissionChecker(), structureId, ActionKeys.DELETE);

		ddmStructureLocalService.deleteStructure(structureId);
	}

	public DDMStructure fetchStructure(long groupId, String structureKey)
		throws PortalException, SystemException {

		DDMStructure ddmStructure = ddmStructurePersistence.fetchByG_S(
			groupId, structureKey);

		if (ddmStructure != null) {
			DDMStructurePermission.check(
				getPermissionChecker(), ddmStructure, ActionKeys.VIEW);
		}

		return ddmStructure;
	}

	public DDMStructure getStructure(long structureId)
		throws PortalException, SystemException {

		DDMStructurePermission.check(
			getPermissionChecker(), structureId, ActionKeys.VIEW);

		return ddmStructurePersistence.findByPrimaryKey(structureId);
	}

	public List<DDMStructure> search(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmStructureFinder.filterFindByKeywords(
			companyId, groupIds, classNameIds, keywords, start, end,
			orderByComparator);
	}

	public List<DDMStructure> search(
			long companyId, long[] groupIds, long[] classNameIds, String name,
			String description, String storageType, int type,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return ddmStructureFinder.filterFindByC_G_C_N_D_S_T(
			companyId, groupIds, classNameIds, name, description, storageType,
			type, andOperator, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds,
			String keywords)
		throws SystemException {

		return ddmStructureFinder.filterCountByKeywords(
			companyId, groupIds, classNameIds, keywords);
	}

	public int searchCount(
			long companyId, long[] groupIds, long[] classNameIds, String name,
			String description, String storageType, int type,
			boolean andOperator)
		throws SystemException {

		return ddmStructureFinder.filterCountByC_G_C_N_D_S_T(
			companyId, groupIds, classNameIds, name, description, storageType,
			type, andOperator);
	}

	public DDMStructure updateStructure(
			long structureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMStructurePermission.check(
			getPermissionChecker(), structureId, ActionKeys.UPDATE);

		return ddmStructureLocalService.updateStructure(
			structureId, nameMap, descriptionMap, xsd, serviceContext);
	}

	public DDMStructure updateStructure(
			long groupId, String structureKey, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDMStructurePermission.check(
			getPermissionChecker(), groupId, structureKey, ActionKeys.UPDATE);

		return ddmStructureLocalService.updateStructure(
			groupId, structureKey, nameMap, descriptionMap, xsd,
			serviceContext);
	}

}