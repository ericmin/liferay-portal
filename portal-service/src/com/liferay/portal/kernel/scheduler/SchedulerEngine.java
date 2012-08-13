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

package com.liferay.portal.kernel.scheduler;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.util.List;

/**
 * @author Michael C. Han
 * @author Bruno Farache
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public interface SchedulerEngine {

	public static final String AUDIT_ACTION = "AUDIT_ACTION";

	public static final String CONTEXT_PATH = "CONTEXT_PATH";

	public static final String DESCRIPTION = "DESCRIPTION";

	public static final int DESCRIPTION_MAX_LENGTH = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.SCHEDULER_DESCRIPTION_MAX_LENGTH));

	public static final String DESTINATION_NAME = "DESTINATION_NAME";

	public static final String DISABLE = "DISABLE";

	public static final String END_TIME = "END_TIME";

	public static final String EXCEPTIONS_MAX_SIZE = "EXCEPTIONS_MAX_SIZE";

	public static final String FINAL_FIRE_TIME = "FINAL_FIRE_TIME";

	public static final String GROUP_NAME = "GROUP_NAME";

	public static final int GROUP_NAME_MAX_LENGTH = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.SCHEDULER_GROUP_NAME_MAX_LENGTH));

	public static final String JOB_NAME = "JOB_NAME";

	public static final int JOB_NAME_MAX_LENGTH = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.SCHEDULER_JOB_NAME_MAX_LENGTH));

	public static final String JOB_STATE = "JOB_STATE";

	public static final String LANGUAGE = "LANGUAGE";

	public static final String MESSAGE = "MESSAGE";

	public static final String MESSAGE_LISTENER_CLASS_NAME =
		"MESSAGE_LISTENER_CLASS_NAME";

	public static final String MESSAGE_LISTENER_UUID = "MESSAGE_LISTENER_UUID";

	public static final String NEXT_FIRE_TIME = "NEXT_FIRE_TIME";

	public static final String PORTLET_ID = "PORTLET_ID";

	public static final String PREVIOUS_FIRE_TIME = "PREVIOUS_FIRE_TIME";

	public static final String RECEIVER_KEY = "RECEIVER_KEY";

	public static final String SCRIPT = "SCRIPT";

	public static final String START_TIME = "START_TIME";

	public static final String STORAGE_TYPE = "STORAGE_TYPE";

	public void delete(String groupName) throws SchedulerException;

	public void delete(String jobName, String groupName)
		throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public SchedulerResponse getScheduledJob(String jobName, String groupName)
		throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public List<SchedulerResponse> getScheduledJobs()
		throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public List<SchedulerResponse> getScheduledJobs(String groupName)
		throws SchedulerException;

	public void pause(String groupName) throws SchedulerException;

	public void pause(String jobName, String groupName)
		throws SchedulerException;

	public void resume(String groupName) throws SchedulerException;

	public void resume(String jobName, String groupName)
		throws SchedulerException;

	public void schedule(
			Trigger trigger, String description, String destinationName,
			Message message)
		throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void shutdown() throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void start() throws SchedulerException;

	public void suppressError(String jobName, String groupName)
		throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void unschedule(String groupName) throws SchedulerException;

	@MessagingProxy(mode = ProxyMode.SYNC)
	public void unschedule(String jobName, String groupName)
		throws SchedulerException;

	public void update(Trigger trigger) throws SchedulerException;

}