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

package com.liferay.portal.service.impl;

import com.liferay.portal.RegionCodeException;
import com.liferay.portal.RegionNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Region;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.base.RegionServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class RegionServiceImpl extends RegionServiceBaseImpl {

	public Region addRegion(
			long countryId, String regionCode, String name, boolean active)
		throws PortalException, SystemException {

		if (!getPermissionChecker().isOmniadmin()) {
			throw new PrincipalException();
		}

		countryPersistence.findByPrimaryKey(countryId);

		if (Validator.isNull(regionCode)) {
			throw new RegionCodeException();
		}

		if (Validator.isNull(name)) {
			throw new RegionNameException();
		}

		long regionId = counterLocalService.increment();

		Region region = regionPersistence.create(regionId);

		region.setCountryId(countryId);
		region.setRegionCode(regionCode);
		region.setName(name);
		region.setActive(active);

		regionPersistence.update(region, false);

		return region;
	}

	public Region fetchRegion(long countryId, String regionCode)
		throws SystemException {

		return regionPersistence.fetchByC_R(countryId, regionCode);
	}

	public Region getRegion(long regionId)
		throws PortalException, SystemException {

		return regionPersistence.findByPrimaryKey(regionId);
	}

	public Region getRegion(long countryId, String regionCode)
		throws PortalException, SystemException {

		return regionPersistence.findByC_R(countryId, regionCode);
	}

	public List<Region> getRegions() throws SystemException {
		return regionPersistence.findAll();
	}

	public List<Region> getRegions(boolean active) throws SystemException {
		return regionPersistence.findByActive(active);
	}

	public List<Region> getRegions(long countryId) throws SystemException {
		return regionPersistence.findByCountryId(countryId);
	}

	public List<Region> getRegions(long countryId, boolean active)
		throws SystemException {

		return regionPersistence.findByC_A(countryId, active);
	}

}