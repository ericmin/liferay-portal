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
import com.liferay.portal.model.UserGroup;

import java.io.Serializable;

/**
 * The cache model class for representing UserGroup in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroup
 * @generated
 */
public class UserGroupCacheModel implements CacheModel<UserGroup>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{userGroupId=");
		sb.append(userGroupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", parentUserGroupId=");
		sb.append(parentUserGroupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", addedByLDAPImport=");
		sb.append(addedByLDAPImport);
		sb.append("}");

		return sb.toString();
	}

	public UserGroup toEntityModel() {
		UserGroupImpl userGroupImpl = new UserGroupImpl();

		userGroupImpl.setUserGroupId(userGroupId);
		userGroupImpl.setCompanyId(companyId);
		userGroupImpl.setParentUserGroupId(parentUserGroupId);

		if (name == null) {
			userGroupImpl.setName(StringPool.BLANK);
		}
		else {
			userGroupImpl.setName(name);
		}

		if (description == null) {
			userGroupImpl.setDescription(StringPool.BLANK);
		}
		else {
			userGroupImpl.setDescription(description);
		}

		userGroupImpl.setAddedByLDAPImport(addedByLDAPImport);

		userGroupImpl.resetOriginalValues();

		return userGroupImpl;
	}

	public long userGroupId;
	public long companyId;
	public long parentUserGroupId;
	public String name;
	public String description;
	public boolean addedByLDAPImport;
}