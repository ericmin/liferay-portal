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

package org.slf4j.impl;

import com.liferay.util.sl4fj.LiferayLoggerFactory;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * @author Michael C. Han
 */
public class StaticLoggerBinder implements LoggerFactoryBinder {

	public static final String REQUESTED_API_VERSION = "1.6.4";

	public static final StaticLoggerBinder getSingleton() {
		return _SINGLETON;
	}

	public ILoggerFactory getLoggerFactory() {
		return _iLoggerFactory;
	}

	public String getLoggerFactoryClassStr() {
		return _LOGGER_FACTORY_CLASS_NAME;
	}

	private StaticLoggerBinder() {
		_iLoggerFactory = new LiferayLoggerFactory();
	}

	private static final String _LOGGER_FACTORY_CLASS_NAME =
		LiferayLoggerFactory.class.getName();

	private static final StaticLoggerBinder _SINGLETON =
		new StaticLoggerBinder();

	private ILoggerFactory _iLoggerFactory;

}