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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.shopping.model.ShoppingItemPrice;

import java.io.Serializable;

/**
 * The cache model class for representing ShoppingItemPrice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemPrice
 * @generated
 */
public class ShoppingItemPriceCacheModel implements CacheModel<ShoppingItemPrice>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{itemPriceId=");
		sb.append(itemPriceId);
		sb.append(", itemId=");
		sb.append(itemId);
		sb.append(", minQuantity=");
		sb.append(minQuantity);
		sb.append(", maxQuantity=");
		sb.append(maxQuantity);
		sb.append(", price=");
		sb.append(price);
		sb.append(", discount=");
		sb.append(discount);
		sb.append(", taxable=");
		sb.append(taxable);
		sb.append(", shipping=");
		sb.append(shipping);
		sb.append(", useShippingFormula=");
		sb.append(useShippingFormula);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public ShoppingItemPrice toEntityModel() {
		ShoppingItemPriceImpl shoppingItemPriceImpl = new ShoppingItemPriceImpl();

		shoppingItemPriceImpl.setItemPriceId(itemPriceId);
		shoppingItemPriceImpl.setItemId(itemId);
		shoppingItemPriceImpl.setMinQuantity(minQuantity);
		shoppingItemPriceImpl.setMaxQuantity(maxQuantity);
		shoppingItemPriceImpl.setPrice(price);
		shoppingItemPriceImpl.setDiscount(discount);
		shoppingItemPriceImpl.setTaxable(taxable);
		shoppingItemPriceImpl.setShipping(shipping);
		shoppingItemPriceImpl.setUseShippingFormula(useShippingFormula);
		shoppingItemPriceImpl.setStatus(status);

		shoppingItemPriceImpl.resetOriginalValues();

		return shoppingItemPriceImpl;
	}

	public long itemPriceId;
	public long itemId;
	public int minQuantity;
	public int maxQuantity;
	public double price;
	public double discount;
	public boolean taxable;
	public double shipping;
	public boolean useShippingFormula;
	public int status;
}