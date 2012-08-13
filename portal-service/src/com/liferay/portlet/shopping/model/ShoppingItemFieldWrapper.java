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

package com.liferay.portlet.shopping.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ShoppingItemField}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ShoppingItemField
 * @generated
 */
public class ShoppingItemFieldWrapper implements ShoppingItemField,
	ModelWrapper<ShoppingItemField> {
	public ShoppingItemFieldWrapper(ShoppingItemField shoppingItemField) {
		_shoppingItemField = shoppingItemField;
	}

	public Class<?> getModelClass() {
		return ShoppingItemField.class;
	}

	public String getModelClassName() {
		return ShoppingItemField.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemFieldId", getItemFieldId());
		attributes.put("itemId", getItemId());
		attributes.put("name", getName());
		attributes.put("values", getValues());
		attributes.put("description", getDescription());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long itemFieldId = (Long)attributes.get("itemFieldId");

		if (itemFieldId != null) {
			setItemFieldId(itemFieldId);
		}

		Long itemId = (Long)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String values = (String)attributes.get("values");

		if (values != null) {
			setValues(values);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	* Returns the primary key of this shopping item field.
	*
	* @return the primary key of this shopping item field
	*/
	public long getPrimaryKey() {
		return _shoppingItemField.getPrimaryKey();
	}

	/**
	* Sets the primary key of this shopping item field.
	*
	* @param primaryKey the primary key of this shopping item field
	*/
	public void setPrimaryKey(long primaryKey) {
		_shoppingItemField.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the item field ID of this shopping item field.
	*
	* @return the item field ID of this shopping item field
	*/
	public long getItemFieldId() {
		return _shoppingItemField.getItemFieldId();
	}

	/**
	* Sets the item field ID of this shopping item field.
	*
	* @param itemFieldId the item field ID of this shopping item field
	*/
	public void setItemFieldId(long itemFieldId) {
		_shoppingItemField.setItemFieldId(itemFieldId);
	}

	/**
	* Returns the item ID of this shopping item field.
	*
	* @return the item ID of this shopping item field
	*/
	public long getItemId() {
		return _shoppingItemField.getItemId();
	}

	/**
	* Sets the item ID of this shopping item field.
	*
	* @param itemId the item ID of this shopping item field
	*/
	public void setItemId(long itemId) {
		_shoppingItemField.setItemId(itemId);
	}

	/**
	* Returns the name of this shopping item field.
	*
	* @return the name of this shopping item field
	*/
	public java.lang.String getName() {
		return _shoppingItemField.getName();
	}

	/**
	* Sets the name of this shopping item field.
	*
	* @param name the name of this shopping item field
	*/
	public void setName(java.lang.String name) {
		_shoppingItemField.setName(name);
	}

	/**
	* Returns the values of this shopping item field.
	*
	* @return the values of this shopping item field
	*/
	public java.lang.String getValues() {
		return _shoppingItemField.getValues();
	}

	/**
	* Sets the values of this shopping item field.
	*
	* @param values the values of this shopping item field
	*/
	public void setValues(java.lang.String values) {
		_shoppingItemField.setValues(values);
	}

	/**
	* Returns the description of this shopping item field.
	*
	* @return the description of this shopping item field
	*/
	public java.lang.String getDescription() {
		return _shoppingItemField.getDescription();
	}

	/**
	* Sets the description of this shopping item field.
	*
	* @param description the description of this shopping item field
	*/
	public void setDescription(java.lang.String description) {
		_shoppingItemField.setDescription(description);
	}

	public boolean isNew() {
		return _shoppingItemField.isNew();
	}

	public void setNew(boolean n) {
		_shoppingItemField.setNew(n);
	}

	public boolean isCachedModel() {
		return _shoppingItemField.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_shoppingItemField.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _shoppingItemField.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _shoppingItemField.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_shoppingItemField.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _shoppingItemField.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_shoppingItemField.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ShoppingItemFieldWrapper((ShoppingItemField)_shoppingItemField.clone());
	}

	public int compareTo(
		com.liferay.portlet.shopping.model.ShoppingItemField shoppingItemField) {
		return _shoppingItemField.compareTo(shoppingItemField);
	}

	@Override
	public int hashCode() {
		return _shoppingItemField.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portlet.shopping.model.ShoppingItemField> toCacheModel() {
		return _shoppingItemField.toCacheModel();
	}

	public com.liferay.portlet.shopping.model.ShoppingItemField toEscapedModel() {
		return new ShoppingItemFieldWrapper(_shoppingItemField.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _shoppingItemField.toString();
	}

	public java.lang.String toXmlString() {
		return _shoppingItemField.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_shoppingItemField.persist();
	}

	public java.lang.String[] getValuesArray() {
		return _shoppingItemField.getValuesArray();
	}

	public void setValuesArray(java.lang.String[] valuesArray) {
		_shoppingItemField.setValuesArray(valuesArray);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ShoppingItemField getWrappedShoppingItemField() {
		return _shoppingItemField;
	}

	public ShoppingItemField getWrappedModel() {
		return _shoppingItemField;
	}

	public void resetOriginalValues() {
		_shoppingItemField.resetOriginalValues();
	}

	private ShoppingItemField _shoppingItemField;
}