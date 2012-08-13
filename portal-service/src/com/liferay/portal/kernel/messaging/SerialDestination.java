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
 * Destination that delivers a message to a list of message listeners one at a
 * time.
 * </p>
 *
 * @author Michael C. Han
 */
public class SerialDestination extends BaseAsyncDestination {

	public SerialDestination() {
		super();

		setWorkersCoreSize(_WORKERS_CORE_SIZE);
		setWorkersMaxSize(_WORKERS_MAX_SIZE);
	}

	/**
	 * @deprecated
	 */
	public SerialDestination(String name) {
		super(name, _WORKERS_CORE_SIZE, _WORKERS_MAX_SIZE);
	}

	@Override
	protected void dispatch(
		final Set<MessageListener> messageListeners, final Message message) {

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

					for (MessageListener messageListener : messageListeners) {
						try {
							messageListener.receive(message);
						}
						catch (MessageListenerException mle) {
							_log.error(
								"Unable to process message " + message, mle);
						}
					}
				}
				finally {
					ThreadLocalCacheManager.clearAll(Lifecycle.REQUEST);

					CentralizedThreadLocal.clearShortLivedThreadLocals();
				}
			}

		};

		threadPoolExecutor.execute(runnable);
	}

	private static final int _WORKERS_CORE_SIZE = 1;

	private static final int _WORKERS_MAX_SIZE = 1;

	private static Log _log = LogFactoryUtil.getLog(SerialDestination.class);

}