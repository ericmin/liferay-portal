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
 * This class is a wrapper for {@link ClassName}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ClassName
 * @generated
 */
public class ClassNameWrapper implements ClassName, ModelWrapper<ClassName> {
	public ClassNameWrapper(ClassName className) {
		_className = className;
	}

	public Class<?> getModelClass() {
		return ClassName.class;
	}

	public String getModelClassName() {
		return ClassName.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("value", getValue());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}
	}

	/**
	* Returns the primary key of this class name.
	*
	* @return the primary key of this class name
	*/
	public long getPrimaryKey() {
		return _className.getPrimaryKey();
	}

	/**
	* Sets the primary key of this class name.
	*
	* @param primaryKey the primary key of this class name
	*/
	public void setPrimaryKey(long primaryKey) {
		_className.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the fully qualified class name of this class name.
	*
	* @return the fully qualified class name of this class name
	*/
	public java.lang.String getClassName() {
		return _className.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_className.setClassName(className);
	}

	/**
	* Returns the class name ID of this class name.
	*
	* @return the class name ID of this class name
	*/
	public long getClassNameId() {
		return _className.getClassNameId();
	}

	/**
	* Sets the class name ID of this class name.
	*
	* @param classNameId the class name ID of this class name
	*/
	public void setClassNameId(long classNameId) {
		_className.setClassNameId(classNameId);
	}

	/**
	* Returns the value of this class name.
	*
	* @return the value of this class name
	*/
	public java.lang.String getValue() {
		return _className.getValue();
	}

	/**
	* Sets the value of this class name.
	*
	* @param value the value of this class name
	*/
	public void setValue(java.lang.String value) {
		_className.setValue(value);
	}

	public boolean isNew() {
		return _className.isNew();
	}

	public void setNew(boolean n) {
		_className.setNew(n);
	}

	public boolean isCachedModel() {
		return _className.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_className.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _className.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _className.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_className.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _className.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_className.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ClassNameWrapper((ClassName)_className.clone());
	}

	public int compareTo(com.liferay.portal.model.ClassName className) {
		return _className.compareTo(className);
	}

	@Override
	public int hashCode() {
		return _className.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.model.ClassName> toCacheModel() {
		return _className.toCacheModel();
	}

	public com.liferay.portal.model.ClassName toEscapedModel() {
		return new ClassNameWrapper(_className.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _className.toString();
	}

	public java.lang.String toXmlString() {
		return _className.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_className.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ClassName getWrappedClassName() {
		return _className;
	}

	public ClassName getWrappedModel() {
		return _className;
	}

	public void resetOriginalValues() {
		_className.resetOriginalValues();
	}

	private ClassName _className;
}