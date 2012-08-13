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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Igor Spasic
 */
public class FooService {

	public static FooData getFooData(int id) {
		FooData fooData = new FooDataImpl();

		fooData.setId(id);

		return fooData;
	}

	public static List<FooData> getFooDatas() {
		List<FooData> fooDataList = new ArrayList<FooData>();

		fooDataList.add(getFooData(1));
		fooDataList.add(getFooData(2));
		fooDataList.add(getFooData(3));

		return fooDataList;
	}

	public static String hello() {
		return "world";
	}

	public static String helloWorld(Integer userId, String worldName) {
		return "Welcome " + userId + " to " + worldName;
	}

	public static String hey(
		Calendar calendar, long[] userIds, List<Locale> locales) {

		return calendar.get(Calendar.YEAR) + ", " + userIds[0] + '/' +
			userIds.length + ", " + locales.get(0) + '/' + locales.size();
	}

	public static String methodOne(long id, long nameId) {
		return "m-2";
	}

	public static String methodOne(long id, long nameId, String subname) {
		return "m-3";
	}

	public static String methodOne(long id, String name) {
		return "m-1";
	}

	public static String nullLover(String name, int number) {
		if (name == null) {
			return "null!";
		}

		return '[' + name + '|' + number + ']';
	}

	public static String nullReturn() {
		return null;
	}

	public static String srvcctx(ServiceContext serviceContext) {
		Class<?> clazz = serviceContext.getClass();

		return clazz.getName();
	}

	public static String use1(FooDataImpl fooData) {
		return "using #1: " + fooData.getValue();
	}

	public static String use2(FooData fooData) {
		return "using #2: " + fooData.getValue();
	}

}