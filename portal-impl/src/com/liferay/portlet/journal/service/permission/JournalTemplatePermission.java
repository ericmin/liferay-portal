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

package com.liferay.portlet.journal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalTemplatePermission {

	public static void check(
			PermissionChecker permissionChecker, JournalTemplate template,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, template, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long id, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, id, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId,
			String templateId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, templateId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, JournalTemplate template,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, template.getGroupId(),
			JournalTemplate.class.getName(), template.getId(),
			PortletKeys.JOURNAL, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				template.getCompanyId(), JournalTemplate.class.getName(),
				template.getId(), template.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			template.getGroupId(), JournalTemplate.class.getName(),
			template.getId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long id, String actionId)
		throws PortalException, SystemException {

		JournalTemplate template = JournalTemplateLocalServiceUtil.getTemplate(
			id);

		return contains(permissionChecker, template, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			String templateId, String actionId)
		throws PortalException, SystemException {

		JournalTemplate template = JournalTemplateLocalServiceUtil.getTemplate(
			groupId, templateId, true);

		return contains(permissionChecker, template, actionId);
	}

}