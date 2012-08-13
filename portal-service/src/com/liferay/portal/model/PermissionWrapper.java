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
 * This class is a wrapper for {@link Permission}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Permission
 * @generated
 */
public class PermissionWrapper implements Permission, ModelWrapper<Permission> {
	public PermissionWrapper(Permission permission) {
		_permission = permission;
	}

	public Class<?> getModelClass() {
		return Permission.class;
	}

	public String getModelClassName() {
		return Permission.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("permissionId", getPermissionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("actionId", getActionId());
		attributes.put("resourceId", getResourceId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long permissionId = (Long)attributes.get("permissionId");

		if (permissionId != null) {
			setPermissionId(permissionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String actionId = (String)attributes.get("actionId");

		if (actionId != null) {
			setActionId(actionId);
		}

		Long resourceId = (Long)attributes.get("resourceId");

		if (resourceId != null) {
			setResourceId(resourceId);
		}
	}

	/**
	* Returns the primary key of this permission.
	*
	* @return the primary key of this permission
	*/
	public long getPrimaryKey() {
		return _permission.getPrimaryKey();
	}

	/**
	* Sets the primary key of this permission.
	*
	* @param primaryKey the primary key of this permission
	*/
	public void setPrimaryKey(long primaryKey) {
		_permission.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the permission ID of this permission.
	*
	* @return the permission ID of this permission
	*/
	public long getPermissionId() {
		return _permission.getPermissionId();
	}

	/**
	* Sets the permission ID of this permission.
	*
	* @param permissionId the permission ID of this permission
	*/
	public void setPermissionId(long permissionId) {
		_permission.setPermissionId(permissionId);
	}

	/**
	* Returns the company ID of this permission.
	*
	* @return the company ID of this permission
	*/
	public long getCompanyId() {
		return _permission.getCompanyId();
	}

	/**
	* Sets the company ID of this permission.
	*
	* @param companyId the company ID of this permission
	*/
	public void setCompanyId(long companyId) {
		_permission.setCompanyId(companyId);
	}

	/**
	* Returns the action ID of this permission.
	*
	* @return the action ID of this permission
	*/
	public java.lang.String getActionId() {
		return _permission.getActionId();
	}

	/**
	* Sets the action ID of this permission.
	*
	* @param actionId the action ID of this permission
	*/
	public void setActionId(java.lang.String actionId) {
		_permission.setActionId(actionId);
	}

	/**
	* Returns the resource ID of this permission.
	*
	* @return the resource ID of this permission
	*/
	public long getResourceId() {
		return _permission.getResourceId();
	}

	/**
	* Sets the resource ID of this permission.
	*
	* @param resourceId the resource ID of this permission
	*/
	public void setResourceId(long resourceId) {
		_permission.setResourceId(resourceId);
	}

	public boolean isNew() {
		return _permission.isNew();
	}

	public void setNew(boolean n) {
		_permission.setNew(n);
	}

	public boolean isCachedModel() {
		return _permission.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_permission.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _permission.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _permission.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_permission.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _permission.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_permission.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PermissionWrapper((Permission)_permission.clone());
	}

	public int compareTo(com.liferay.portal.model.Permission permission) {
		return _permission.compareTo(permission);
	}

	@Override
	public int hashCode() {
		return _permission.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Permission> toCacheModel() {
		return _permission.toCacheModel();
	}

	public com.liferay.portal.model.Permission toEscapedModel() {
		return new PermissionWrapper(_permission.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _permission.toString();
	}

	public java.lang.String toXmlString() {
		return _permission.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_permission.persist();
	}

	public java.lang.String getName() {
		return _permission.getName();
	}

	public java.lang.String getPrimKey() {
		return _permission.getPrimKey();
	}

	public int getScope() {
		return _permission.getScope();
	}

	public void setName(java.lang.String name) {
		_permission.setName(name);
	}

	public void setPrimKey(java.lang.String primKey) {
		_permission.setPrimKey(primKey);
	}

	public void setScope(int scope) {
		_permission.setScope(scope);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Permission getWrappedPermission() {
		return _permission;
	}

	public Permission getWrappedModel() {
		return _permission;
	}

	public void resetOriginalValues() {
		_permission.resetOriginalValues();
	}

	private Permission _permission;
}