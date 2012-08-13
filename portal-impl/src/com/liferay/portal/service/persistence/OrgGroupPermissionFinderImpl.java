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
import com.liferay.portal.model.OrgGroupPermission;
import com.liferay.portal.model.impl.OrgGroupPermissionImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;

/**
 * @author Brian Wing Shun Chan
 */
public class OrgGroupPermissionFinderImpl
	extends BasePersistenceImpl<OrgGroupPermission>
	implements OrgGroupPermissionFinder {

	public static final String FIND_BY_O_G_R =
		OrgGroupPermissionFinder.class.getName() + ".findByO_G_R";

	public void removeByO_G_R(
			long organizationId, long groupId, long resourceId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_O_G_R);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("OrgGroupPermission", OrgGroupPermissionImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(resourceId);
			qPos.add(organizationId);
			qPos.add(groupId);

			Iterator<OrgGroupPermission> itr = q.iterate();

			while (itr.hasNext()) {
				OrgGroupPermission orgGroupPermission = itr.next();

				OrgGroupPermissionUtil.remove(
					orgGroupPermission.getPrimaryKey());
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}