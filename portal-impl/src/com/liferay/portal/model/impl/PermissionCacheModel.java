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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Permission;

import java.io.Serializable;

/**
 * The cache model class for representing Permission in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Permission
 * @generated
 */
public class PermissionCacheModel implements CacheModel<Permission>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{permissionId=");
		sb.append(permissionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", actionId=");
		sb.append(actionId);
		sb.append(", resourceId=");
		sb.append(resourceId);
		sb.append("}");

		return sb.toString();
	}

	public Permission toEntityModel() {
		PermissionImpl permissionImpl = new PermissionImpl();

		permissionImpl.setPermissionId(permissionId);
		permissionImpl.setCompanyId(companyId);

		if (actionId == null) {
			permissionImpl.setActionId(StringPool.BLANK);
		}
		else {
			permissionImpl.setActionId(actionId);
		}

		permissionImpl.setResourceId(resourceId);

		permissionImpl.resetOriginalValues();

		return permissionImpl;
	}

	public long permissionId;
	public long companyId;
	public String actionId;
	public long resourceId;
}