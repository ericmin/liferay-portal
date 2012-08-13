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

import com.liferay.portal.NoSuchLayoutSetBranchException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.impl.LayoutSetBranchImpl;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.List;

/**
 * @author Julio Camarero
 */
public class LayoutSetBranchFinderImpl
	extends BasePersistenceImpl<Layout> implements LayoutSetBranchFinder {

	public static final String FIND_BY_MASTER =
		LayoutSetBranchFinder.class.getName() + ".findByMaster";

	public LayoutSetBranch findByMaster(long groupId, boolean privateLayout)
		throws NoSuchLayoutSetBranchException, SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_MASTER);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("LayoutSetBranch", LayoutSetBranchImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(privateLayout);
			qPos.add(true);

			List<LayoutSetBranch> layoutSetBranches = q.list();

			if (!layoutSetBranches.isEmpty()) {
				return layoutSetBranches.get(0);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}

		StringBundler sb = new StringBundler(5);

		sb.append("No LayoutSetBranch exists with the key {groupId=");
		sb.append(groupId);
		sb.append(", privateLayout=");
		sb.append(privateLayout);
		sb.append(", master=true}");

		throw new NoSuchLayoutSetBranchException(sb.toString());
	}

}