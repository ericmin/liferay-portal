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

package com.liferay.portal.kernel.messaging;

import com.liferay.portal.kernel.cache.Lifecycle;
import com.liferay.portal.kernel.cache.ThreadLocalCacheManager;
import com.liferay.portal.kernel.cluster.ClusterLinkUtil;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.proxy.MessageValuesThreadLocal;
import com.liferay.portal.kernel.util.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalThreadLocal;

import java.util.Set;

/**
 * <p>
 * Destination that delivers a message to a list of message listeners in
 * parallel.
 * </p>
 *
 * @author Michael C. Han
 */
public class ParallelDestination extends BaseAsyncDestination {

	public ParallelDestination() {
	}

	/**
	 * @deprecated
	 */
	public ParallelDestination(String name) {
		super(name);
	}

	/**
	 * @deprecated
	 */
	public ParallelDestination(
		String name, int workersCoreSize, int workersMaxSize) {

		super(name, workersCoreSize, workersMaxSize);
	}

	@Override
	protected void dispatch(
		Set<MessageListener> messageListeners, final Message message) {

		if (!message.contains("companyId")) {
			message.put("companyId", CompanyThreadLocal.getCompanyId());
		}

		if (!message.contains("principalName")) {
			message.put("principalName", PrincipalThreadLocal.getName());
		}

		if (!message.contains("principalPassword")) {
			message.put(
				"principalPassword", PrincipalThreadLocal.getPassword());
		}

		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

		for (final MessageListener messageListener : messageListeners) {
			Runnable runnable = new MessageRunnable(message) {

				public void run() {
					try {
						long messageCompanyId = message.getLong("companyId");

						if (messageCompanyId > 0) {
							CompanyThreadLocal.setCompanyId(messageCompanyId);
						}

						String messagePrincipalName = message.getString(
							"principalName");

						if (Validator.isNotNull(messagePrincipalName)) {
							PrincipalThreadLocal.setName(messagePrincipalName);
						}

						String messagePrincipalPassword = message.getString(
							"principalPassword");

						if (Validator.isNotNull(messagePrincipalPassword)) {
							PrincipalThreadLocal.setPassword(
								messagePrincipalPassword);
						}

						Boolean clusterForwardMessage = (Boolean)message.get(
							ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE);

						if (clusterForwardMessage != null) {
							MessageValuesThreadLocal.setValue(
								ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE,
								clusterForwardMessage);
						}

						messageListener.receive(message);
					}
					catch (MessageListenerException mle) {
						_log.error("Unable to process message " + message, mle);
					}
					finally {
						ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);

						CentralizedThreadLocal.clearShortLivedThreadLocals();
					}
				}

			};

			threadPoolExecutor.execute(runnable);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ParallelDestination.class);

}