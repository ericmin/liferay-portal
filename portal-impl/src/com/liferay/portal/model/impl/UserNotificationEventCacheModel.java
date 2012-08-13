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
import com.liferay.portal.model.UserNotificationEvent;

import java.io.Serializable;

/**
 * The cache model class for representing UserNotificationEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationEvent
 * @generated
 */
public class UserNotificationEventCacheModel implements CacheModel<UserNotificationEvent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", userNotificationEventId=");
		sb.append(userNotificationEventId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", timestamp=");
		sb.append(timestamp);
		sb.append(", deliverBy=");
		sb.append(deliverBy);
		sb.append(", payload=");
		sb.append(payload);
		sb.append(", archived=");
		sb.append(archived);
		sb.append("}");

		return sb.toString();
	}

	public UserNotificationEvent toEntityModel() {
		UserNotificationEventImpl userNotificationEventImpl = new UserNotificationEventImpl();

		if (uuid == null) {
			userNotificationEventImpl.setUuid(StringPool.BLANK);
		}
		else {
			userNotificationEventImpl.setUuid(uuid);
		}

		userNotificationEventImpl.setUserNotificationEventId(userNotificationEventId);
		userNotificationEventImpl.setCompanyId(companyId);
		userNotificationEventImpl.setUserId(userId);

		if (type == null) {
			userNotificationEventImpl.setType(StringPool.BLANK);
		}
		else {
			userNotificationEventImpl.setType(type);
		}

		userNotificationEventImpl.setTimestamp(timestamp);
		userNotificationEventImpl.setDeliverBy(deliverBy);

		if (payload == null) {
			userNotificationEventImpl.setPayload(StringPool.BLANK);
		}
		else {
			userNotificationEventImpl.setPayload(payload);
		}

		userNotificationEventImpl.setArchived(archived);

		userNotificationEventImpl.resetOriginalValues();

		return userNotificationEventImpl;
	}

	public String uuid;
	public long userNotificationEventId;
	public long companyId;
	public long userId;
	public String type;
	public long timestamp;
	public long deliverBy;
	public String payload;
	public boolean archived;
}