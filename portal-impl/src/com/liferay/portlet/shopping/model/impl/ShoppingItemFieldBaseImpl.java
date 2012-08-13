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

package com.liferay.portlet.shopping.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.shopping.model.ShoppingItemField;
import com.liferay.portlet.shopping.service.ShoppingItemFieldLocalServiceUtil;

/**
 * The extended model base implementation for the ShoppingItemField service. Represents a row in the &quot;ShoppingItemField&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ShoppingItemFieldImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemFieldImpl
 * @see com.liferay.portlet.shopping.model.ShoppingItemField
 * @generated
 */
public abstract class ShoppingItemFieldBaseImpl
	extends ShoppingItemFieldModelImpl implements ShoppingItemField {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a shopping item field model instance should use the {@link ShoppingItemField} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			ShoppingItemFieldLocalServiceUtil.addShoppingItemField(this);
		}
		else {
			ShoppingItemFieldLocalServiceUtil.updateShoppingItemField(this);
		}
	}
}