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
import com.liferay.portal.model.EmailAddress;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing EmailAddress in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see EmailAddress
 * @generated
 */
public class EmailAddressCacheModel implements CacheModel<EmailAddress>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{emailAddressId=");
		sb.append(emailAddressId);
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
		sb.append(", address=");
		sb.append(address);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", primary=");
		sb.append(primary);
		sb.append("}");

		return sb.toString();
	}

	public EmailAddress toEntityModel() {
		EmailAddressImpl emailAddressImpl = new EmailAddressImpl();

		emailAddressImpl.setEmailAddressId(emailAddressId);
		emailAddressImpl.setCompanyId(companyId);
		emailAddressImpl.setUserId(userId);

		if (userName == null) {
			emailAddressImpl.setUserName(StringPool.BLANK);
		}
		else {
			emailAddressImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			emailAddressImpl.setCreateDate(null);
		}
		else {
			emailAddressImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			emailAddressImpl.setModifiedDate(null);
		}
		else {
			emailAddressImpl.setModifiedDate(new Date(modifiedDate));
		}

		emailAddressImpl.setClassNameId(classNameId);
		emailAddressImpl.setClassPK(classPK);

		if (address == null) {
			emailAddressImpl.setAddress(StringPool.BLANK);
		}
		else {
			emailAddressImpl.setAddress(address);
		}

		emailAddressImpl.setTypeId(typeId);
		emailAddressImpl.setPrimary(primary);

		emailAddressImpl.resetOriginalValues();

		return emailAddressImpl;
	}

	public long emailAddressId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String address;
	public int typeId;
	public boolean primary;
}