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
 * This class is a wrapper for {@link Shard}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Shard
 * @generated
 */
public class ShardWrapper implements Shard, ModelWrapper<Shard> {
	public ShardWrapper(Shard shard) {
		_shard = shard;
	}

	public Class<?> getModelClass() {
		return Shard.class;
	}

	public String getModelClassName() {
		return Shard.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("shardId", getShardId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("name", getName());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long shardId = (Long)attributes.get("shardId");

		if (shardId != null) {
			setShardId(shardId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this shard.
	*
	* @return the primary key of this shard
	*/
	public long getPrimaryKey() {
		return _shard.getPrimaryKey();
	}

	/**
	* Sets the primary key of this shard.
	*
	* @param primaryKey the primary key of this shard
	*/
	public void setPrimaryKey(long primaryKey) {
		_shard.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the shard ID of this shard.
	*
	* @return the shard ID of this shard
	*/
	public long getShardId() {
		return _shard.getShardId();
	}

	/**
	* Sets the shard ID of this shard.
	*
	* @param shardId the shard ID of this shard
	*/
	public void setShardId(long shardId) {
		_shard.setShardId(shardId);
	}

	/**
	* Returns the fully qualified class name of this shard.
	*
	* @return the fully qualified class name of this shard
	*/
	public java.lang.String getClassName() {
		return _shard.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_shard.setClassName(className);
	}

	/**
	* Returns the class name ID of this shard.
	*
	* @return the class name ID of this shard
	*/
	public long getClassNameId() {
		return _shard.getClassNameId();
	}

	/**
	* Sets the class name ID of this shard.
	*
	* @param classNameId the class name ID of this shard
	*/
	public void setClassNameId(long classNameId) {
		_shard.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this shard.
	*
	* @return the class p k of this shard
	*/
	public long getClassPK() {
		return _shard.getClassPK();
	}

	/**
	* Sets the class p k of this shard.
	*
	* @param classPK the class p k of this shard
	*/
	public void setClassPK(long classPK) {
		_shard.setClassPK(classPK);
	}

	/**
	* Returns the name of this shard.
	*
	* @return the name of this shard
	*/
	public java.lang.String getName() {
		return _shard.getName();
	}

	/**
	* Sets the name of this shard.
	*
	* @param name the name of this shard
	*/
	public void setName(java.lang.String name) {
		_shard.setName(name);
	}

	public boolean isNew() {
		return _shard.isNew();
	}

	public void setNew(boolean n) {
		_shard.setNew(n);
	}

	public boolean isCachedModel() {
		return _shard.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_shard.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _shard.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _shard.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_shard.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _shard.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_shard.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ShardWrapper((Shard)_shard.clone());
	}

	public int compareTo(com.liferay.portal.model.Shard shard) {
		return _shard.compareTo(shard);
	}

	@Override
	public int hashCode() {
		return _shard.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.Shard> toCacheModel() {
		return _shard.toCacheModel();
	}

	public com.liferay.portal.model.Shard toEscapedModel() {
		return new ShardWrapper(_shard.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _shard.toString();
	}

	public java.lang.String toXmlString() {
		return _shard.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_shard.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Shard getWrappedShard() {
		return _shard;
	}

	public Shard getWrappedModel() {
		return _shard;
	}

	public void resetOriginalValues() {
		_shard.resetOriginalValues();
	}

	private Shard _shard;
}