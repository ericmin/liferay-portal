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

package com.liferay.portlet.calendar.util.comparator;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.util.CalUtil;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Samuel Kong
 * @author Janghyun Kim
 */
public class EventTimeComparator implements Comparator<CalEvent> {

	public EventTimeComparator(TimeZone timeZone, Locale locale) {
		_timeZone = timeZone;
		_locale = locale;
	}

	public int compare(CalEvent event1, CalEvent event2) {
		boolean allDay1 = CalUtil.isAllDay(event1, _timeZone, _locale);
		boolean allDay2 = CalUtil.isAllDay(event2, _timeZone, _locale);

		if (allDay1 && allDay2) {
			return compareTitle(event1, event2);
		}
		else if (allDay1) {
			return -1;
		}
		else if (allDay2) {
			return 1;
		}

		boolean repeating = event1.isRepeating() || event2.isRepeating();

		Date startDate1 = getStartDate(event1, _timeZone, repeating);
		Date startDate2 = getStartDate(event2, _timeZone, repeating);

		int value = startDate1.compareTo(startDate2);

		if (value != 0) {
			return value;
		}

		Long duration1 = getDuration(event1);
		Long duration2 = getDuration(event2);

		value = duration1.compareTo(duration2);

		if (value != 0) {
			return value;
		}

		return compareTitle(event1, event2);
	}

	protected int compareTitle(CalEvent event1, CalEvent event2) {
		return event1.getTitle().toLowerCase().compareTo(
			event2.getTitle().toLowerCase());
	}

	protected Long getDuration(CalEvent event) {
		return (Time.HOUR * event.getDurationHour()) +
			(Time.MINUTE * event.getDurationMinute());
	}

	protected Date getStartDate(
		CalEvent event, TimeZone timeZone, boolean repeating) {

		if (repeating) {

			// Normalize the start date of the event when at least one of the
			// events is a recurring event

			Calendar calendar = null;

			if (event.isTimeZoneSensitive()) {
				calendar = CalendarFactoryUtil.getCalendar(_timeZone);
			}
			else {
				calendar = CalendarFactoryUtil.getCalendar();
			}

			calendar.setTime(event.getStartDate());

			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.YEAR, 1);

			return Time.getDate(calendar);
		}
		else {
			if (event.isTimeZoneSensitive()) {
				return Time.getDate(event.getStartDate(), timeZone);
			}
			else {
				return event.getStartDate();
			}
		}
	}

	private Locale _locale;
	private TimeZone _timeZone;

}