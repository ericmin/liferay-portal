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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLSync;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.model.impl.DLSyncImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Michael Young
 */
public class DLSyncFinderImpl
	extends BasePersistenceImpl<DLSync> implements DLSyncFinder {

	public static final String FIND_BY_C_M_R_T =
		DLSyncFinder.class.getName() + ".findByC_M_R_T";

	public List<DLSync> filterFindByC_M_R(
			long companyId, Date modifiedDate, long repositoryId)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_M_R_T);

			sql = InlineSQLHelperUtil.replacePermissionCheck(
				sql, DLFolder.class.getName(), "DLSync.fileId", null,
				"DLSync.repositoryId", new long[] {repositoryId}, null);

			StringBundler sb = new StringBundler(3);

			sb.append(sql);
			sb.append(" UNION ALL ");

			sql = CustomSQLUtil.get(FIND_BY_C_M_R_T);

			sql = InlineSQLHelperUtil.replacePermissionCheck(
				sql, DLFileEntry.class.getName(), "DLSync.fileId", null,
				"DLSync.repositoryId", new long[] {repositoryId}, null);

			sb.append(sql);

			sql = sb.toString();

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("DLSync", DLSyncImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(modifiedDate);
			qPos.add(repositoryId);
			qPos.add(DLSyncConstants.TYPE_FOLDER);
			qPos.add(companyId);
			qPos.add(modifiedDate);
			qPos.add(repositoryId);
			qPos.add(DLSyncConstants.TYPE_FILE);

			return q.list();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}