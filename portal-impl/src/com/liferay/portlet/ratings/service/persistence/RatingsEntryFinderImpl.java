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

package com.liferay.portlet.ratings.service.persistence;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.model.impl.RatingsEntryImpl;
import com.liferay.portlet.ratings.model.impl.RatingsEntryModelImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class RatingsEntryFinderImpl
	extends BasePersistenceImpl<RatingsEntry> implements RatingsEntryFinder {

	public static final String FIND_BY_U_C_C =
		RatingsEntryFinder.class.getName() + ".findByU_C_C";

	public static final FinderPath FINDER_PATH_FIND_BY_U_C_C = new FinderPath(
		RatingsEntryModelImpl.ENTITY_CACHE_ENABLED,
		RatingsEntryModelImpl.FINDER_CACHE_ENABLED, RatingsEntryImpl.class,
		RatingsEntryPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
		"findByU_C_C",
		new String[] {
			Long.class.getName(), Long.class.getName(), List.class.getName()
		});

	public List<RatingsEntry> findByU_C_C(
			long userId, long classNameId, List<Long> classPKs)
		throws SystemException {

		Object[] finderArgs = new Object[] {
			userId, classNameId,
			StringUtil.merge(classPKs.toArray(new Long[classPKs.size()]))
		};

		List<RatingsEntry> list = (List<RatingsEntry>)FinderCacheUtil.getResult(
			FINDER_PATH_FIND_BY_U_C_C, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (RatingsEntry ratingsEntry : list) {
				if ((userId != ratingsEntry.getUserId()) ||
					(classNameId != ratingsEntry.getClassNameId()) ||
					!classPKs.contains(ratingsEntry.getClassPK())) {

					list = null;

					break;
				}
			}
		}

		if (list != null) {
			return list;
		}

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_U_C_C);

			sql = StringUtil.replace(
				sql, "[$CLASS_PKS$]", StringUtil.merge(classPKs));

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("RatingsEntry", RatingsEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);
			qPos.add(classNameId);

			list = q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			if (list == null) {
				FinderCacheUtil.removeResult(
					FINDER_PATH_FIND_BY_U_C_C, finderArgs);
			}
			else {
				FinderCacheUtil.putResult(
					FINDER_PATH_FIND_BY_U_C_C, finderArgs, list);
			}

			closeSession(session);
		}

		return list;
	}

}