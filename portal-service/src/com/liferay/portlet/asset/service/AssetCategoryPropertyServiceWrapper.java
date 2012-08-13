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

package com.liferay.portlet.asset.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetCategoryPropertyService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetCategoryPropertyService
 * @generated
 */
public class AssetCategoryPropertyServiceWrapper
	implements AssetCategoryPropertyService,
		ServiceWrapper<AssetCategoryPropertyService> {
	public AssetCategoryPropertyServiceWrapper(
		AssetCategoryPropertyService assetCategoryPropertyService) {
		_assetCategoryPropertyService = assetCategoryPropertyService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetCategoryPropertyService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetCategoryPropertyService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.asset.model.AssetCategoryProperty addCategoryProperty(
		long entryId, java.lang.String key, java.lang.String value)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetCategoryPropertyService.addCategoryProperty(entryId, key,
			value);
	}

	public void deleteCategoryProperty(long categoryPropertyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetCategoryPropertyService.deleteCategoryProperty(categoryPropertyId);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetCategoryProperty> getCategoryProperties(
		long entryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetCategoryPropertyService.getCategoryProperties(entryId);
	}

	public java.util.List<com.liferay.portlet.asset.model.AssetCategoryProperty> getCategoryPropertyValues(
		long companyId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetCategoryPropertyService.getCategoryPropertyValues(companyId,
			key);
	}

	public com.liferay.portlet.asset.model.AssetCategoryProperty updateCategoryProperty(
		long categoryPropertyId, java.lang.String key, java.lang.String value)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetCategoryPropertyService.updateCategoryProperty(categoryPropertyId,
			key, value);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetCategoryPropertyService getWrappedAssetCategoryPropertyService() {
		return _assetCategoryPropertyService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetCategoryPropertyService(
		AssetCategoryPropertyService assetCategoryPropertyService) {
		_assetCategoryPropertyService = assetCategoryPropertyService;
	}

	public AssetCategoryPropertyService getWrappedService() {
		return _assetCategoryPropertyService;
	}

	public void setWrappedService(
		AssetCategoryPropertyService assetCategoryPropertyService) {
		_assetCategoryPropertyService = assetCategoryPropertyService;
	}

	private AssetCategoryPropertyService _assetCategoryPropertyService;
}