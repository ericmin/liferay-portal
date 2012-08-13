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
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.impl.PortletPreferencesImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class PortletPreferencesFinderImpl
	extends BasePersistenceImpl<PortletPreferences>
	implements PortletPreferencesFinder {

	public static final String FIND_BY_PORTLETID =
		PortletPreferencesFinder.class.getName() + ".findByPortletId";

	public static final String FIND_BY_C_G_O_O_P_P =
		PortletPreferencesFinder.class.getName() + ".findByC_G_O_O_P_P";

	public List<PortletPreferences> findByPortletId(String portletId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_PORTLETID);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("PortletPreferences", PortletPreferencesImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(portletId);

			return q.list(true);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<PortletPreferences> findByC_G_O_O_P_P(
			long companyId, long groupId, long ownerId, int ownerType,
			String portletId, boolean privateLayout)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_O_O_P_P);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("PortletPreferences", PortletPreferencesImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(ownerId);
			qPos.add(ownerType);
			qPos.add(portletId);
			qPos.add(portletId.concat("_INSTANCE_%"));
			qPos.add(privateLayout);

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