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

package com.liferay.portal.kernel.ldap;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;

import java.util.Date;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

/**
 * @author Toma Bedolla
 * @author Michael Young
 * @author Brian Wing Shun Chan
 */
public class LDAPUtil {

	public static Object getAttributeObject(
			Attributes attributes, Properties properties, String key)
		throws NamingException {

		String id = properties.getProperty(key);

		return getAttributeObject(attributes, id);
	}

	public static Object getAttributeObject(
			Attributes attributes, Properties properties, String key,
			Object defaultValue)
		throws NamingException {

		String id = properties.getProperty(key);

		return getAttributeObject(attributes, id, defaultValue);
	}

	public static Object getAttributeObject(Attributes attributes, String id)
		throws NamingException {

		return getAttributeObject(attributes, id, null);
	}

	public static Object getAttributeObject(
			Attributes attributes, String id, Object defaultValue)
		throws NamingException {

		if (Validator.isNull(id)) {
			return defaultValue;
		}

		Attribute attribute = attributes.get(id);

		if (attribute == null) {
			return defaultValue;
		}

		Object object = attribute.get();

		if (object == null) {
			return defaultValue;
		}

		return object;
	}

	public static String getAttributeString(
			Attributes attributes, Properties properties, String key)
		throws NamingException {

		String id = properties.getProperty(key);

		return getAttributeString(attributes, id);
	}

	public static String getAttributeString(
			Attributes attributes, Properties properties, String key,
			String defaultValue)
		throws NamingException {

		String id = properties.getProperty(key);

		return getAttributeString(attributes, id, defaultValue);
	}

	public static String getAttributeString(Attributes attributes, String id)
		throws NamingException {

		return getAttributeString(attributes, id, StringPool.BLANK);
	}

	public static String getAttributeString(
			Attributes attributes, String id, String defaultValue)
		throws NamingException {

		if (Validator.isNull(id)) {
			return defaultValue;
		}

		Attribute attribute = attributes.get(id);

		if (attribute == null) {
			return defaultValue;
		}

		Object object = attribute.get();

		if (object == null) {
			return defaultValue;
		}

		return object.toString();
	}

	public static String[] getAttributeStringArray(
			Attributes attributes, Properties properties, String key)
		throws NamingException {

		String id = properties.getProperty(key);

		return getAttributeStringArray(attributes, id);
	}

	public static String[] getAttributeStringArray(
			Attributes attributes, String id)
		throws NamingException {

		if (Validator.isNull(id)) {
			return null;
		}

		Attribute attribute = attributes.get(id);

		if (attribute == null) {
			return null;
		}

		int size = attribute.size();

		if (size == 0) {
			return null;
		}

		String[] array = new String[size];

		for (int i = 0; i < size; i++) {
			Object object = attribute.get(i);

			if (object == null) {
				array[i] = StringPool.BLANK;
			}
			else {
				array[i] = object.toString();
			}
		}

		return array;
	}

	public static String getFullProviderURL(String baseURL, String baseDN) {
		return baseURL + StringPool.SLASH + baseDN;
	}

	public static Date parseDate(String date) throws Exception {
		String format = "yyyyMMddHHmmss";

		if (date.endsWith("Z")) {
			if (date.indexOf(CharPool.PERIOD) != -1) {
				format = "yyyyMMddHHmmss.S'Z'";
			}
			else {
				format = "yyyyMMddHHmmss'Z'";
			}
		}
		else if ((date.indexOf(CharPool.DASH) != -1) ||
				 (date.indexOf(CharPool.PLUS) != -1)) {

			if (date.indexOf(CharPool.PERIOD) != -1) {
				format = "yyyyMMddHHmmss.SZ";
			}
			else {
				format = "yyyyMMddHHmmssZ";
			}
		}
		else if (date.indexOf(CharPool.PERIOD) != -1) {
			format = "yyyyMMddHHmmss.S";
		}

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			format);

		return dateFormat.parse(date);
	}

}