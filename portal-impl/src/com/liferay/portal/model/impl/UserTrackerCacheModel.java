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
import com.liferay.portal.model.UserTracker;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing UserTracker in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserTracker
 * @generated
 */
public class UserTrackerCacheModel implements CacheModel<UserTracker>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{userTrackerId=");
		sb.append(userTrackerId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", sessionId=");
		sb.append(sessionId);
		sb.append(", remoteAddr=");
		sb.append(remoteAddr);
		sb.append(", remoteHost=");
		sb.append(remoteHost);
		sb.append(", userAgent=");
		sb.append(userAgent);
		sb.append("}");

		return sb.toString();
	}

	public UserTracker toEntityModel() {
		UserTrackerImpl userTrackerImpl = new UserTrackerImpl();

		userTrackerImpl.setUserTrackerId(userTrackerId);
		userTrackerImpl.setCompanyId(companyId);
		userTrackerImpl.setUserId(userId);

		if (modifiedDate == Long.MIN_VALUE) {
			userTrackerImpl.setModifiedDate(null);
		}
		else {
			userTrackerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (sessionId == null) {
			userTrackerImpl.setSessionId(StringPool.BLANK);
		}
		else {
			userTrackerImpl.setSessionId(sessionId);
		}

		if (remoteAddr == null) {
			userTrackerImpl.setRemoteAddr(StringPool.BLANK);
		}
		else {
			userTrackerImpl.setRemoteAddr(remoteAddr);
		}

		if (remoteHost == null) {
			userTrackerImpl.setRemoteHost(StringPool.BLANK);
		}
		else {
			userTrackerImpl.setRemoteHost(remoteHost);
		}

		if (userAgent == null) {
			userTrackerImpl.setUserAgent(StringPool.BLANK);
		}
		else {
			userTrackerImpl.setUserAgent(userAgent);
		}

		userTrackerImpl.resetOriginalValues();

		return userTrackerImpl;
	}

	public long userTrackerId;
	public long companyId;
	public long userId;
	public long modifiedDate;
	public String sessionId;
	public String remoteAddr;
	public String remoteHost;
	public String userAgent;
}