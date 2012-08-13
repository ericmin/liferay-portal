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

import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Time;

/**
 * @author Shuyang Zhou
 */
public class SchedulerEntryImpl implements SchedulerEntry {

	public String getContextPath() {
		return _contextPath;
	}

	public String getDescription() {
		return _description;
	}

	public MessageListener getEventListener() {
		return _eventListener;
	}

	public String getEventListenerClass() {
		return _eventListenerClass;
	}

	public String getPropertyKey() {
		return _propertyKey;
	}

	public TimeUnit getTimeUnit() {
		return _timeUnit;
	}

	public Trigger getTrigger() throws SchedulerException {
		if (_trigger != null) {
			return _trigger;
		}

		if (_triggerType.equals(TriggerType.CRON)) {
			_trigger = new CronTrigger(
				_eventListenerClass, _eventListenerClass, _triggerValue);
		}
		else if (_triggerType.equals(TriggerType.SIMPLE)) {
			long intervalTime = GetterUtil.getLong(_triggerValue);

			if (_timeUnit.equals(TimeUnit.DAY)) {
				intervalTime = intervalTime * Time.DAY;
			}
			else if (_timeUnit.equals(TimeUnit.HOUR)) {
				intervalTime = intervalTime * Time.HOUR;
			}
			else if (_timeUnit.equals(TimeUnit.MINUTE)) {
				intervalTime = intervalTime * Time.MINUTE;
			}
			else if (_timeUnit.equals(TimeUnit.WEEK)) {
				intervalTime = intervalTime * Time.WEEK;
			}
			else {
				intervalTime = intervalTime * Time.SECOND;
			}

			_trigger = new IntervalTrigger(
				_eventListenerClass, _eventListenerClass, intervalTime);
		}
		else {
			throw new SchedulerException(
				"Unsupport trigger type " + _triggerType);
		}

		return _trigger;
	}

	public TriggerType getTriggerType() {
		return _triggerType;
	}

	public String getTriggerValue() {
		return _triggerValue;
	}

	public void setContextPath(String contextPath) {
		_contextPath = contextPath;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setEventListener(MessageListener eventListener) {
		_eventListener = eventListener;
	}

	public void setEventListenerClass(String eventListenerClass) {
		_eventListenerClass = eventListenerClass;
	}

	public void setPropertyKey(String propertyKey) {
		_propertyKey = propertyKey;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		_timeUnit = timeUnit;
	}

	public void setTriggerType(TriggerType triggerType) {
		_triggerType = triggerType;
	}

	public void setTriggerValue(int triggerValue) {
		_triggerValue = String.valueOf(triggerValue);
	}

	public void setTriggerValue(long triggerValue) {
		_triggerValue = String.valueOf(triggerValue);
	}

	public void setTriggerValue(String triggerValue) {
		_triggerValue = triggerValue;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{contextPath=");
		sb.append(_contextPath);
		sb.append(", description=");
		sb.append(_description);
		sb.append(", eventListener=");
		sb.append(_eventListener);
		sb.append(", eventListenerClass=");
		sb.append(_eventListenerClass);
		sb.append(", propertyKey=");
		sb.append(_propertyKey);
		sb.append(", timeUnit=");
		sb.append(_timeUnit);
		sb.append(", trigger=");
		sb.append(_trigger);
		sb.append(", triggerType=");
		sb.append(_triggerType);
		sb.append(", triggerValue=");
		sb.append(_triggerValue);
		sb.append("}");

		return sb.toString();
	}

	private String _contextPath;
	private String _description;
	private transient MessageListener _eventListener;
	private String _eventListenerClass;
	private String _propertyKey;
	private TimeUnit _timeUnit;
	private Trigger _trigger;
	private TriggerType _triggerType;
	private String _triggerValue;

}