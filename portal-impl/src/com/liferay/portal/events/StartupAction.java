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

package com.liferay.portal.events;

import com.liferay.portal.jericho.CachedLoggerProvider;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.freemarker.FreeMarkerEngineUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.messaging.sender.SynchronousMessageSender;
import com.liferay.portal.kernel.scheduler.SchedulerEngineUtil;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.servlet.JspFactorySwapper;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.velocity.VelocityEngineUtil;
import com.liferay.portal.plugin.PluginPackageIndexer;
import com.liferay.portal.security.lang.PortalSecurityManager;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portal.tools.DBUpgrader;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.messageboards.util.MBIndexer;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(ids);
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(String[] ids) throws Exception {

		// Print release information

		System.out.println("Starting " + ReleaseInfo.getReleaseInfo());

		// Clear locks

		if (_log.isDebugEnabled()) {
			_log.debug("Clear locks");
		}

		try {
			LockLocalServiceUtil.clear();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to clear locks because Lock table does not exist");
			}
		}

		// Shutdown hook

		if (_log.isDebugEnabled()) {
			_log.debug("Add shutdown hook");
		}

		Runtime runtime = Runtime.getRuntime();

		runtime.addShutdownHook(new Thread(new ShutdownHook()));

		// Security manager

		String portalSecurityManagerStrategy =
			PropsValues.PORTAL_SECURITY_MANAGER_STRATEGY;

		if (portalSecurityManagerStrategy.equals("smart")) {
			if (ServerDetector.isWebSphere()) {
				portalSecurityManagerStrategy = "none";
			}
			else {
				portalSecurityManagerStrategy = "default";
			}
		}

		if (portalSecurityManagerStrategy.equals("liferay")) {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new PortalSecurityManager());
			}
		}
		else if (portalSecurityManagerStrategy.equals("none")) {
			System.setSecurityManager(null);
		}

		// FreeMarker

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize FreeMarker engine");
		}

		FreeMarkerEngineUtil.init();

		// Velocity

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize Velocity engine");
		}

		VelocityEngineUtil.init();

		// Indexers

		IndexerRegistryUtil.register(new MBIndexer());
		IndexerRegistryUtil.register(new PluginPackageIndexer());

		// Upgrade

		if (_log.isDebugEnabled()) {
			_log.debug("Upgrade database");
		}

		DBUpgrader.upgrade();

		// Messaging

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize message bus");
		}

		MessageBus messageBus = (MessageBus)PortalBeanLocatorUtil.locate(
			MessageBus.class.getName());
		MessageSender messageSender =
			(MessageSender)PortalBeanLocatorUtil.locate(
				MessageSender.class.getName());
		SynchronousMessageSender synchronousMessageSender =
			(SynchronousMessageSender)PortalBeanLocatorUtil.locate(
				SynchronousMessageSender.class.getName());

		MessageBusUtil.init(
			messageBus, messageSender, synchronousMessageSender);

		// Cluster executor

		ClusterExecutorUtil.initialize();

		// Scheduler

		if (_log.isDebugEnabled()) {
			_log.debug("Initialize scheduler engine lifecycle");
		}

		SchedulerEngineUtil.initialize();

		// Verify

		if (_log.isDebugEnabled()) {
			_log.debug("Verify database");
		}

		DBUpgrader.verify();

		// Liferay JspFactory

		JspFactorySwapper.swap();

		// Jericho

		CachedLoggerProvider.install();
	}

	private static Log _log = LogFactoryUtil.getLog(StartupAction.class);

}