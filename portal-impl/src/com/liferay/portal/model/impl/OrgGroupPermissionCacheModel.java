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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.OrgGroupPermission;

import java.io.Serializable;

/**
 * The cache model class for representing OrgGroupPermission in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OrgGroupPermission
 * @generated
 */
public class OrgGroupPermissionCacheModel implements CacheModel<OrgGroupPermission>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{organizationId=");
		sb.append(organizationId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", permissionId=");
		sb.append(permissionId);
		sb.append("}");

		return sb.toString();
	}

	public OrgGroupPermission toEntityModel() {
		OrgGroupPermissionImpl orgGroupPermissionImpl = new OrgGroupPermissionImpl();

		orgGroupPermissionImpl.setOrganizationId(organizationId);
		orgGroupPermissionImpl.setGroupId(groupId);
		orgGroupPermissionImpl.setPermissionId(permissionId);

		orgGroupPermissionImpl.resetOriginalValues();

		return orgGroupPermissionImpl;
	}

	public long organizationId;
	public long groupId;
	public long permissionId;
}