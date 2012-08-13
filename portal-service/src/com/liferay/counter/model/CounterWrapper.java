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

package com.liferay.counter.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Counter}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Counter
 * @generated
 */
public class CounterWrapper implements Counter, ModelWrapper<Counter> {
	public CounterWrapper(Counter counter) {
		_counter = counter;
	}

	public Class<?> getModelClass() {
		return Counter.class;
	}

	public String getModelClassName() {
		return Counter.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("name", getName());
		attributes.put("currentId", getCurrentId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long currentId = (Long)attributes.get("currentId");

		if (currentId != null) {
			setCurrentId(currentId);
		}
	}

	/**
	* Returns the primary key of this counter.
	*
	* @return the primary key of this counter
	*/
	public java.lang.String getPrimaryKey() {
		return _counter.getPrimaryKey();
	}

	/**
	* Sets the primary key of this counter.
	*
	* @param primaryKey the primary key of this counter
	*/
	public void setPrimaryKey(java.lang.String primaryKey) {
		_counter.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the name of this counter.
	*
	* @return the name of this counter
	*/
	public java.lang.String getName() {
		return _counter.getName();
	}

	/**
	* Sets the name of this counter.
	*
	* @param name the name of this counter
	*/
	public void setName(java.lang.String name) {
		_counter.setName(name);
	}

	/**
	* Returns the current ID of this counter.
	*
	* @return the current ID of this counter
	*/
	public long getCurrentId() {
		return _counter.getCurrentId();
	}

	/**
	* Sets the current ID of this counter.
	*
	* @param currentId the current ID of this counter
	*/
	public void setCurrentId(long currentId) {
		_counter.setCurrentId(currentId);
	}

	public boolean isNew() {
		return _counter.isNew();
	}

	public void setNew(boolean n) {
		_counter.setNew(n);
	}

	public boolean isCachedModel() {
		return _counter.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_counter.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _counter.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _counter.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_counter.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _counter.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_counter.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CounterWrapper((Counter)_counter.clone());
	}

	public int compareTo(com.liferay.counter.model.Counter counter) {
		return _counter.compareTo(counter);
	}

	@Override
	public int hashCode() {
		return _counter.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.counter.model.Counter> toCacheModel() {
		return _counter.toCacheModel();
	}

	public com.liferay.counter.model.Counter toEscapedModel() {
		return new CounterWrapper(_counter.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _counter.toString();
	}

	public java.lang.String toXmlString() {
		return _counter.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_counter.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Counter getWrappedCounter() {
		return _counter;
	}

	public Counter getWrappedModel() {
		return _counter;
	}

	public void resetOriginalValues() {
		_counter.resetOriginalValues();
	}

	private Counter _counter;
}