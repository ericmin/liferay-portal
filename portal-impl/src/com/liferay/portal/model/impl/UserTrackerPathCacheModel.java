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
import com.liferay.portal.model.UserTrackerPath;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing UserTrackerPath in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerPath
 * @generated
 */
public class UserTrackerPathCacheModel implements CacheModel<UserTrackerPath>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{userTrackerPathId=");
		sb.append(userTrackerPathId);
		sb.append(", userTrackerId=");
		sb.append(userTrackerId);
		sb.append(", path=");
		sb.append(path);
		sb.append(", pathDate=");
		sb.append(pathDate);
		sb.append("}");

		return sb.toString();
	}

	public UserTrackerPath toEntityModel() {
		UserTrackerPathImpl userTrackerPathImpl = new UserTrackerPathImpl();

		userTrackerPathImpl.setUserTrackerPathId(userTrackerPathId);
		userTrackerPathImpl.setUserTrackerId(userTrackerId);

		if (path == null) {
			userTrackerPathImpl.setPath(StringPool.BLANK);
		}
		else {
			userTrackerPathImpl.setPath(path);
		}

		if (pathDate == Long.MIN_VALUE) {
			userTrackerPathImpl.setPathDate(null);
		}
		else {
			userTrackerPathImpl.setPathDate(new Date(pathDate));
		}

		userTrackerPathImpl.resetOriginalValues();

		return userTrackerPathImpl;
	}

	public long userTrackerPathId;
	public long userTrackerId;
	public String path;
	public long pathDate;
}