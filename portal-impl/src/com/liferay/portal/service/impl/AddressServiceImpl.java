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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.base.AddressServiceBaseImpl;
import com.liferay.portal.service.permission.CommonPermissionUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class AddressServiceImpl extends AddressServiceBaseImpl {

	public Address addAddress(
			String className, long classPK, String street1, String street2,
			String street3, String city, String zip, long regionId,
			long countryId, int typeId, boolean mailing, boolean primary)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.UPDATE);

		return addressLocalService.addAddress(
			getUserId(), className, classPK, street1, street2, street3, city,
			zip, regionId, countryId, typeId, mailing, primary);
	}

	public void deleteAddress(long addressId)
		throws PortalException, SystemException {

		Address address = addressPersistence.findByPrimaryKey(addressId);

		CommonPermissionUtil.check(
			getPermissionChecker(), address.getClassNameId(),
			address.getClassPK(), ActionKeys.UPDATE);

		addressLocalService.deleteAddress(addressId);
	}

	public Address getAddress(long addressId)
		throws PortalException, SystemException {

		Address address = addressPersistence.findByPrimaryKey(addressId);

		CommonPermissionUtil.check(
			getPermissionChecker(), address.getClassNameId(),
			address.getClassPK(), ActionKeys.VIEW);

		return address;
	}

	public List<Address> getAddresses(String className, long classPK)
		throws PortalException, SystemException {

		CommonPermissionUtil.check(
			getPermissionChecker(), className, classPK, ActionKeys.VIEW);

		User user = getUser();

		return addressLocalService.getAddresses(
			user.getCompanyId(), className, classPK);
	}

	public Address updateAddress(
			long addressId, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId, int typeId,
			boolean mailing, boolean primary)
		throws PortalException, SystemException {

		Address address = addressPersistence.findByPrimaryKey(addressId);

		CommonPermissionUtil.check(
			getPermissionChecker(), address.getClassNameId(),
			address.getClassPK(), ActionKeys.UPDATE);

		return addressLocalService.updateAddress(
			addressId, street1, street2, street3, city, zip, regionId,
			countryId, typeId, mailing, primary);
	}

}