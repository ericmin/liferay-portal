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

package com.liferay.portlet.journal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.service.base.JournalStructureServiceBaseImpl;
import com.liferay.portlet.journal.service.permission.JournalPermission;
import com.liferay.portlet.journal.service.permission.JournalStructurePermission;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalStructureServiceImpl
	extends JournalStructureServiceBaseImpl {

	public JournalStructure addStructure(
			long groupId, String structureId, boolean autoStructureId,
			String parentStructureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String xsd,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_STRUCTURE);

		return journalStructureLocalService.addStructure(
			getUserId(), groupId, structureId, autoStructureId,
			parentStructureId, nameMap, descriptionMap, xsd, serviceContext);
	}

	public JournalStructure copyStructure(
			long groupId, String oldStructureId, String newStructureId,
			boolean autoStructureId)
		throws PortalException, SystemException {

		JournalPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_STRUCTURE);

		return journalStructureLocalService.copyStructure(
			getUserId(), groupId, oldStructureId, newStructureId,
			autoStructureId);
	}

	public void deleteStructure(long groupId, String structureId)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.DELETE);

		journalStructureLocalService.deleteStructure(groupId, structureId);
	}

	public JournalStructure getStructure(long groupId, String structureId)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.VIEW);

		return journalStructureLocalService.getStructure(groupId, structureId);
	}

	public List<JournalStructure> getStructures(long groupId)
		throws SystemException {

		return journalStructurePersistence.filterFindByGroupId(groupId);
	}

	public List<JournalStructure> search(
			long companyId, long[] groupIds, String keywords, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		return journalStructureFinder.filterFindByKeywords(
			companyId, groupIds, keywords, start, end, obc);
	}

	public List<JournalStructure> search(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return journalStructureFinder.filterFindByC_G_S_N_D(
			companyId, groupIds, structureId, name, description, andOperator,
			start, end, obc);
	}

	public int searchCount(long companyId, long[] groupIds, String keywords)
		throws SystemException {

		return journalStructureFinder.filterCountByKeywords(
			companyId, groupIds, keywords);
	}

	public int searchCount(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator)
		throws SystemException {

		return journalStructureFinder.filterCountByC_G_S_N_D(
			companyId, groupIds, structureId, name, description, andOperator);
	}

	public JournalStructure updateStructure(
			long groupId, String structureId, String parentStructureId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			String xsd, ServiceContext serviceContext)
		throws PortalException, SystemException {

		JournalStructurePermission.check(
			getPermissionChecker(), groupId, structureId, ActionKeys.UPDATE);

		return journalStructureLocalService.updateStructure(
			groupId, structureId, parentStructureId, nameMap, descriptionMap,
			xsd, serviceContext);
	}

}