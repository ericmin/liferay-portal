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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Country;

import java.io.Serializable;

/**
 * The cache model class for representing Country in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Country
 * @generated
 */
public class CountryCacheModel implements CacheModel<Country>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{countryId=");
		sb.append(countryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", a2=");
		sb.append(a2);
		sb.append(", a3=");
		sb.append(a3);
		sb.append(", number=");
		sb.append(number);
		sb.append(", idd=");
		sb.append(idd);
		sb.append(", zipRequired=");
		sb.append(zipRequired);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public Country toEntityModel() {
		CountryImpl countryImpl = new CountryImpl();

		countryImpl.setCountryId(countryId);

		if (name == null) {
			countryImpl.setName(StringPool.BLANK);
		}
		else {
			countryImpl.setName(name);
		}

		if (a2 == null) {
			countryImpl.setA2(StringPool.BLANK);
		}
		else {
			countryImpl.setA2(a2);
		}

		if (a3 == null) {
			countryImpl.setA3(StringPool.BLANK);
		}
		else {
			countryImpl.setA3(a3);
		}

		if (number == null) {
			countryImpl.setNumber(StringPool.BLANK);
		}
		else {
			countryImpl.setNumber(number);
		}

		if (idd == null) {
			countryImpl.setIdd(StringPool.BLANK);
		}
		else {
			countryImpl.setIdd(idd);
		}

		countryImpl.setZipRequired(zipRequired);
		countryImpl.setActive(active);

		countryImpl.resetOriginalValues();

		return countryImpl;
	}

	public long countryId;
	public String name;
	public String a2;
	public String a3;
	public String number;
	public String idd;
	public boolean zipRequired;
	public boolean active;
}