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
 * This class is a wrapper for {@link PortalPreferences}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortalPreferences
 * @generated
 */
public class PortalPreferencesWrapper implements PortalPreferences,
	ModelWrapper<PortalPreferences> {
	public PortalPreferencesWrapper(PortalPreferences portalPreferences) {
		_portalPreferences = portalPreferences;
	}

	public Class<?> getModelClass() {
		return PortalPreferences.class;
	}

	public String getModelClassName() {
		return PortalPreferences.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("portalPreferencesId", getPortalPreferencesId());
		attributes.put("ownerId", getOwnerId());
		attributes.put("ownerType", getOwnerType());
		attributes.put("preferences", getPreferences());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long portalPreferencesId = (Long)attributes.get("portalPreferencesId");

		if (portalPreferencesId != null) {
			setPortalPreferencesId(portalPreferencesId);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}

		Integer ownerType = (Integer)attributes.get("ownerType");

		if (ownerType != null) {
			setOwnerType(ownerType);
		}

		String preferences = (String)attributes.get("preferences");

		if (preferences != null) {
			setPreferences(preferences);
		}
	}

	/**
	* Returns the primary key of this portal preferences.
	*
	* @return the primary key of this portal preferences
	*/
	public long getPrimaryKey() {
		return _portalPreferences.getPrimaryKey();
	}

	/**
	* Sets the primary key of this portal preferences.
	*
	* @param primaryKey the primary key of this portal preferences
	*/
	public void setPrimaryKey(long primaryKey) {
		_portalPreferences.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the portal preferences ID of this portal preferences.
	*
	* @return the portal preferences ID of this portal preferences
	*/
	public long getPortalPreferencesId() {
		return _portalPreferences.getPortalPreferencesId();
	}

	/**
	* Sets the portal preferences ID of this portal preferences.
	*
	* @param portalPreferencesId the portal preferences ID of this portal preferences
	*/
	public void setPortalPreferencesId(long portalPreferencesId) {
		_portalPreferences.setPortalPreferencesId(portalPreferencesId);
	}

	/**
	* Returns the owner ID of this portal preferences.
	*
	* @return the owner ID of this portal preferences
	*/
	public long getOwnerId() {
		return _portalPreferences.getOwnerId();
	}

	/**
	* Sets the owner ID of this portal preferences.
	*
	* @param ownerId the owner ID of this portal preferences
	*/
	public void setOwnerId(long ownerId) {
		_portalPreferences.setOwnerId(ownerId);
	}

	/**
	* Returns the owner type of this portal preferences.
	*
	* @return the owner type of this portal preferences
	*/
	public int getOwnerType() {
		return _portalPreferences.getOwnerType();
	}

	/**
	* Sets the owner type of this portal preferences.
	*
	* @param ownerType the owner type of this portal preferences
	*/
	public void setOwnerType(int ownerType) {
		_portalPreferences.setOwnerType(ownerType);
	}

	/**
	* Returns the preferences of this portal preferences.
	*
	* @return the preferences of this portal preferences
	*/
	public java.lang.String getPreferences() {
		return _portalPreferences.getPreferences();
	}

	/**
	* Sets the preferences of this portal preferences.
	*
	* @param preferences the preferences of this portal preferences
	*/
	public void setPreferences(java.lang.String preferences) {
		_portalPreferences.setPreferences(preferences);
	}

	public boolean isNew() {
		return _portalPreferences.isNew();
	}

	public void setNew(boolean n) {
		_portalPreferences.setNew(n);
	}

	public boolean isCachedModel() {
		return _portalPreferences.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_portalPreferences.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _portalPreferences.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _portalPreferences.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_portalPreferences.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _portalPreferences.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_portalPreferences.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new PortalPreferencesWrapper((PortalPreferences)_portalPreferences.clone());
	}

	public int compareTo(
		com.liferay.portal.model.PortalPreferences portalPreferences) {
		return _portalPreferences.compareTo(portalPreferences);
	}

	@Override
	public int hashCode() {
		return _portalPreferences.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.PortalPreferences> toCacheModel() {
		return _portalPreferences.toCacheModel();
	}

	public com.liferay.portal.model.PortalPreferences toEscapedModel() {
		return new PortalPreferencesWrapper(_portalPreferences.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _portalPreferences.toString();
	}

	public java.lang.String toXmlString() {
		return _portalPreferences.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalPreferences.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public PortalPreferences getWrappedPortalPreferences() {
		return _portalPreferences;
	}

	public PortalPreferences getWrappedModel() {
		return _portalPreferences;
	}

	public void resetOriginalValues() {
		_portalPreferences.resetOriginalValues();
	}

	private PortalPreferences _portalPreferences;
}