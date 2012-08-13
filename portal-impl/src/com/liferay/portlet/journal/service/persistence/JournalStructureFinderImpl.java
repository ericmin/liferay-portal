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

package com.liferay.portlet.journal.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.impl.JournalStructureImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public class JournalStructureFinderImpl
	extends BasePersistenceImpl<JournalStructure>
	implements JournalStructureFinder {

	public static final String COUNT_BY_C_G_S_N_D =
		JournalStructureFinder.class.getName() + ".countByC_G_S_N_D";

	public static final String FIND_BY_C_G_S_N_D =
		JournalStructureFinder.class.getName() + ".findByC_G_S_N_D";

	public int countByKeywords(long companyId, long[] groupIds, String keywords)
		throws SystemException {

		String[] structureIds = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			structureIds = CustomSQLUtil.keywords(keywords, false);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return countByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions,
			andOperator);
	}

	public int countByC_G_S_N_D(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator)
		throws SystemException {

		String[] structureIds = CustomSQLUtil.keywords(structureId, false);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description);

		return countByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions,
			andOperator);
	}

	public int countByC_G_S_N_D(
			long companyId, long[] groupIds, String[] structureIds,
			String[] names, String[] descriptions, boolean andOperator)
		throws SystemException {

		return doCountByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			false);
	}

	public int filterCountByKeywords(
			long companyId, long[] groupIds, String keywords)
		throws SystemException {

		String[] structureIds = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			structureIds = CustomSQLUtil.keywords(keywords, false);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return filterCountByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions,
			andOperator);
	}

	public int filterCountByC_G_S_N_D(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator)
		throws SystemException {

		String[] structureIds = CustomSQLUtil.keywords(structureId, false);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description);

		return filterCountByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions,
			andOperator);
	}

	public int filterCountByC_G_S_N_D(
			long companyId, long[] groupIds, String[] structureIds,
			String[] names, String[] descriptions, boolean andOperator)
		throws SystemException {

		return doCountByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			true);
	}

	public List<JournalStructure> filterFindByKeywords(
			long companyId, long[] groupIds, String keywords, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		String[] structureIds = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			structureIds = CustomSQLUtil.keywords(keywords, false);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return filterFindByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			start, end, obc);
	}

	public List<JournalStructure> filterFindByC_G_S_N_D(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String[] structureIds = CustomSQLUtil.keywords(structureId, false);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description);

		return filterFindByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			start, end, obc);
	}

	public List<JournalStructure> filterFindByC_G_S_N_D(
			long companyId, long[] groupIds, String[] structureIds,
			String[] names, String[] descriptions, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return doFindByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			start, end, obc, true);
	}

	public List<JournalStructure> findByKeywords(
			long companyId, long[] groupIds, String keywords, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		String[] structureIds = null;
		String[] names = null;
		String[] descriptions = null;
		boolean andOperator = false;

		if (Validator.isNotNull(keywords)) {
			structureIds = CustomSQLUtil.keywords(keywords, false);
			names = CustomSQLUtil.keywords(keywords);
			descriptions = CustomSQLUtil.keywords(keywords);
		}
		else {
			andOperator = true;
		}

		return findByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			start, end, obc);
	}

	public List<JournalStructure> findByC_G_S_N_D(
			long companyId, long[] groupIds, String structureId, String name,
			String description, boolean andOperator, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		String[] structureIds = CustomSQLUtil.keywords(structureId, false);
		String[] names = CustomSQLUtil.keywords(name);
		String[] descriptions = CustomSQLUtil.keywords(description);

		return findByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			start, end, obc);
	}

	public List<JournalStructure> findByC_G_S_N_D(
			long companyId, long[] groupIds, String[] structureIds,
			String[] names, String[] descriptions, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return doFindByC_G_S_N_D(
			companyId, groupIds, structureIds, names, descriptions, andOperator,
			start, end, obc, false);
	}

	protected int doCountByC_G_S_N_D(
			long companyId, long[] groupIds, String[] structureIds,
			String[] names, String[] descriptions, boolean andOperator,
			boolean inlineSQLHelper)
		throws SystemException {

		structureIds = CustomSQLUtil.keywords(structureIds, false);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_C_G_S_N_D);

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "structureId", StringPool.LIKE, false, structureIds);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(description)", StringPool.LIKE, true, descriptions);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalStructure.class.getName(),
					"JournalStructure.id_", groupIds);

				sql = StringUtil.replace(
					sql, "(companyId", "(JournalStructure.companyId");

				sql = StringUtil.replace(
					sql, "(name", "(JournalStructure.name");
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(structureIds, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

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

	protected List<JournalStructure> doFindByC_G_S_N_D(
			long companyId, long[] groupIds, String[] structureIds,
			String[] names, String[] descriptions, boolean andOperator,
			int start, int end, OrderByComparator obc, boolean inlineSQLHelper)
		throws SystemException {

		structureIds = CustomSQLUtil.keywords(structureIds, false);
		names = CustomSQLUtil.keywords(names);
		descriptions = CustomSQLUtil.keywords(descriptions);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_S_N_D);

			sql = StringUtil.replace(
				sql, "[$GROUP_ID$]", getGroupIds(groupIds));
			sql = CustomSQLUtil.replaceKeywords(
				sql, "structureId", StringPool.LIKE, false, structureIds);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(name)", StringPool.LIKE, false, names);
			sql = CustomSQLUtil.replaceKeywords(
				sql, "lower(description)", StringPool.LIKE, true, descriptions);

			sql = CustomSQLUtil.replaceAndOperator(sql, andOperator);
			sql = CustomSQLUtil.replaceOrderBy(sql, obc);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, JournalStructure.class.getName(),
					"JournalStructure.id_", groupIds);

				sql = StringUtil.replace(
					sql, "(companyId", "(JournalStructure.companyId");

				sql = StringUtil.replace(
					sql, "(name", "(JournalStructure.name");
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("JournalStructure", JournalStructureImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupIds);
			qPos.add(structureIds, 2);
			qPos.add(names, 2);
			qPos.add(descriptions, 2);

			return (List<JournalStructure>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected String getGroupIds(long[] groupIds) {
		if (groupIds.length == 0) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(groupIds.length + 2);

		sb.append(" (groupId = ? ");

		for (int i = 1; i < groupIds.length; i++) {
			sb.append(" OR groupId = ? ");
		}

		sb.append(") AND ");

		return sb.toString();
	}

}