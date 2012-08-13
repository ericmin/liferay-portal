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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.DateFormatFactory;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class DateFormatFactoryImpl implements DateFormatFactory {

	public DateFormat getDate(Locale locale) {
		return getDate(locale, null);
	}

	public DateFormat getDate(Locale locale, TimeZone timeZone) {
		DateFormat dateFormat = DateFormat.getDateInstance(
			DateFormat.SHORT, locale);

		if (timeZone != null) {
			dateFormat.setTimeZone(timeZone);
		}

		return dateFormat;
	}

	public DateFormat getDate(TimeZone timeZone) {
		return getDate(LocaleUtil.getDefault(), timeZone);
	}

	public DateFormat getDateTime(Locale locale) {
		return getDateTime(locale, null);
	}

	public DateFormat getDateTime(Locale locale, TimeZone timeZone) {
		DateFormat dateFormat = DateFormat.getDateTimeInstance(
			DateFormat.SHORT, DateFormat.SHORT, locale);

		if (timeZone != null) {
			dateFormat.setTimeZone(timeZone);
		}

		return dateFormat;
	}

	public DateFormat getDateTime(TimeZone timeZone) {
		return getDateTime(LocaleUtil.getDefault(), timeZone);
	}

	public DateFormat getSimpleDateFormat(String pattern) {
		return getSimpleDateFormat(pattern, LocaleUtil.getDefault(), null);
	}

	public DateFormat getSimpleDateFormat(String pattern, Locale locale) {
		return getSimpleDateFormat(pattern, locale, null);
	}

	public DateFormat getSimpleDateFormat(
		String pattern, Locale locale, TimeZone timeZone) {

		DateFormat dateFormat = new SimpleDateFormat(pattern, locale);

		if (timeZone != null) {
			dateFormat.setTimeZone(timeZone);
		}

		return dateFormat;
	}

	public DateFormat getSimpleDateFormat(String pattern, TimeZone timeZone) {
		return getSimpleDateFormat(pattern, LocaleUtil.getDefault(), timeZone);
	}

	public DateFormat getTime(Locale locale) {
		return getTime(locale, null);
	}

	public DateFormat getTime(Locale locale, TimeZone timeZone) {
		DateFormat dateFormat = DateFormat.getTimeInstance(
			DateFormat.SHORT, locale);

		if (timeZone != null) {
			dateFormat.setTimeZone(timeZone);
		}

		return dateFormat;
	}

	public DateFormat getTime(TimeZone timeZone) {
		return getTime(LocaleUtil.getDefault(), timeZone);
	}

}