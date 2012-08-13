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

import java.io.PrintStream;
import java.io.PrintWriter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Brian Wing Shun Chan
 */
public class SortedProperties extends Properties {

	public SortedProperties() {
		super();

		_names = new TreeSet<String>();
	}

	public SortedProperties(Properties properties) {
		this();

		Enumeration<String> enu =
			(Enumeration<String>)properties.propertyNames();

		while (enu.hasMoreElements()) {
			String key = enu.nextElement();

			String value = properties.getProperty(key);

			setProperty(key, value);
		}
	}

	@Override
	public void clear() {
		super.clear();

		_names.clear();
	}

	@Override
	public void list(PrintStream out) {
		System.out.println("-- listing properties --");

		Enumeration<String> enu = propertyNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			out.println(name + StringPool.EQUAL + getProperty(name));
		}
	}

	@Override
	public void list(PrintWriter out) {
		System.out.println("-- listing properties --");

		Enumeration<String> enu = propertyNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			out.println(name + StringPool.EQUAL + getProperty(name));
		}
	}

	@Override
	public Enumeration<String> propertyNames() {
		return Collections.enumeration(_names);
	}

	public Object put(String key, String value) {
		if (_names.contains(key)) {
			_names.remove(key);
		}

		_names.add(key);

		return super.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		_names.remove(key);

		return super.remove(key);
	}

	@Override
	public Object setProperty(String key, String value) {
		return put(key, value);
	}

	private Set<String> _names;

}