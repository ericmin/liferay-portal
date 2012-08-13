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

package com.liferay.portal.kernel.test;

import java.io.File;

import java.lang.reflect.Constructor;

import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 */
public class NewClassLoaderTestCase extends TestCase {

	@Override
	public void setUp() throws Exception {
		String pathsString = System.getProperty("java.class.path");

		String[] paths = pathsString.split(File.pathSeparator);

		List<URL> urlsList = new ArrayList<URL>();

		for (String path : paths) {
			File file = new File(path);

			URI uri = file.toURI();

			urlsList.add(uri.toURL());
		}

		urls = urlsList.toArray(new URL[urlsList.size()]);
	}

	protected <T> T runInNewClassLoader(Class<? extends Callable<T>> clazz)
		throws Exception {

		assertSame(
			clazz + " is not enclosed by " + getClass(), getClass(),
			clazz.getEnclosingClass());

		Constructor<? extends Callable<T>> constructor = null;

		try {
			constructor = clazz.getDeclaredConstructor();
		}
		catch (NoSuchMethodException nsme1) {
			try {
				constructor = clazz.getDeclaredConstructor(getClass());
			}
			catch (NoSuchMethodException nsme2) {
				throw new Exception(
					clazz.getName() + " does not have a default constructor");
			}
		}

		return runInNewClassLoader(constructor);
	}

	protected <T> T runInNewClassLoader(
			Constructor<? extends Callable<T>> constructor, Object... arguments)
		throws Exception {

		// Prepare new class loader

		URLClassLoader urlClassLoader = new URLClassLoader(urls, null);

		// Get loaded class

		Class<? extends Callable<T>> callableClass =
			constructor.getDeclaringClass();

		assertSame(
			callableClass + " is not enclosed by " + getClass(), getClass(),
			callableClass.getEnclosingClass());

		// Reload class with new class loader

		callableClass = (Class<? extends Callable<T>>)urlClassLoader.loadClass(
			callableClass.getName());

		// Reload constructor paramter types

		Class<?>[] parameterTypes = constructor.getParameterTypes();

		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = urlClassLoader.loadClass(
				parameterTypes[i].getName());
		}

		// Refetch constructor

		constructor = callableClass.getDeclaredConstructor(parameterTypes);

		// Inner class requires outter reference

		if (parameterTypes.length > arguments.length) {

			// Reload outter class with new class loader

			Class<?> outterClass = urlClassLoader.loadClass(
				getClass().getName());

			// Create outter object for the inner class instance

			Object outterObject = outterClass.newInstance();

			Object[] newArguments = new Object[arguments.length + 1];

			newArguments[0] = outterObject;

			System.arraycopy(arguments, 0, newArguments, 1, arguments.length);

			arguments = newArguments;
		}

		constructor.setAccessible(true);

		// Create callable instance that is fully loaded by the new class loader

		Callable<T> callable = constructor.newInstance(arguments);

		// Run callable with new class loader

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(urlClassLoader);

			return callable.call();
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}
	}

	protected URL[] urls;

}