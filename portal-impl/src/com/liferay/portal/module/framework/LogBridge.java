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

package com.liferay.portal.module.framework;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Raymond Augé
 */
public class LogBridge
	implements BundleActivator, LogListener,
			   ServiceTrackerCustomizer<LogReaderService, LogReaderService> {

	public LogReaderService addingService(
		ServiceReference<LogReaderService> serviceReference) {

		LogReaderService logReaderService = _bundleContext.getService(
			serviceReference);

		logReaderService.addLogListener(this);

		return logReaderService;
	}

	public void logged(LogEntry logEntry) {
		int level = logEntry.getLevel();

		Bundle bundle = logEntry.getBundle();

		String symbolicName = StringUtil.replace(
			bundle.getSymbolicName(), StringPool.PERIOD, StringPool.UNDERLINE);

		Log log = LogFactoryUtil.getLog("osgi.logging." + symbolicName);

		String message = logEntry.getMessage();

		ServiceReference<?> serviceReference = logEntry.getServiceReference();

		if (serviceReference != null) {
			message += " " + serviceReference.toString();
		}

		if ((level == LogService.LOG_DEBUG) && log.isDebugEnabled()) {
			log.debug(message, logEntry.getException());
		}
		else if ((level == LogService.LOG_ERROR) && log.isErrorEnabled()) {
			log.error(message, logEntry.getException());
		}
		else if ((level == LogService.LOG_INFO) && log.isInfoEnabled()) {
			log.info(message, logEntry.getException());
		}
		else if ((level == LogService.LOG_WARNING) && log.isWarnEnabled()) {
			log.warn(message, logEntry.getException());
		}
	}

	public void modifiedService(
		ServiceReference<LogReaderService> serviceReference,
		LogReaderService logReaderService) {
	}

	public void removedService(
		ServiceReference<LogReaderService> serviceReference,
		LogReaderService logReaderService) {

		logReaderService.removeLogListener(this);
	}

	public void start(BundleContext bundleContext) throws Exception {
		_bundleContext = bundleContext;

		_serviceTracker =
			new ServiceTracker<LogReaderService, LogReaderService>(
				bundleContext, LogReaderService.class, this);

		_serviceTracker.open();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		_serviceTracker.close();

		_serviceTracker = null;

		_bundleContext = null;
	}

	private BundleContext _bundleContext;
	private ServiceTracker<LogReaderService, LogReaderService> _serviceTracker;

}