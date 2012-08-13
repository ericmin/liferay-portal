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

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.NoSuchSubscriptionException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.impl.MBCategoryImpl;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Raymond Augé
 */
public class MBCategoryFinderImpl
	extends BasePersistenceImpl<MBCategory> implements MBCategoryFinder {

	public static final String COUNT_BY_S_G_U_P =
		MBCategoryFinder.class.getName() + ".countByS_G_U_P";

	public static final String FIND_BY_S_G_U_P =
		MBCategoryFinder.class.getName() + ".findByS_G_U_P";

	public int countByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds)
		throws SystemException {

		return doCountByS_G_U_P(groupId, userId, parentCategoryIds, false);
	}

	public int filterCountByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds)
		throws SystemException {

		return doCountByS_G_U_P(groupId, userId, parentCategoryIds, true);
	}

	public List<MBCategory> filterFindByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds, int start,
			int end)
		throws SystemException {

		return doFindByS_G_U_P(
			groupId, userId, parentCategoryIds, start, end, true);
	}

	public List<MBCategory> findByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds, int start,
		int end)
		throws SystemException {

		return doFindByS_G_U_P(
			groupId, userId, parentCategoryIds, start, end, false);
	}

	protected int doCountByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds,
			boolean inlineSQLHelper)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_S_G_U_P);

			if ((parentCategoryIds == null) ||
				(parentCategoryIds.length == 0)) {

				sql = StringUtil.replace(
					sql, "(MBCategory.parentCategoryId = ?) AND",
					StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, "MBCategory.parentCategoryId = ?",
					"MBCategory.parentCategoryId = " +
						StringUtil.merge(
							parentCategoryIds,
							" OR MBCategory.parentCategoryId = "));
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, MBCategory.class.getName(), "MBCategory.categoryId",
					groupId);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(PortalUtil.getClassNameId(MBCategory.class.getName()));
			qPos.add(groupId);
			qPos.add(userId);

			int count = 0;

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long l = itr.next();

				if (l != null) {
					count = l.intValue();
				}
			}

			try {
				Group group = GroupLocalServiceUtil.getGroup(groupId);

				SubscriptionLocalServiceUtil.getSubscription(
					group.getCompanyId(), userId, MBCategory.class.getName(),
					groupId);

				count++;
			}
			catch (NoSuchSubscriptionException nsse) {
			}

			return count;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected List<MBCategory> doFindByS_G_U_P(
			long groupId, long userId, long[] parentCategoryIds, int start,
			int end, boolean inlineSQLHelper)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_S_G_U_P);

			if ((parentCategoryIds == null) ||
				(parentCategoryIds.length == 0)) {

				sql = StringUtil.replace(
					sql, "(MBCategory.parentCategoryId = ?) AND",
					StringPool.BLANK);
			}
			else {
				sql = StringUtil.replace(
					sql, "MBCategory.parentCategoryId = ?",
					"MBCategory.parentCategoryId = " +
						StringUtil.merge(
							parentCategoryIds,
							" OR MBCategory.parentCategoryId = "));
			}

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, MBCategory.class.getName(), "MBCategory.categoryId",
					groupId);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MBCategory", MBCategoryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(PortalUtil.getClassNameId(MBCategory.class.getName()));
			qPos.add(groupId);
			qPos.add(userId);

			List<MBCategory> list = (List<MBCategory>)QueryUtil.list(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, false);

			try {
				Group group = GroupLocalServiceUtil.getGroup(groupId);

				SubscriptionLocalServiceUtil.getSubscription(
					group.getCompanyId(), userId, MBCategory.class.getName(),
					groupId);

				int threadCount =
					MBThreadLocalServiceUtil.getCategoryThreadsCount(
						groupId, MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
						WorkflowConstants.STATUS_APPROVED);
				int messageCount =
					MBMessageLocalServiceUtil.getCategoryMessagesCount(
						groupId, MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
						WorkflowConstants.STATUS_APPROVED);

				MBCategory category = new MBCategoryImpl();

				category.setCompanyId(group.getCompanyId());
				category.setName(group.getName());
				category.setDescription(group.getDescription());
				category.setThreadCount(threadCount);
				category.setMessageCount(messageCount);

				list.add(category);
			}
			catch (NoSuchSubscriptionException nsse) {
			}

			return new UnmodifiableList<MBCategory>(
				ListUtil.subList(list, start, end));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}