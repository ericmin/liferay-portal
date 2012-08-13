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

package com.liferay.portal.kernel.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;

import java.io.File;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class AutoDeployDir {

	public static final String DEFAULT_NAME = "defaultAutoDeployDir";

	public AutoDeployDir(
		String name, File deployDir, File destDir, long interval,
		int blacklistThreshold, List<AutoDeployListener> autoDeployListeners) {

		_name = name;
		_deployDir = deployDir;
		_destDir = destDir;
		_interval = interval;
		_blacklistThreshold = blacklistThreshold;
		_autoDeployListeners = new CopyOnWriteArrayList<AutoDeployListener>(
			autoDeployListeners);
		_inProcessFiles = new HashMap<String, IntegerWrapper>();
		_blacklistFiles = new HashSet<String>();
	}

	public int getBlacklistThreshold() {
		return _blacklistThreshold;
	}

	public File getDeployDir() {
		return _deployDir;
	}

	public File getDestDir() {
		return _destDir;
	}

	public long getInterval() {
		return _interval;
	}

	public List<AutoDeployListener> getListeners() {
		return _autoDeployListeners;
	}

	public String getName() {
		return _name;
	}

	public void registerListener(AutoDeployListener listener) {
		_autoDeployListeners.add(listener);
	}

	public void start() {
		if (!_deployDir.exists()) {
			if (_log.isInfoEnabled()) {
				_log.info("Creating missing directory " + _deployDir);
			}

			boolean created = _deployDir.mkdirs();

			if (!created) {
				_log.error("Directory " + _deployDir + " could not be created");
			}
		}

		if (_interval > 0) {
			try {
				Thread currentThread = Thread.currentThread();

				_autoDeployScanner = new AutoDeployScanner(
					currentThread.getThreadGroup(),
					AutoDeployScanner.class.getName(), this);

				_autoDeployScanner.start();

				if (_log.isInfoEnabled()) {
					_log.info("Auto deploy scanner started for " + _deployDir);
				}
			}
			catch (Exception e) {
				_log.error(e, e);

				stop();

				return;
			}
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info("Auto deploy scanning is disabled for " + _deployDir);
			}
		}
	}

	public void stop() {
		if (_autoDeployScanner != null) {
			_autoDeployScanner.pause();
		}
	}

	public void unregisterListener(AutoDeployListener autoDeployListener) {
		_autoDeployListeners.remove(autoDeployListener);
	}

	protected AutoDeploymentContext buildAutoDeploymentContext(File file) {
		AutoDeploymentContext autoDeploymentContext =
			new AutoDeploymentContext();

		autoDeploymentContext.setFile(file);

		return autoDeploymentContext;
	}

	protected void processFile(File file) {
		String fileName = file.getName();

		if (!file.canRead()) {
			_log.error("Unable to read " + fileName);

			return;
		}

		if (!file.canWrite()) {
			_log.error("Unable to write " + fileName);

			return;
		}

		if (_blacklistFiles.contains(fileName)) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skip processing of " + fileName + " because it is " +
						"blacklisted. You must restart the server to remove " +
							"the file from the blacklist.");
			}

			return;
		}

		IntegerWrapper attempt = _inProcessFiles.get(fileName);

		if (attempt == null) {
			attempt = new IntegerWrapper(1);

			_inProcessFiles.put(fileName, attempt);

			if (_log.isInfoEnabled()) {
				_log.info("Processing " + fileName);
			}
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Processing " + fileName + ". This is attempt " +
						attempt.getValue() + ".");
			}
		}

		try {
			AutoDeploymentContext autoDeploymentContext =
				buildAutoDeploymentContext(file);

			for (AutoDeployListener autoDeployListener : _autoDeployListeners) {
				autoDeployListener.deploy(autoDeploymentContext);
			}

			if (file.delete()) {
				_inProcessFiles.remove(fileName);
			}
			else {
				_log.error("Auto deploy failed to remove " + fileName);

				if (_log.isInfoEnabled()) {
					_log.info("Add " + fileName + " to the blacklist");
				}

				_blacklistFiles.add(fileName);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			attempt.increment();

			if (attempt.getValue() >= _blacklistThreshold) {
				if (_log.isInfoEnabled()) {
					_log.info("Add " + fileName + " to the blacklist");
				}

				_blacklistFiles.add(fileName);
			}
		}
	}

	protected void scanDirectory() {
		File[] files = _deployDir.listFiles();

		for (File file : files) {
			String fileName = file.getName().toLowerCase();

			if (file.isFile() &&
				(fileName.endsWith(".jar") || fileName.endsWith(".lpkg") ||
				 fileName.endsWith(".war") || fileName.endsWith(".xml") ||
				 fileName.endsWith(".zip"))) {

				processFile(file);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AutoDeployDir.class);

	private List<AutoDeployListener> _autoDeployListeners;
	private AutoDeployScanner _autoDeployScanner;
	private Set<String> _blacklistFiles;
	private int _blacklistThreshold;
	private File _deployDir;
	private File _destDir;
	private Map<String, IntegerWrapper> _inProcessFiles;
	private long _interval;
	private String _name;

}