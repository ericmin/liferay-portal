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

package com.liferay.portlet.mobiledevicerules.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Edward Han
 * @author Eduardo Lundgren
 */
public class MDRRuleGroupFinderImpl extends BasePersistenceImpl<MDRRuleGroup>
	implements MDRRuleGroupFinder {

	public static final String COUNT_BY_G_N =
		MDRRuleGroupFinder.class.getName() + ".countByG_N";

	public static final String FIND_BY_G_N =
		MDRRuleGroupFinder.class.getName() + ".findByG_N";

	public int countByKeywords(long groupId, String keywords)
		throws SystemException {

		String[] names = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByG_N(groupId, names, andOperator);
	}

	public int countByG_N(long groupId, String name, boolean andOperator)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);

		return countByG_N(groupId, names, andOperator);
	}

	public int countByG_N(long groupId, String[] names, boolean andOperator)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_N);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, true, names);
			sql = CustomSQLUtil.replaceAndOperator(sql, false);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(names, 2);

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

	public List<MDRRuleGroup> findByKeywords(
			long groupId, String keywords, int start, int end)
		throws SystemException {

		String[] names = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			names = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByG_N(groupId, names, andOperator, start, end);
	}

	public List<MDRRuleGroup> findByG_N(
			long groupId, String name, boolean andOperator)
		throws SystemException {

		return findByG_N(
			groupId, name, andOperator, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<MDRRuleGroup> findByG_N(
			long groupId, String name, boolean andOperator, int start, int end)
		throws SystemException {

		String[] names = CustomSQLUtil.keywords(name);

		return findByG_N(groupId, names, andOperator, start, end);
	}

	public List<MDRRuleGroup> findByG_N(
			long groupId, String[] names, boolean andOperator, int start,
			int end)
		throws SystemException {

		names = CustomSQLUtil.keywords(names);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_N);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, true, names);
			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("MDRRuleGroup", MDRRuleGroupImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(names, 2);

			return (List<MDRRuleGroup>) QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}