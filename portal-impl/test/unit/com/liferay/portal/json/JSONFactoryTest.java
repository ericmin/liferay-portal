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

package com.liferay.portal.json;

import com.liferay.portal.dao.orm.common.EntityCacheImpl;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Igor Spasic
 */
public class JSONFactoryTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		new JSONFactoryUtil().setJSONFactory(new JSONFactoryImpl());
	}

	public void testLooseDeserialize() {
		try {
			JSONFactoryUtil.looseDeserialize(
				"{\"class\":\"" + EntityCacheUtil.class.getName() + "\"}}");

			fail();
		}
		catch (Exception e) {
		}

		try {
			Object object = JSONFactoryUtil.looseDeserialize(
				"{\"class\":\"java.lang.Thread\"}}");

			assertEquals(Thread.class, object.getClass());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}

	public void testLooseDeserializeSafe() {
		Object object = JSONFactoryUtil.looseDeserializeSafe(
			"{\"class\":\"java.lang.Thread\"}}");

		assertEquals(HashMap.class, object.getClass());

		object = JSONFactoryUtil.looseDeserializeSafe(
			"{\"\u0063lass\":\"java.lang.Thread\"}}");

		assertEquals(HashMap.class, object.getClass());
		assertTrue(((Map<?, ?>)object).containsKey("class"));

		try {
			JSONFactoryUtil.looseDeserializeSafe(
				"{\"class\":\"" + EntityCacheUtil.class.getName() + "\"}}");
		}
		catch (Exception e) {
			fail(e.toString());
		}

		Map<?, ?> map = (Map<?, ?>)JSONFactoryUtil.looseDeserializeSafe(
			"{\"class\":\"" + EntityCacheUtil.class.getName() +
				"\",\"foo\": \"boo\"}");

		assertNotNull(map);
		assertEquals(2, map.size());
		assertEquals(
			"com.liferay.portal.kernel.dao.orm.EntityCacheUtil",
			map.get("class"));
		assertEquals("boo", map.get("foo"));

		map = (Map<?, ?>)JSONFactoryUtil.looseDeserializeSafe(
			"{\"class\":\"" + EntityCacheUtil.class.getName() +
				"\",\"foo\": \"boo\",\"entityCache\":{\"class\":\"" +
					EntityCacheImpl.class.getName() + "\"}}");

		assertNotNull(map);
		assertEquals(3, map.size());
		assertEquals( EntityCacheUtil.class.getName(), map.get("class"));
		assertEquals("boo", map.get("foo"));

		map = (Map<?, ?>)map.get("entityCache");

		assertNotNull(map);
		assertEquals(1, map.size());
		assertEquals(EntityCacheImpl.class.getName(), map.get("class"));
	}

}