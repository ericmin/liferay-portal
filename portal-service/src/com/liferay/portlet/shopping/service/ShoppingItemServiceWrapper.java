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

package com.liferay.portlet.shopping.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ShoppingItemService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ShoppingItemService
 * @generated
 */
public class ShoppingItemServiceWrapper implements ShoppingItemService,
	ServiceWrapper<ShoppingItemService> {
	public ShoppingItemServiceWrapper(ShoppingItemService shoppingItemService) {
		_shoppingItemService = shoppingItemService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _shoppingItemService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_shoppingItemService.setBeanIdentifier(beanIdentifier);
	}

	public void addBookItems(long groupId, long categoryId,
		java.lang.String[] isbns)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_shoppingItemService.addBookItems(groupId, categoryId, isbns);
	}

	public com.liferay.portlet.shopping.model.ShoppingItem addItem(
		long groupId, long categoryId, java.lang.String sku,
		java.lang.String name, java.lang.String description,
		java.lang.String properties, java.lang.String fieldsQuantities,
		boolean requiresShipping, int stockQuantity, boolean featured,
		java.lang.Boolean sale, boolean smallImage,
		java.lang.String smallImageURL, java.io.File smallFile,
		boolean mediumImage, java.lang.String mediumImageURL,
		java.io.File mediumFile, boolean largeImage,
		java.lang.String largeImageURL, java.io.File largeFile,
		java.util.List<com.liferay.portlet.shopping.model.ShoppingItemField> itemFields,
		java.util.List<com.liferay.portlet.shopping.model.ShoppingItemPrice> itemPrices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.addItem(groupId, categoryId, sku, name,
			description, properties, fieldsQuantities, requiresShipping,
			stockQuantity, featured, sale, smallImage, smallImageURL,
			smallFile, mediumImage, mediumImageURL, mediumFile, largeImage,
			largeImageURL, largeFile, itemFields, itemPrices, serviceContext);
	}

	public void deleteItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_shoppingItemService.deleteItem(itemId);
	}

	public int getCategoriesItemsCount(long groupId,
		java.util.List<java.lang.Long> categoryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.getCategoriesItemsCount(groupId, categoryIds);
	}

	public com.liferay.portlet.shopping.model.ShoppingItem getItem(long itemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.getItem(itemId);
	}

	public java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> getItems(
		long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.getItems(groupId, categoryId);
	}

	public java.util.List<com.liferay.portlet.shopping.model.ShoppingItem> getItems(
		long groupId, long categoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.getItems(groupId, categoryId, start, end,
			obc);
	}

	public int getItemsCount(long groupId, long categoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.getItemsCount(groupId, categoryId);
	}

	public com.liferay.portlet.shopping.model.ShoppingItem[] getItemsPrevAndNext(
		long itemId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.getItemsPrevAndNext(itemId, obc);
	}

	public com.liferay.portlet.shopping.model.ShoppingItem updateItem(
		long itemId, long groupId, long categoryId, java.lang.String sku,
		java.lang.String name, java.lang.String description,
		java.lang.String properties, java.lang.String fieldsQuantities,
		boolean requiresShipping, int stockQuantity, boolean featured,
		java.lang.Boolean sale, boolean smallImage,
		java.lang.String smallImageURL, java.io.File smallFile,
		boolean mediumImage, java.lang.String mediumImageURL,
		java.io.File mediumFile, boolean largeImage,
		java.lang.String largeImageURL, java.io.File largeFile,
		java.util.List<com.liferay.portlet.shopping.model.ShoppingItemField> itemFields,
		java.util.List<com.liferay.portlet.shopping.model.ShoppingItemPrice> itemPrices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _shoppingItemService.updateItem(itemId, groupId, categoryId,
			sku, name, description, properties, fieldsQuantities,
			requiresShipping, stockQuantity, featured, sale, smallImage,
			smallImageURL, smallFile, mediumImage, mediumImageURL, mediumFile,
			largeImage, largeImageURL, largeFile, itemFields, itemPrices,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ShoppingItemService getWrappedShoppingItemService() {
		return _shoppingItemService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedShoppingItemService(
		ShoppingItemService shoppingItemService) {
		_shoppingItemService = shoppingItemService;
	}

	public ShoppingItemService getWrappedService() {
		return _shoppingItemService;
	}

	public void setWrappedService(ShoppingItemService shoppingItemService) {
		_shoppingItemService = shoppingItemService;
	}

	private ShoppingItemService _shoppingItemService;
}