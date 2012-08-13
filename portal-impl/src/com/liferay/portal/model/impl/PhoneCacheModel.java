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
import com.liferay.portal.model.Phone;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Phone in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Phone
 * @generated
 */
public class PhoneCacheModel implements CacheModel<Phone>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{phoneId=");
		sb.append(phoneId);
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
		sb.append(", number=");
		sb.append(number);
		sb.append(", extension=");
		sb.append(extension);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", primary=");
		sb.append(primary);
		sb.append("}");

		return sb.toString();
	}

	public Phone toEntityModel() {
		PhoneImpl phoneImpl = new PhoneImpl();

		phoneImpl.setPhoneId(phoneId);
		phoneImpl.setCompanyId(companyId);
		phoneImpl.setUserId(userId);

		if (userName == null) {
			phoneImpl.setUserName(StringPool.BLANK);
		}
		else {
			phoneImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			phoneImpl.setCreateDate(null);
		}
		else {
			phoneImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			phoneImpl.setModifiedDate(null);
		}
		else {
			phoneImpl.setModifiedDate(new Date(modifiedDate));
		}

		phoneImpl.setClassNameId(classNameId);
		phoneImpl.setClassPK(classPK);

		if (number == null) {
			phoneImpl.setNumber(StringPool.BLANK);
		}
		else {
			phoneImpl.setNumber(number);
		}

		if (extension == null) {
			phoneImpl.setExtension(StringPool.BLANK);
		}
		else {
			phoneImpl.setExtension(extension);
		}

		phoneImpl.setTypeId(typeId);
		phoneImpl.setPrimary(primary);

		phoneImpl.resetOriginalValues();

		return phoneImpl;
	}

	public long phoneId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String number;
	public String extension;
	public int typeId;
	public boolean primary;
}