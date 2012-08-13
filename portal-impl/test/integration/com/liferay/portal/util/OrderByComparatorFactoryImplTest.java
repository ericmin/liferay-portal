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

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.impl.EmailAddressImpl;
import com.liferay.portal.model.impl.EmailAddressModelImpl;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Wesley Gong
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class OrderByComparatorFactoryImplTest {

	@Test
	public void testCollectionsSortMultipleColumnsAscending() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress(
			newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			newDate(0, 2, 2012), "abc@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<EmailAddress>();

		expectedList.add(emailAddress1);
		expectedList.add(emailAddress2);

		List<EmailAddress> actualList = new ArrayList<EmailAddress>();

		actualList.add(emailAddress2);
		actualList.add(emailAddress1);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false, "createDate",
			true);

		Collections.sort(actualList, obc);

		Assert.assertEquals(expectedList, actualList);
	}

	@Test
	public void testCollectionsSortMultipleColumnsDescending()
		throws Exception {

		EmailAddress emailAddress1 = newEmailAddress(
			newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			newDate(0, 2, 2012), "abc@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<EmailAddress>();

		expectedList.add(emailAddress2);
		expectedList.add(emailAddress1);

		List<EmailAddress> actualList = new ArrayList<EmailAddress>();

		actualList.add(emailAddress1);
		actualList.add(emailAddress2);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false, "createDate",
			false);

		Collections.sort(actualList, obc);

		Assert.assertEquals(expectedList, actualList);
	}

	@Test
	public void testCollectionsSortSingleColumnAscending() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress(
			newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			newDate(0, 2, 2012), "def@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<EmailAddress>();

		expectedList.add(emailAddress1);
		expectedList.add(emailAddress2);

		List<EmailAddress> actualList = new ArrayList<EmailAddress>();

		actualList.add(emailAddress2);
		actualList.add(emailAddress1);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", true);

		Collections.sort(actualList, obc);

		Assert.assertEquals(expectedList, actualList);
	}

	@Test
	public void testCollectionsSortSingleColumnDescending() throws Exception {
		EmailAddress emailAddress1 = newEmailAddress(
			newDate(0, 1, 2012), "abc@liferay.com");
		EmailAddress emailAddress2 = newEmailAddress(
			newDate(0, 2, 2012), "def@liferay.com");

		List<EmailAddress> expectedList = new ArrayList<EmailAddress>();

		expectedList.add(emailAddress2);
		expectedList.add(emailAddress1);

		List<EmailAddress> actualList = new ArrayList<EmailAddress>();

		actualList.add(emailAddress1);
		actualList.add(emailAddress2);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false);

		Collections.sort(actualList, obc);

		Assert.assertEquals(expectedList, actualList);
	}

	@Test
	public void testGetOrderByMultipleColumns() throws Exception {
		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", true, "createDate",
			false);

		Assert.assertEquals(
			"EmailAddress.address ASC,EmailAddress.createDate DESC",
			obc.getOrderBy());

		obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false, "createDate",
			true);

		Assert.assertEquals(
			"EmailAddress.address DESC,EmailAddress.createDate ASC",
			obc.getOrderBy());
	}

	@Test
	public void testGetOrderBySingleColumn() throws Exception {
		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", true);

		Assert.assertEquals("EmailAddress.address ASC", obc.getOrderBy());

		obc = OrderByComparatorFactoryUtil.create(
			EmailAddressModelImpl.TABLE_NAME, "address", false);

		Assert.assertEquals("EmailAddress.address DESC", obc.getOrderBy());
	}

	@Test
	public void testInvalidColumns() throws Exception {
		try {
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME);

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
		}

		try {
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address");

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
		}

		try {
			OrderByComparatorFactoryUtil.create(
				EmailAddressModelImpl.TABLE_NAME, "address", true,
				"createDate");

			Assert.fail();
		}
		catch (IllegalArgumentException iae) {
		}
	}

	protected Date newDate(int month, int day, int year) throws Exception {
		Calendar calendar = new GregorianCalendar();

		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.YEAR, year);

		return calendar.getTime();
	}

	protected EmailAddress newEmailAddress(Date createDate, String address) {
		EmailAddress emailAddress = new EmailAddressImpl();

		emailAddress.setCreateDate(createDate);
		emailAddress.setAddress(address);

		return emailAddress;
	}

}