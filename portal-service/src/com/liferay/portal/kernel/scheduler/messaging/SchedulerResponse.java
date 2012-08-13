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

package com.liferay.portal.kernel.scheduler.messaging;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;

import java.io.Serializable;

/**
 * @author Tina Tian
 */
public class SchedulerResponse implements Serializable {

	public String getDescription() {
		return _description;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public String getGroupName() {
		if (_trigger != null) {
			return _trigger.getGroupName();
		}

		return _groupName;
	}

	public String getJobName() {
		if (_trigger != null) {
			return _trigger.getJobName();
		}

		return _jobName;
	}

	public Message getMessage() {
		return _message;
	}

	public StorageType getStorageType() {
		return _storageType;
	}

	public Trigger getTrigger() {
		return _trigger;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setGroupName(String groupName) {
		_groupName = groupName;
	}

	public void setJobName(String jobName) {
		_jobName = jobName;
	}

	public void setMessage(Message message) {
		_message = message;
	}

	public void setStorageType(StorageType storageType) {
		_storageType = storageType;
	}

	public void setTrigger(Trigger trigger) {
		_trigger = trigger;
	}

	private String _description;
	private String _destinationName;
	private String _groupName;
	private String _jobName;
	private Message _message;
	private StorageType _storageType;
	private Trigger _trigger;

}