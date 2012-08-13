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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Address in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Address
 * @generated
 */
public class AddressCacheModel implements CacheModel<Address>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{addressId=");
		sb.append(addressId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", street1=");
		sb.append(street1);
		sb.append(", street2=");
		sb.append(street2);
		sb.append(", street3=");
		sb.append(street3);
		sb.append(", city=");
		sb.append(city);
		sb.append(", zip=");
		sb.append(zip);
		sb.append(", regionId=");
		sb.append(regionId);
		sb.append(", countryId=");
		sb.append(countryId);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", mailing=");
		sb.append(mailing);
		sb.append(", primary=");
		sb.append(primary);
		sb.append("}");

		return sb.toString();
	}

	public Address toEntityModel() {
		AddressImpl addressImpl = new AddressImpl();

		addressImpl.setAddressId(addressId);
		addressImpl.setCompanyId(companyId);
		addressImpl.setUserId(userId);

		if (userName == null) {
			addressImpl.setUserName(StringPool.BLANK);
		}
		else {
			addressImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			addressImpl.setCreateDate(null);
		}
		else {
			addressImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			addressImpl.setModifiedDate(null);
		}
		else {
			addressImpl.setModifiedDate(new Date(modifiedDate));
		}

		addressImpl.setClassNameId(classNameId);
		addressImpl.setClassPK(classPK);

		if (street1 == null) {
			addressImpl.setStreet1(StringPool.BLANK);
		}
		else {
			addressImpl.setStreet1(street1);
		}

		if (street2 == null) {
			addressImpl.setStreet2(StringPool.BLANK);
		}
		else {
			addressImpl.setStreet2(street2);
		}

		if (street3 == null) {
			addressImpl.setStreet3(StringPool.BLANK);
		}
		else {
			addressImpl.setStreet3(street3);
		}

		if (city == null) {
			addressImpl.setCity(StringPool.BLANK);
		}
		else {
			addressImpl.setCity(city);
		}

		if (zip == null) {
			addressImpl.setZip(StringPool.BLANK);
		}
		else {
			addressImpl.setZip(zip);
		}

		addressImpl.setRegionId(regionId);
		addressImpl.setCountryId(countryId);
		addressImpl.setTypeId(typeId);
		addressImpl.setMailing(mailing);
		addressImpl.setPrimary(primary);

		addressImpl.resetOriginalValues();

		return addressImpl;
	}

	public long addressId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String street1;
	public String street2;
	public String street3;
	public String city;
	public String zip;
	public long regionId;
	public long countryId;
	public int typeId;
	public boolean mailing;
	public boolean primary;
}