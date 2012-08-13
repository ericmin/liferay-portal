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

package com.liferay.portal.kernel.scheduler.config;

import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;

import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public interface SchedulingConfigurator {

	public void destroy();

	public void init();

	public void setMessageBus(MessageBus messageBus);

	public void setSchedulerEngine(SchedulerEngine schedulerEngine);

	public void setSchedulerEntries(
		Map<String, List<SchedulerEntry>> schedulerEntries);

}