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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Resource;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class ResourceFinderImpl
	extends BasePersistenceImpl<Resource> implements ResourceFinder {

	public static final String FIND_BY_CONTAINER_RESOURCE =
		ResourceFinder.class.getName() + ".findByContainerResource";

	public static final String FIND_BY_NAME =
		ResourceFinder.class.getName() + ".findByName";

	public static final String FIND_BY_NO_ACTIONS =
		ResourceFinder.class.getName() + ".findByNoActions";

	public static final String FIND_BY_C_P =
		ResourceFinder.class.getName() + ".findByC_P";

	public static final String FIND_BY_N_S =
		ResourceFinder.class.getName() + ".findByN_S";

	public List<Resource> findByContainerResource(long codeId, long classNameId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_CONTAINER_RESOURCE);

			if (classNameId != 0) {
				sql = sql.concat(" WHERE Group_.classNameId = " + classNameId);
			}

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Resource_", ResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(codeId);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Resource> findByName(String name) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NAME);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Resource_", ResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(name);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Resource> findByNoActions(long codeId, String actionId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_NO_ACTIONS);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Resource_", ResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(actionId);
			qPos.add(codeId);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Resource> findByC_P(long companyId, String primKey)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_P);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Resource_", ResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(primKey);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Resource> findByN_S(String name, int scope)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_N_S);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Resource_", ResourceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(name);
			qPos.add(scope);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}