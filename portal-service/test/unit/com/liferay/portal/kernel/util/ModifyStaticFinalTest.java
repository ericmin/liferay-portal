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

import com.liferay.portal.kernel.test.NewClassLoaderTestCase;

import java.lang.reflect.Constructor;

import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 */
public class ModifyStaticFinalTest extends NewClassLoaderTestCase {

	public void testAnonymousClass() throws Exception {
		assertFalse(StaticFinalClass.VALUE);

		System.setProperty(_TEST_KEY, "true");

		assertFalse(StaticFinalClass.VALUE);

		Callable<Boolean> callable = new Callable<Boolean>() {

			public Boolean call() throws Exception {
				return StaticFinalClass.VALUE;
			}

		};

		assertTrue(
			runInNewClassLoader(
				(Class<? extends Callable<Boolean>>)callable.getClass()));
	}

	public void testLocalClass() throws Exception {
		assertFalse(StaticFinalClass.VALUE);

		System.setProperty(_TEST_KEY, "true");

		assertFalse(StaticFinalClass.VALUE);

		class LocalCallable implements Callable<Boolean> {

			public Boolean call() throws Exception {
				return StaticFinalClass.VALUE;
			}

		}

		assertTrue(runInNewClassLoader(LocalCallable.class));
	}

	public void testMemberClass() throws Exception {
		assertFalse(StaticFinalClass.VALUE);

		System.setProperty(_TEST_KEY, "true");

		assertFalse(StaticFinalClass.VALUE);

		assertTrue(runInNewClassLoader(MemberCallable.class));
	}

	public void testNoDefaultConstructor() throws Exception {
		assertFalse(StaticFinalClass.VALUE);

		System.setProperty(_TEST_KEY, "true");

		assertFalse(StaticFinalClass.VALUE);

		class NoDefaultConstructorCallable implements Callable<Boolean> {

			@SuppressWarnings("unused")
			public NoDefaultConstructorCallable(String value) {
			}

			public Boolean call() throws Exception {
				return StaticFinalClass.VALUE;
			}

		}

		try {
			runInNewClassLoader(NoDefaultConstructorCallable.class);

			fail();
		}
		catch (Exception e) {
		}

		Constructor<NoDefaultConstructorCallable> constructor =
			NoDefaultConstructorCallable.class.getDeclaredConstructor(
				getClass(), String.class);

		assertTrue(runInNewClassLoader(constructor, "value"));
	}

	public void testStaticMemberClass() throws Exception {
		assertFalse(StaticFinalClass.VALUE);

		System.setProperty(_TEST_KEY, "true");

		assertFalse(StaticFinalClass.VALUE);

		assertTrue(runInNewClassLoader(StaticMemberCallable.class));
	}

	public void testThrowsException() throws Exception {
		try {
			Callable<Boolean> callable = new Callable<Boolean>() {

				public Boolean call() throws Exception {
					throw new Exception();
				}

			};

			runInNewClassLoader(
				(Class<? extends Callable<Boolean>>)callable.getClass());

			fail();
		}
		catch (Exception e) {
		}
	}

	private static final String _TEST_KEY = "test.key";

	private static class StaticFinalClass {

		public static final boolean VALUE = Boolean.valueOf(
			System.getProperty(_TEST_KEY));

	}

	private static class StaticMemberCallable implements Callable<Boolean> {

		public Boolean call() throws Exception {
			return StaticFinalClass.VALUE;
		}

	}

	private class MemberCallable implements Callable<Boolean> {

		public Boolean call() throws Exception {
			return StaticFinalClass.VALUE;
		}

	}

}