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

package com.liferay.portlet.messageboards.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.service.MBBanLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 */
public class MBCategoryPermission {

	public static void check(
			PermissionChecker permissionChecker, long groupId, long categoryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, categoryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long categoryId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, categoryId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, MBCategory category,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, category, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, long categoryId,
			String actionId)
		throws PortalException, SystemException {

		if ((categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ||
			(categoryId == MBCategoryConstants.DISCUSSION_CATEGORY_ID)) {

			return MBPermission.contains(permissionChecker, groupId, actionId);
		}
		else {
			MBCategory category = MBCategoryLocalServiceUtil.getCategory(
				categoryId);

			return contains(permissionChecker, category, actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long categoryId,
			String actionId)
		throws PortalException, SystemException {

		MBCategory category = MBCategoryLocalServiceUtil.getCategory(
			categoryId);

		return contains(permissionChecker, category, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, MBCategory category,
			String actionId)
		throws PortalException, SystemException {

		if (actionId.equals(ActionKeys.ADD_CATEGORY)) {
			actionId = ActionKeys.ADD_SUBCATEGORY;
		}

		if (MBBanLocalServiceUtil.hasBan(
				category.getGroupId(), permissionChecker.getUserId())) {

			return false;
		}

		long categoryId = category.getCategoryId();

		if (actionId.equals(ActionKeys.VIEW)) {
			while (categoryId !=
					MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

				category = MBCategoryLocalServiceUtil.getCategory(categoryId);

				categoryId = category.getParentCategoryId();

				if (!permissionChecker.hasOwnerPermission(
						category.getCompanyId(), MBCategory.class.getName(),
						category.getCategoryId(), category.getUserId(),
						actionId) &&
					!permissionChecker.hasPermission(
						category.getGroupId(), MBCategory.class.getName(),
						category.getCategoryId(), actionId)) {

					return false;
				}

				if (!PropsValues.PERMISSIONS_VIEW_DYNAMIC_INHERITANCE) {
					break;
				}
			}

			return true;
		}
		else {
			while (categoryId !=
					MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

				category = MBCategoryLocalServiceUtil.getCategory(categoryId);

				categoryId = category.getParentCategoryId();

				if (permissionChecker.hasOwnerPermission(
						category.getCompanyId(), MBCategory.class.getName(),
						category.getCategoryId(), category.getUserId(),
						actionId)) {

					return true;
				}

				if (permissionChecker.hasPermission(
						category.getGroupId(), MBCategory.class.getName(),
						category.getCategoryId(), actionId)) {

					return true;
				}

				if (actionId.equals(ActionKeys.VIEW)) {
					break;
				}
			}

			return false;
		}
	}

}