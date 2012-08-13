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

package com.liferay.portlet.calendar.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.calendar.model.CalEvent;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing CalEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see CalEvent
 * @generated
 */
public class CalEventCacheModel implements CacheModel<CalEvent>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(47);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", eventId=");
		sb.append(eventId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", location=");
		sb.append(location);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", durationHour=");
		sb.append(durationHour);
		sb.append(", durationMinute=");
		sb.append(durationMinute);
		sb.append(", allDay=");
		sb.append(allDay);
		sb.append(", timeZoneSensitive=");
		sb.append(timeZoneSensitive);
		sb.append(", type=");
		sb.append(type);
		sb.append(", repeating=");
		sb.append(repeating);
		sb.append(", recurrence=");
		sb.append(recurrence);
		sb.append(", remindBy=");
		sb.append(remindBy);
		sb.append(", firstReminder=");
		sb.append(firstReminder);
		sb.append(", secondReminder=");
		sb.append(secondReminder);
		sb.append("}");

		return sb.toString();
	}

	public CalEvent toEntityModel() {
		CalEventImpl calEventImpl = new CalEventImpl();

		if (uuid == null) {
			calEventImpl.setUuid(StringPool.BLANK);
		}
		else {
			calEventImpl.setUuid(uuid);
		}

		calEventImpl.setEventId(eventId);
		calEventImpl.setGroupId(groupId);
		calEventImpl.setCompanyId(companyId);
		calEventImpl.setUserId(userId);

		if (userName == null) {
			calEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			calEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			calEventImpl.setCreateDate(null);
		}
		else {
			calEventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			calEventImpl.setModifiedDate(null);
		}
		else {
			calEventImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			calEventImpl.setTitle(StringPool.BLANK);
		}
		else {
			calEventImpl.setTitle(title);
		}

		if (description == null) {
			calEventImpl.setDescription(StringPool.BLANK);
		}
		else {
			calEventImpl.setDescription(description);
		}

		if (location == null) {
			calEventImpl.setLocation(StringPool.BLANK);
		}
		else {
			calEventImpl.setLocation(location);
		}

		if (startDate == Long.MIN_VALUE) {
			calEventImpl.setStartDate(null);
		}
		else {
			calEventImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			calEventImpl.setEndDate(null);
		}
		else {
			calEventImpl.setEndDate(new Date(endDate));
		}

		calEventImpl.setDurationHour(durationHour);
		calEventImpl.setDurationMinute(durationMinute);
		calEventImpl.setAllDay(allDay);
		calEventImpl.setTimeZoneSensitive(timeZoneSensitive);

		if (type == null) {
			calEventImpl.setType(StringPool.BLANK);
		}
		else {
			calEventImpl.setType(type);
		}

		calEventImpl.setRepeating(repeating);

		if (recurrence == null) {
			calEventImpl.setRecurrence(StringPool.BLANK);
		}
		else {
			calEventImpl.setRecurrence(recurrence);
		}

		calEventImpl.setRemindBy(remindBy);
		calEventImpl.setFirstReminder(firstReminder);
		calEventImpl.setSecondReminder(secondReminder);

		calEventImpl.resetOriginalValues();

		return calEventImpl;
	}

	public String uuid;
	public long eventId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public String location;
	public long startDate;
	public long endDate;
	public int durationHour;
	public int durationMinute;
	public boolean allDay;
	public boolean timeZoneSensitive;
	public String type;
	public boolean repeating;
	public String recurrence;
	public int remindBy;
	public int firstReminder;
	public int secondReminder;
}