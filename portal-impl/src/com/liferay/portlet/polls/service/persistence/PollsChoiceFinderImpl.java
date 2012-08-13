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

package com.liferay.portlet.polls.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.polls.NoSuchChoiceException;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.impl.PollsChoiceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class PollsChoiceFinderImpl
	extends BasePersistenceImpl<PollsChoice> implements PollsChoiceFinder {

	public static final String FIND_BY_UUID_G =
		PollsChoiceFinder.class.getName() + ".findByUUID_G";

	public PollsChoice fetchByUUID_G(String uuid, long groupId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_UUID_G);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("PollsChoice", PollsChoiceImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(uuid);
			qPos.add(groupId);

			List<PollsChoice> choices = q.list();

			if (!choices.isEmpty()) {
				return choices.get(0);
			}

			return null;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public PollsChoice findByUUID_G(String uuid, long groupId)
		throws NoSuchChoiceException, SystemException {

		PollsChoice choice = fetchByUUID_G(uuid, groupId);

		if (choice != null) {
			return choice;
		}

		StringBundler sb = new StringBundler(5);

		sb.append("No PollsChoice exists with the key {uuid=");
		sb.append(uuid);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append("}");

		throw new NoSuchChoiceException(sb.toString());
	}

}