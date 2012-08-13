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
import com.liferay.portal.model.Role;

import java.io.Serializable;

/**
 * The cache model class for representing Role in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Role
 * @generated
 */
public class RoleCacheModel implements CacheModel<Role>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{roleId=");
		sb.append(roleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", subtype=");
		sb.append(subtype);
		sb.append("}");

		return sb.toString();
	}

	public Role toEntityModel() {
		RoleImpl roleImpl = new RoleImpl();

		roleImpl.setRoleId(roleId);
		roleImpl.setCompanyId(companyId);
		roleImpl.setClassNameId(classNameId);
		roleImpl.setClassPK(classPK);

		if (name == null) {
			roleImpl.setName(StringPool.BLANK);
		}
		else {
			roleImpl.setName(name);
		}

		if (title == null) {
			roleImpl.setTitle(StringPool.BLANK);
		}
		else {
			roleImpl.setTitle(title);
		}

		if (description == null) {
			roleImpl.setDescription(StringPool.BLANK);
		}
		else {
			roleImpl.setDescription(description);
		}

		roleImpl.setType(type);

		if (subtype == null) {
			roleImpl.setSubtype(StringPool.BLANK);
		}
		else {
			roleImpl.setSubtype(subtype);
		}

		roleImpl.resetOriginalValues();

		return roleImpl;
	}

	public long roleId;
	public long companyId;
	public long classNameId;
	public long classPK;
	public String name;
	public String title;
	public String description;
	public int type;
	public String subtype;
}