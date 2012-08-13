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

package com.liferay.portal.service;

/**
 * <p>
 * This class is a wrapper for {@link RegionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       RegionService
 * @generated
 */
public class RegionServiceWrapper implements RegionService,
	ServiceWrapper<RegionService> {
	public RegionServiceWrapper(RegionService regionService) {
		_regionService = regionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _regionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_regionService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.Region addRegion(long countryId,
		java.lang.String regionCode, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _regionService.addRegion(countryId, regionCode, name, active);
	}

	public com.liferay.portal.model.Region fetchRegion(long countryId,
		java.lang.String regionCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _regionService.fetchRegion(countryId, regionCode);
	}

	public com.liferay.portal.model.Region getRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _regionService.getRegion(regionId);
	}

	public com.liferay.portal.model.Region getRegion(long countryId,
		java.lang.String regionCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _regionService.getRegion(countryId, regionCode);
	}

	public java.util.List<com.liferay.portal.model.Region> getRegions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _regionService.getRegions();
	}

	public java.util.List<com.liferay.portal.model.Region> getRegions(
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _regionService.getRegions(active);
	}

	public java.util.List<com.liferay.portal.model.Region> getRegions(
		long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _regionService.getRegions(countryId);
	}

	public java.util.List<com.liferay.portal.model.Region> getRegions(
		long countryId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _regionService.getRegions(countryId, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public RegionService getWrappedRegionService() {
		return _regionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedRegionService(RegionService regionService) {
		_regionService = regionService;
	}

	public RegionService getWrappedService() {
		return _regionService;
	}

	public void setWrappedService(RegionService regionService) {
		_regionService = regionService;
	}

	private RegionService _regionService;
}