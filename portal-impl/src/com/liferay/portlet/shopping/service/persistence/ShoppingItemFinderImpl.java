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

package com.liferay.portlet.shopping.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.shopping.model.ShoppingItem;
import com.liferay.portlet.shopping.model.impl.ShoppingItemImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemFinderImpl
	extends BasePersistenceImpl<ShoppingItem> implements ShoppingItemFinder {

	public static final String COUNT_BY_G_C =
		ShoppingItemFinder.class.getName() + ".countByG_C";

	public int countByG_C(long groupId, List<Long> categoryIds)
		throws SystemException {

		return doCountByG_C(groupId, categoryIds, false);
	}

	public int countByFeatured(long groupId, long[] categoryIds)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler();

			query.append("SELECT COUNT(*) AS COUNT_VALUE FROM ShoppingItem ");
			query.append("WHERE ");
			query.append("ShoppingItem.groupId = ? AND (");

			if ((categoryIds != null) && (categoryIds.length > 0)) {
				query.append("(");

				for (int i = 0; i < categoryIds.length; i++) {
					query.append("ShoppingItem.categoryId = ? ");

					if ((i + 1) < categoryIds.length) {
						query.append("OR ");
					}
				}

				query.append(") AND ");
			}

			query.append("ShoppingItem.featured = ? AND ");
			query.append("ShoppingItem.smallImage = ?");

			SQLQuery q = session.createSQLQuery(query.toString());

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (long categoryId : categoryIds) {
				qPos.add(categoryId);
			}

			qPos.add(true);
			qPos.add(true);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countByKeywords(
			long groupId, long[] categoryIds, String keywords)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler();

			query.append("SELECT COUNT(*) AS COUNT_VALUE FROM ShoppingItem ");
			query.append("WHERE ");
			query.append("ShoppingItem.groupId = ? AND (");

			if ((categoryIds != null) && (categoryIds.length > 0)) {
				query.append("(");

				for (int i = 0; i < categoryIds.length; i++) {
					query.append("ShoppingItem.categoryId = ? ");

					if ((i + 1) < categoryIds.length) {
						query.append("OR ");
					}
				}

				query.append(") AND ");
			}

			query.append("(ShoppingItem.name LIKE ? OR ");
			query.append("ShoppingItem.description LIKE ? OR ");
			query.append("ShoppingItem.properties LIKE ?))");

			keywords = '%' + keywords + '%';

			SQLQuery q = session.createSQLQuery(query.toString());

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (long categoryId : categoryIds) {
				qPos.add(categoryId);
			}

			qPos.add(keywords);
			qPos.add(keywords);
			qPos.add(keywords);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int countBySale(long groupId, long[] categoryIds)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler();

			query.append("SELECT COUNT(*) AS COUNT_VALUE FROM ShoppingItem ");
			query.append("WHERE ");
			query.append("ShoppingItem.groupId = ? AND (");

			if ((categoryIds != null) && (categoryIds.length > 0)) {
				query.append("(");

				for (int i = 0; i < categoryIds.length; i++) {
					query.append("ShoppingItem.categoryId = ? ");

					if ((i + 1) < categoryIds.length) {
						query.append("OR ");
					}
				}

				query.append(") AND ");
			}

			query.append("ShoppingItem.sale = ? AND ");
			query.append("ShoppingItem.smallImage = ?");

			SQLQuery q = session.createSQLQuery(query.toString());

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (long categoryId : categoryIds) {
				qPos.add(categoryId);
			}

			qPos.add(true);
			qPos.add(true);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public int filterCountByG_C(long groupId, List<Long> categoryIds)
		throws SystemException {

		return doCountByG_C(groupId, categoryIds, true);
	}

	public List<ShoppingItem> findByFeatured(
			long groupId, long[] categoryIds, int numOfItems)
		throws SystemException {

		int countByFeatured = countByFeatured(groupId, categoryIds);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler();

			query.append("SELECT {ShoppingItem.*} FROM ShoppingItem ");
			query.append("WHERE ");
			query.append("ShoppingItem.groupId = ? AND (");

			if ((categoryIds != null) && (categoryIds.length > 0)) {
				query.append("(");

				for (int i = 0; i < categoryIds.length; i++) {
					query.append("ShoppingItem.categoryId = ? ");

					if ((i + 1) < categoryIds.length) {
						query.append("OR ");
					}
				}

				query.append(") AND ");
			}

			query.append("ShoppingItem.featured = ? AND ");
			query.append("ShoppingItem.smallImage = ?");

			SQLQuery q = session.createSQLQuery(query.toString());

			q.addEntity("ShoppingItem", ShoppingItemImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (long categoryId : categoryIds) {
				qPos.add(categoryId);
			}

			qPos.add(true);
			qPos.add(true);

			return (List<ShoppingItem>)QueryUtil.randomList(
				q, getDialect(), countByFeatured, numOfItems);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ShoppingItem> findByKeywords(
			long groupId, long[] categoryIds, String keywords, int start,
			int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler();

			query.append("SELECT {ShoppingItem.*} FROM ShoppingItem ");
			query.append("WHERE ");
			query.append("ShoppingItem.groupId = ? AND (");

			if ((categoryIds != null) && (categoryIds.length > 0)) {
				query.append("(");

				for (int i = 0; i < categoryIds.length; i++) {
					query.append("ShoppingItem.categoryId = ? ");

					if ((i + 1) < categoryIds.length) {
						query.append("OR ");
					}
				}

				query.append(") AND ");
			}

			query.append("(ShoppingItem.name LIKE ? OR ");
			query.append("ShoppingItem.description LIKE ? OR ");
			query.append("ShoppingItem.properties LIKE ?))");

			keywords = '%' + keywords + '%';

			SQLQuery q = session.createSQLQuery(query.toString());

			q.addEntity("ShoppingItem", ShoppingItemImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (long categoryId : categoryIds) {
				qPos.add(categoryId);
			}

			qPos.add(keywords);
			qPos.add(keywords);
			qPos.add(keywords);

			return (List<ShoppingItem>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<ShoppingItem> findBySale(
			long groupId, long[] categoryIds, int numOfItems)
		throws SystemException {

		int countBySale = countBySale(groupId, categoryIds);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = new StringBundler();

			query.append("SELECT {ShoppingItem.*} FROM ShoppingItem ");
			query.append("WHERE ");
			query.append("ShoppingItem.groupId = ? AND (");

			if ((categoryIds != null) && (categoryIds.length > 0)) {
				query.append("(");

				for (int i = 0; i < categoryIds.length; i++) {
					query.append("ShoppingItem.categoryId = ? ");

					if ((i + 1) < categoryIds.length) {
						query.append("OR ");
					}
				}

				query.append(") AND ");
			}

			query.append("ShoppingItem.sale = ? AND ");
			query.append("ShoppingItem.smallImage = ?");

			SQLQuery q = session.createSQLQuery(query.toString());

			q.addEntity("ShoppingItem", ShoppingItemImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (long categoryId : categoryIds) {
				qPos.add(categoryId);
			}

			qPos.add(true);
			qPos.add(true);

			return (List<ShoppingItem>)QueryUtil.randomList(
				q, getDialect(), countBySale, numOfItems);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected int doCountByG_C(
			long groupId, List<Long> categoryIds, boolean inlineSQLHelper)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_C);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, ShoppingItem.class.getName(), "ShoppingItem.itemId",
					groupId);
			}

			sql = StringUtil.replace(
				sql, "[$CATEGORY_ID$]", getCategoryIds(categoryIds));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			for (int i = 0; i < categoryIds.size(); i++) {
				Long categoryId = categoryIds.get(i);

				qPos.add(categoryId);
			}

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getCategoryIds(List<Long> categoryIds) {
		if (categoryIds.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(categoryIds.size() * 2 - 1);

		for (int i = 0; i < categoryIds.size(); i++) {
			sb.append("categoryId = ? ");

			if ((i + 1) != categoryIds.size()) {
				sb.append("OR ");
			}
		}

		return sb.toString();
	}

}