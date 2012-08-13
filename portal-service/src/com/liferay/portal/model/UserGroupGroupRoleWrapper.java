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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UserGroupGroupRole}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserGroupGroupRole
 * @generated
 */
public class UserGroupGroupRoleWrapper implements UserGroupGroupRole,
	ModelWrapper<UserGroupGroupRole> {
	public UserGroupGroupRoleWrapper(UserGroupGroupRole userGroupGroupRole) {
		_userGroupGroupRole = userGroupGroupRole;
	}

	public Class<?> getModelClass() {
		return UserGroupGroupRole.class;
	}

	public String getModelClassName() {
		return UserGroupGroupRole.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userGroupId", getUserGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("roleId", getRoleId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long userGroupId = (Long)attributes.get("userGroupId");

		if (userGroupId != null) {
			setUserGroupId(userGroupId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long roleId = (Long)attributes.get("roleId");

		if (roleId != null) {
			setRoleId(roleId);
		}
	}

	/**
	* Returns the primary key of this user group group role.
	*
	* @return the primary key of this user group group role
	*/
	public com.liferay.portal.service.persistence.UserGroupGroupRolePK getPrimaryKey() {
		return _userGroupGroupRole.getPrimaryKey();
	}

	/**
	* Sets the primary key of this user group group role.
	*
	* @param primaryKey the primary key of this user group group role
	*/
	public void setPrimaryKey(
		com.liferay.portal.service.persistence.UserGroupGroupRolePK primaryKey) {
		_userGroupGroupRole.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the user group ID of this user group group role.
	*
	* @return the user group ID of this user group group role
	*/
	public long getUserGroupId() {
		return _userGroupGroupRole.getUserGroupId();
	}

	/**
	* Sets the user group ID of this user group group role.
	*
	* @param userGroupId the user group ID of this user group group role
	*/
	public void setUserGroupId(long userGroupId) {
		_userGroupGroupRole.setUserGroupId(userGroupId);
	}

	/**
	* Returns the group ID of this user group group role.
	*
	* @return the group ID of this user group group role
	*/
	public long getGroupId() {
		return _userGroupGroupRole.getGroupId();
	}

	/**
	* Sets the group ID of this user group group role.
	*
	* @param groupId the group ID of this user group group role
	*/
	public void setGroupId(long groupId) {
		_userGroupGroupRole.setGroupId(groupId);
	}

	/**
	* Returns the role ID of this user group group role.
	*
	* @return the role ID of this user group group role
	*/
	public long getRoleId() {
		return _userGroupGroupRole.getRoleId();
	}

	/**
	* Sets the role ID of this user group group role.
	*
	* @param roleId the role ID of this user group group role
	*/
	public void setRoleId(long roleId) {
		_userGroupGroupRole.setRoleId(roleId);
	}

	public boolean isNew() {
		return _userGroupGroupRole.isNew();
	}

	public void setNew(boolean n) {
		_userGroupGroupRole.setNew(n);
	}

	public boolean isCachedModel() {
		return _userGroupGroupRole.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_userGroupGroupRole.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _userGroupGroupRole.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _userGroupGroupRole.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_userGroupGroupRole.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _userGroupGroupRole.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_userGroupGroupRole.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new UserGroupGroupRoleWrapper((UserGroupGroupRole)_userGroupGroupRole.clone());
	}

	public int compareTo(
		com.liferay.portal.model.UserGroupGroupRole userGroupGroupRole) {
		return _userGroupGroupRole.compareTo(userGroupGroupRole);
	}

	@Override
	public int hashCode() {
		return _userGroupGroupRole.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.UserGroupGroupRole> toCacheModel() {
		return _userGroupGroupRole.toCacheModel();
	}

	public com.liferay.portal.model.UserGroupGroupRole toEscapedModel() {
		return new UserGroupGroupRoleWrapper(_userGroupGroupRole.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _userGroupGroupRole.toString();
	}

	public java.lang.String toXmlString() {
		return _userGroupGroupRole.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_userGroupGroupRole.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public UserGroupGroupRole getWrappedUserGroupGroupRole() {
		return _userGroupGroupRole;
	}

	public UserGroupGroupRole getWrappedModel() {
		return _userGroupGroupRole;
	}

	public void resetOriginalValues() {
		_userGroupGroupRole.resetOriginalValues();
	}

	private UserGroupGroupRole _userGroupGroupRole;
}