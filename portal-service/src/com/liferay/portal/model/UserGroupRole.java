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

package com.liferay.portal.model;

import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the UserGroupRole service. Represents a row in the &quot;UserGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRoleModel
 * @see com.liferay.portal.model.impl.UserGroupRoleImpl
 * @see com.liferay.portal.model.impl.UserGroupRoleModelImpl
 * @generated
 */
public interface UserGroupRole extends UserGroupRoleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.UserGroupRoleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserGroupRole, Long> GROUP_ID_ACCESSOR = new Accessor<UserGroupRole, Long>() {
			public Long get(UserGroupRole userGroupRole) {
				return userGroupRole.getGroupId();
			}
		};

	public static final Accessor<UserGroupRole, Long> ROLE_ID_ACCESSOR = new Accessor<UserGroupRole, Long>() {
			public Long get(UserGroupRole userGroupRole) {
				return userGroupRole.getRoleId();
			}
		};

	public com.liferay.portal.model.Group getGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.Role getRole()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.User getUser()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}