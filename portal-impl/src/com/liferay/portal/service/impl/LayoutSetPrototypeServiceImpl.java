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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.LayoutSetPrototypeServiceBaseImpl;
import com.liferay.portal.service.permission.LayoutSetPrototypePermissionUtil;
import com.liferay.portal.service.permission.PortalPermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class LayoutSetPrototypeServiceImpl
	extends LayoutSetPrototypeServiceBaseImpl {

	public LayoutSetPrototype addLayoutSetPrototype(
			Map<Locale, String> nameMap, String description, boolean active,
			boolean layoutsUpdateable, ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_LAYOUT_PROTOTYPE);

		User user = getUser();

		return layoutSetPrototypeLocalService.addLayoutSetPrototype(
			user.getUserId(), user.getCompanyId(), nameMap, description, active,
			layoutsUpdateable, serviceContext);
	}

	public void deleteLayoutSetPrototype(long layoutSetPrototypeId)
		throws PortalException, SystemException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.DELETE);

		layoutSetPrototypeLocalService.deleteLayoutSetPrototype(
			layoutSetPrototypeId);
	}

	public LayoutSetPrototype getLayoutSetPrototype(long layoutSetPrototypeId)
		throws PortalException, SystemException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.VIEW);

		return layoutSetPrototypeLocalService.getLayoutSetPrototype(
			layoutSetPrototypeId);
	}

	public List<LayoutSetPrototype> search(
			long companyId, Boolean active, OrderByComparator obc)
		throws PortalException, SystemException {

		List<LayoutSetPrototype> filteredLayoutSetPrototypes =
			new ArrayList<LayoutSetPrototype>();

		List<LayoutSetPrototype> layoutSetPrototypes =
			layoutSetPrototypeLocalService.search(
				companyId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			if (LayoutSetPrototypePermissionUtil.contains(
					getPermissionChecker(),
					layoutSetPrototype.getLayoutSetPrototypeId(),
					ActionKeys.VIEW)) {

				filteredLayoutSetPrototypes.add(layoutSetPrototype);
			}
		}

		return filteredLayoutSetPrototypes;
	}

	public LayoutSetPrototype updateLayoutSetPrototype(
			long layoutSetPrototypeId, Map<Locale, String> nameMap,
			String description, boolean active, boolean layoutsUpdateable,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		LayoutSetPrototypePermissionUtil.check(
			getPermissionChecker(), layoutSetPrototypeId, ActionKeys.UPDATE);

		return layoutSetPrototypeLocalService.updateLayoutSetPrototype(
			layoutSetPrototypeId, nameMap, description, active,
			layoutsUpdateable, serviceContext);
	}

	public LayoutSetPrototype updateLayoutSetPrototype(
			long layoutSetPrototypeId, String settings)
		throws PortalException, SystemException {

		return layoutSetPrototypeLocalService.updateLayoutSetPrototype(
			layoutSetPrototypeId, settings);
	}

}