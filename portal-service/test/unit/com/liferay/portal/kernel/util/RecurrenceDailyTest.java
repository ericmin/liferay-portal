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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;

import java.util.Calendar;

/**
 * @author Douglas Wong
 */
public class RecurrenceDailyTest extends RecurrenceTestCase {

	public void testDtStart() {
		Recurrence recurrence = getRecurrence(dtStart, durationOneHour, 2);

		Calendar beforeDtStart = getCalendar(2008, FEBRUARY, 5, 22, 9);

		assertRecurrenceEquals(false, recurrence, beforeDtStart);

		Calendar duringDtStart1 = getCalendar(2008, FEBRUARY, 5, 22, 10);
		Calendar duringDtStart2 = getCalendar(2008, FEBRUARY, 5, 23, 9);

		assertRecurrenceEquals(true, recurrence, duringDtStart1);
		assertRecurrenceEquals(true, recurrence, duringDtStart2);

		Calendar afterDtStart = getCalendar(2008, FEBRUARY, 5, 23, 10);

		assertRecurrenceEquals(false, recurrence, afterDtStart);
	}

	public void testRecurrence() {
		Recurrence recurrence = getRecurrence(dtStart, durationOneHour, 1);

		Calendar beforeRecurrence = getCalendar(2008, FEBRUARY, 5, 22, 9);

		assertRecurrenceEquals(false, recurrence, beforeRecurrence);

		Calendar duringRecurrence1 = getCalendar(2008, FEBRUARY, 5, 22, 10);
		Calendar duringRecurrence2 = getCalendar(2008, FEBRUARY, 6, 22, 10);
		Calendar duringRecurrence3 = getCalendar(2008, FEBRUARY, 8, 22, 10);
		Calendar duringRecurrence4 = getCalendar(2008, FEBRUARY, 9, 22, 10);
		Calendar duringRecurrence5 = getCalendar(2008, FEBRUARY, 10, 22, 10);
		Calendar duringRecurrence6 = getCalendar(2008, FEBRUARY, 15, 22, 10);

		assertRecurrenceEquals(true, recurrence, duringRecurrence1);
		assertRecurrenceEquals(true, recurrence, duringRecurrence2);
		assertRecurrenceEquals(true, recurrence, duringRecurrence3);
		assertRecurrenceEquals(true, recurrence, duringRecurrence4);
		assertRecurrenceEquals(true, recurrence, duringRecurrence5);
		assertRecurrenceEquals(true, recurrence, duringRecurrence6);

		Calendar afterRecurrence = getCalendar(2008, FEBRUARY, 5, 23, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	public void testRecurrenceCrossDates() {
		Recurrence recurrence = getRecurrence(dtStart, durationTwoHours, 1);

		Calendar duringRecurrence = getCalendar(2008, FEBRUARY, 6, 0, 9);

		assertRecurrenceEquals(true, recurrence, duringRecurrence);

		Calendar afterRecurrence = getCalendar(2008, FEBRUARY, 6, 0, 10);

		assertRecurrenceEquals(false, recurrence, afterRecurrence);
	}

	public void testRecurrenceWithInterval() {
		Recurrence recurrence = getRecurrence(dtStart, durationOneHour, 2);

		Calendar duringRecurrence1 = getCalendar(2008, FEBRUARY, 5, 22, 15);
		Calendar duringRecurrence2 = getCalendar(2008, FEBRUARY, 6, 22, 15);
		Calendar duringRecurrence3 = getCalendar(2008, FEBRUARY, 7, 22, 15);
		Calendar duringRecurrence4 = getCalendar(2008, FEBRUARY, 8, 22, 15);

		assertRecurrenceEquals(true, recurrence, duringRecurrence1);
		assertRecurrenceEquals(false, recurrence, duringRecurrence2);
		assertRecurrenceEquals(true, recurrence, duringRecurrence3);
		assertRecurrenceEquals(false, recurrence, duringRecurrence4);
	}

	public void testRecurrenceWithUntilDate() {
		Recurrence recurrence = getRecurrence(dtStart, durationOneHour, 1);

		recurrence.setUntil(getCalendar(2008, FEBRUARY, 6, 22, 0));

		Calendar beforeUntil = getCalendar(2008, FEBRUARY, 5, 22, 15);

		assertRecurrenceEquals(true, recurrence, beforeUntil);

		Calendar afterUntil = getCalendar(2008, FEBRUARY, 6, 22, 15);

		assertRecurrenceEquals(false, recurrence, afterUntil);
	}

	protected Recurrence getRecurrence(
		Calendar dtStart, Duration duration, int interval) {

		Recurrence recurrence = new Recurrence(
			dtStart, duration, Recurrence.DAILY);

		recurrence.setInterval(interval);

		return recurrence;
	}

}