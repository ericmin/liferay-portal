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
import com.liferay.portal.model.Subscription;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Subscription in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Subscription
 * @generated
 */
public class SubscriptionCacheModel implements CacheModel<Subscription>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{subscriptionId=");
		sb.append(subscriptionId);
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
		sb.append(", frequency=");
		sb.append(frequency);
		sb.append("}");

		return sb.toString();
	}

	public Subscription toEntityModel() {
		SubscriptionImpl subscriptionImpl = new SubscriptionImpl();

		subscriptionImpl.setSubscriptionId(subscriptionId);
		subscriptionImpl.setCompanyId(companyId);
		subscriptionImpl.setUserId(userId);

		if (userName == null) {
			subscriptionImpl.setUserName(StringPool.BLANK);
		}
		else {
			subscriptionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			subscriptionImpl.setCreateDate(null);
		}
		else {
			subscriptionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			subscriptionImpl.setModifiedDate(null);
		}
		else {
			subscriptionImpl.setModifiedDate(new Date(modifiedDate));
		}

		subscriptionImpl.setClassNameId(classNameId);
		subscriptionImpl.setClassPK(classPK);

		if (frequency == null) {
			subscriptionImpl.setFrequency(StringPool.BLANK);
		}
		else {
			subscriptionImpl.setFrequency(frequency);
		}

		subscriptionImpl.resetOriginalValues();

		return subscriptionImpl;
	}

	public long subscriptionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String frequency;
}