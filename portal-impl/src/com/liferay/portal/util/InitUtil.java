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

import com.liferay.portal.cache.CacheRegistryImpl;
import com.liferay.portal.configuration.ConfigurationFactoryImpl;
import com.liferay.portal.dao.db.DBFactoryImpl;
import com.liferay.portal.dao.jdbc.DataSourceFactoryImpl;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaDetector;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.log.Log4jLogFactoryImpl;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;
import com.liferay.portal.spring.util.SpringUtil;
import com.liferay.util.log4j.Log4JUtil;

import com.sun.syndication.io.XmlReader;

import java.util.List;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 */
public class InitUtil {

	public static synchronized void init() {
		if (_initialized) {
			return;
		}

		StopWatch stopWatch = null;

		if (_PRINT_TIME) {
			stopWatch = new StopWatch();

			stopWatch.start();
		}

		// Set the default locale used by Liferay. This locale is no longer set
		// at the VM level. See LEP-2584.

		String userLanguage = SystemProperties.get("user.language");
		String userCountry = SystemProperties.get("user.country");
		String userVariant = SystemProperties.get("user.variant");

		LocaleUtil.setDefault(userLanguage, userCountry, userVariant);

		// Set the default time zone used by Liferay. This time zone is no
		// longer set at the VM level. See LEP-2584.

		String userTimeZone = SystemProperties.get("user.timezone");

		TimeZoneUtil.setDefault(userTimeZone);

		// Shared class loader

		try {
			PortalClassLoaderUtil.setClassLoader(
				PACLClassLoaderUtil.getContextClassLoader());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Properties

		com.liferay.portal.kernel.util.PropsUtil.setProps(new PropsImpl());

		// Log4J

		if (GetterUtil.getBoolean(
				SystemProperties.get("log4j.configure.on.startup"), true)) {

			ClassLoader classLoader = InitUtil.class.getClassLoader();

			Log4JUtil.configureLog4J(classLoader);
		}

		// Shared log

		try {
			LogFactoryUtil.setLogFactory(new Log4jLogFactoryImpl());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Cache registry

		CacheRegistryUtil.setCacheRegistry(new CacheRegistryImpl());

		// Configuration factory

		ConfigurationFactoryUtil.setConfigurationFactory(
			new ConfigurationFactoryImpl());

		// Data source factory

		DataSourceFactoryUtil.setDataSourceFactory(new DataSourceFactoryImpl());

		// DB factory

		DBFactoryUtil.setDBFactory(new DBFactoryImpl());

		// Java properties

		JavaDetector.isJDK5();

		// ROME

		XmlReader.setDefaultEncoding(StringPool.UTF8);

		if (_PRINT_TIME) {
			System.out.println(
				"InitAction takes " + stopWatch.getTime() + " ms");
		}

		_initialized = true;
	}

	public synchronized static void initWithSpring() {
		initWithSpring(false, null);
	}

	public synchronized static void initWithSpring(boolean force) {
		initWithSpring(force, null);
	}

	public synchronized static void initWithSpring(
		boolean force, List<String> extraConfigLocations) {

		if (force) {
			_initialized = false;
		}

		if (_initialized) {
			return;
		}

		if (!_neverInitialized) {
			PropsUtil.reload();
		}
		else {
			_neverInitialized = false;
		}

		init();

		SpringUtil.loadContext(extraConfigLocations);

		_initialized = true;
	}

	public synchronized static void initWithSpring(
		List<String> extraConfigLocations) {

		initWithSpring(false, extraConfigLocations);
	}

	private static final boolean _PRINT_TIME = false;

	private static boolean _initialized;
	private static boolean _neverInitialized = true;

}