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
 * The extended model interface for the Permission service. Represents a row in the &quot;Permission_&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PermissionModel
 * @see com.liferay.portal.model.impl.PermissionImpl
 * @see com.liferay.portal.model.impl.PermissionModelImpl
 * @generated
 */
public interface Permission extends PermissionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.model.impl.PermissionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Permission, Long> PERMISSION_ID_ACCESSOR = new Accessor<Permission, Long>() {
			public Long get(Permission permission) {
				return permission.getPermissionId();
			}
		};

	public java.lang.String getName();

	public java.lang.String getPrimKey();

	public int getScope();

	public void setName(java.lang.String name);

	public void setPrimKey(java.lang.String primKey);

	public void setScope(int scope);
}