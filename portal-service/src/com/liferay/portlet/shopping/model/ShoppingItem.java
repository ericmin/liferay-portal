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

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ShoppingItem service. Represents a row in the &quot;ShoppingItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemModel
 * @see com.liferay.portlet.shopping.model.impl.ShoppingItemImpl
 * @see com.liferay.portlet.shopping.model.impl.ShoppingItemModelImpl
 * @generated
 */
public interface ShoppingItem extends ShoppingItemModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.shopping.model.impl.ShoppingItemImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public int compareTo(com.liferay.portlet.shopping.model.ShoppingItem item);

	public com.liferay.portlet.shopping.model.ShoppingCategory getCategory();

	public java.lang.String[] getFieldsQuantitiesArray();

	public java.util.List<com.liferay.portlet.shopping.model.ShoppingItemPrice> getItemPrices()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void setFieldsQuantities(java.lang.String fieldsQuantities);

	public void setFieldsQuantitiesArray(
		java.lang.String[] fieldsQuantitiesArray);
}