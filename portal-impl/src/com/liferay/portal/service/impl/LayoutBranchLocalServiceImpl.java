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

package com.liferay.portal.service.impl;

import com.liferay.portal.LayoutBranchNameException;
import com.liferay.portal.NoSuchLayoutBranchException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.LayoutRevision;
import com.liferay.portal.model.LayoutRevisionConstants;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutBranchLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Julio Camarero
 */
public class LayoutBranchLocalServiceImpl
	extends LayoutBranchLocalServiceBaseImpl {

	public LayoutBranch addLayoutBranch(
			long layoutSetBranchId, long plid, String name, String description,
			boolean master, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		LayoutSetBranch layoutSetBranch =
			layoutSetBranchPersistence.findByPrimaryKey(layoutSetBranchId);

		validate(0, layoutSetBranchId, plid, name);

		long layoutBranchId = counterLocalService.increment();

		LayoutBranch layoutBranch = layoutBranchPersistence.create(
			layoutBranchId);

		layoutBranch.setGroupId(layoutSetBranch.getGroupId());
		layoutBranch.setCompanyId(user.getCompanyId());
		layoutBranch.setUserId(user.getUserId());
		layoutBranch.setUserName(user.getFullName());
		layoutBranch.setLayoutSetBranchId(layoutSetBranchId);
		layoutBranch.setPlid(plid);
		layoutBranch.setName(name);
		layoutBranch.setDescription(description);
		layoutBranch.setMaster(master);

		layoutBranchPersistence.update(layoutBranch, false);

		return layoutBranch;
	}

	public LayoutBranch addLayoutBranch(
			long layoutRevisionId, String name, String description,
			boolean master, ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutRevision layoutRevision =
			layoutRevisionPersistence.findByPrimaryKey(layoutRevisionId);

		LayoutBranch layoutBranch = addLayoutBranch(
			layoutRevision.getLayoutSetBranchId(), layoutRevision.getPlid(),
			name, description, master, serviceContext);

		layoutRevisionService.addLayoutRevision(
			layoutBranch.getUserId(), layoutRevision.getLayoutSetBranchId(),
			layoutBranch.getLayoutBranchId(),
			LayoutRevisionConstants.DEFAULT_PARENT_LAYOUT_REVISION_ID, false,
			layoutRevision.getPlid(), layoutRevision.getLayoutRevisionId(),
			layoutRevision.isPrivateLayout(), layoutRevision.getName(),
			layoutRevision.getTitle(), layoutRevision.getDescription(),
			layoutRevision.getKeywords(), layoutRevision.getRobots(),
			layoutRevision.getTypeSettings(), layoutRevision.getIconImage(),
			layoutRevision.getIconImageId(), layoutRevision.getThemeId(),
			layoutRevision.getColorSchemeId(), layoutRevision.getWapThemeId(),
			layoutRevision.getWapColorSchemeId(), layoutRevision.getCss(),
			serviceContext);

		return layoutBranch;
	}

	@Override
	public LayoutBranch deleteLayoutBranch(long layoutBranchId)
		throws PortalException, SystemException {

		LayoutBranch layoutBranch = layoutBranchPersistence.findByPrimaryKey(
			layoutBranchId);

		layoutRevisionLocalService.deleteLayoutRevisions(
			layoutBranch.getLayoutSetBranchId(), layoutBranchId,
			layoutBranch.getPlid());

		return layoutBranchLocalService.deleteLayoutBranch(layoutBranch);
	}

	public void deleteLayoutSetBranchLayoutBranches(long layoutSetBranchId)
		throws PortalException, SystemException {

		List<LayoutBranch> layoutBranches =
			layoutBranchPersistence.findByLayoutSetBranchId(layoutSetBranchId);

		for (LayoutBranch layoutBranch : layoutBranches) {
			deleteLayoutBranch(layoutBranch.getLayoutBranchId());
		}
	}

	public List<LayoutBranch> getLayoutBranches(
			long layoutSetBranchId, long plid, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return layoutBranchPersistence.findByL_P(
			layoutSetBranchId, plid, start, end, orderByComparator);
	}

	public List<LayoutBranch> getLayoutSetBranchLayoutBranches(
			long layoutSetBranchId)
		throws SystemException {

		return layoutBranchPersistence.findByLayoutSetBranchId(
			layoutSetBranchId);
	}

	public LayoutBranch getMasterLayoutBranch(long layoutSetBranchId, long plid)
		throws PortalException, SystemException {

		return layoutBranchPersistence.findByL_P_M(
			layoutSetBranchId, plid, true);
	}

	public LayoutBranch updateLayoutBranch(
			long layoutBranchId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutBranch layoutBranch = layoutBranchPersistence.findByPrimaryKey(
			layoutBranchId);

		validate(
			layoutBranch.getLayoutBranchId(),
			layoutBranch.getLayoutSetBranchId(), layoutBranch.getPlid(), name);

		layoutBranch.setName(name);
		layoutBranch.setDescription(description);

		layoutBranchPersistence.update(layoutBranch, false);

		return layoutBranch;
	}

	protected void validate(
			long layoutBranchId, long layoutSetBranchId, long plid, String name)
		throws PortalException, SystemException {

		if (Validator.isNull(name) || (name.length() < 4)) {
			throw new LayoutBranchNameException(
				LayoutBranchNameException.TOO_SHORT);
		}

		if (name.length() > 100) {
			throw new LayoutBranchNameException(
				LayoutBranchNameException.TOO_LONG);
		}

		try {
			LayoutBranch layoutBranch = layoutBranchPersistence.findByL_P_N(
				layoutSetBranchId, plid, name);

			if (layoutBranch.getLayoutBranchId() != layoutBranchId) {
				throw new LayoutBranchNameException(
					LayoutBranchNameException.DUPLICATE);
			}
		}
		catch (NoSuchLayoutBranchException nslbe) {
		}
	}

}