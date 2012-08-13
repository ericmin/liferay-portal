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
import com.liferay.portal.kernel.workflow.permission.WorkflowPermissionUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalArticlePermission {

	public static void check(
			PermissionChecker permissionChecker, JournalArticle article,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, article, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, resourcePrimKey, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, String articleId,
			double version, String actionId)
		throws PortalException, SystemException {

		if (!contains(
				permissionChecker, groupId, articleId, version, actionId)) {

			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, String articleId,
			int status, String actionId)
		throws PortalException, SystemException {

		if (!contains(
				permissionChecker, groupId, articleId, status, actionId)) {

			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long groupId, String articleId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, groupId, articleId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, JournalArticle article,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, article.getGroupId(),
			JournalArticle.class.getName(), article.getResourcePrimKey(),
			PortletKeys.JOURNAL, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (article.isPending()) {
			hasPermission = WorkflowPermissionUtil.hasPermission(
				permissionChecker, article.getGroupId(),
				JournalArticle.class.getName(), article.getResourcePrimKey(),
				actionId);

			if (hasPermission != null) {
				return hasPermission.booleanValue();
			}
		}

		if (permissionChecker.hasOwnerPermission(
				article.getCompanyId(), JournalArticle.class.getName(),
				article.getResourcePrimKey(), article.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			article.getGroupId(), JournalArticle.class.getName(),
			article.getResourcePrimKey(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long resourcePrimKey,
			String actionId)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(resourcePrimKey);

		return contains(permissionChecker, article, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String articleId,
			double version, String actionId)
		throws PortalException, SystemException {

		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(
			groupId, articleId, version);

		return contains(permissionChecker, article, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String articleId,
			int status, String actionId)
		throws PortalException, SystemException {

		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(
				groupId, articleId, status);

		return contains(permissionChecker, article, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String articleId,
			String actionId)
		throws PortalException, SystemException {

		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(
			groupId, articleId);

		return contains(permissionChecker, article, actionId);
	}

}