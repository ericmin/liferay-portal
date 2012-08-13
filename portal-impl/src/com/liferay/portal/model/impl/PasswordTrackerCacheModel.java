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
import com.liferay.portal.model.PasswordTracker;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PasswordTracker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PasswordTracker
 * @generated
 */
public class PasswordTrackerCacheModel implements CacheModel<PasswordTracker>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{passwordTrackerId=");
		sb.append(passwordTrackerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", password=");
		sb.append(password);
		sb.append("}");

		return sb.toString();
	}

	public PasswordTracker toEntityModel() {
		PasswordTrackerImpl passwordTrackerImpl = new PasswordTrackerImpl();

		passwordTrackerImpl.setPasswordTrackerId(passwordTrackerId);
		passwordTrackerImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			passwordTrackerImpl.setCreateDate(null);
		}
		else {
			passwordTrackerImpl.setCreateDate(new Date(createDate));
		}

		if (password == null) {
			passwordTrackerImpl.setPassword(StringPool.BLANK);
		}
		else {
			passwordTrackerImpl.setPassword(password);
		}

		passwordTrackerImpl.resetOriginalValues();

		return passwordTrackerImpl;
	}

	public long passwordTrackerId;
	public long userId;
	public long createDate;
	public String password;
}