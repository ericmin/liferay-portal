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
import com.liferay.portal.model.ResourcePermission;

import java.io.Serializable;

/**
 * The cache model class for representing ResourcePermission in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermission
 * @generated
 */
public class ResourcePermissionCacheModel implements CacheModel<ResourcePermission>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{resourcePermissionId=");
		sb.append(resourcePermissionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", scope=");
		sb.append(scope);
		sb.append(", primKey=");
		sb.append(primKey);
		sb.append(", roleId=");
		sb.append(roleId);
		sb.append(", ownerId=");
		sb.append(ownerId);
		sb.append(", actionIds=");
		sb.append(actionIds);
		sb.append("}");

		return sb.toString();
	}

	public ResourcePermission toEntityModel() {
		ResourcePermissionImpl resourcePermissionImpl = new ResourcePermissionImpl();

		resourcePermissionImpl.setResourcePermissionId(resourcePermissionId);
		resourcePermissionImpl.setCompanyId(companyId);

		if (name == null) {
			resourcePermissionImpl.setName(StringPool.BLANK);
		}
		else {
			resourcePermissionImpl.setName(name);
		}

		resourcePermissionImpl.setScope(scope);

		if (primKey == null) {
			resourcePermissionImpl.setPrimKey(StringPool.BLANK);
		}
		else {
			resourcePermissionImpl.setPrimKey(primKey);
		}

		resourcePermissionImpl.setRoleId(roleId);
		resourcePermissionImpl.setOwnerId(ownerId);
		resourcePermissionImpl.setActionIds(actionIds);

		resourcePermissionImpl.resetOriginalValues();

		return resourcePermissionImpl;
	}

	public long resourcePermissionId;
	public long companyId;
	public String name;
	public int scope;
	public String primKey;
	public long roleId;
	public long ownerId;
	public long actionIds;
}