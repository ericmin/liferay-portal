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

package com.liferay.portal.kernel.messaging.jmx;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationStatistics;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class DestinationStatisticsManager
	implements DestinationStatisticsManagerMBean {

	public static ObjectName createObjectName(String destinationName) {
		try {
			return new ObjectName(_OBJECT_NAME_PREFIX + destinationName);
		}
		catch (MalformedObjectNameException mone) {
			throw new IllegalStateException(mone);
		}
	}

	public DestinationStatisticsManager(Destination destination) {
		_destination = destination;
	}

	public int getActiveThreadCount() {
		if (_autoRefresh) {
			refresh();
		}

		return _destinationStatistics.getActiveThreadCount();
	}

	public int getCurrentThreadCount() {
		if (_autoRefresh || (_destinationStatistics == null)) {
			refresh();
		}

		return _destinationStatistics.getCurrentThreadCount();
	}

	public int getLargestThreadCount() {
		if (_autoRefresh || (_destinationStatistics == null)) {
			refresh();
		}

		return _destinationStatistics.getLargestThreadCount();
	}

	public String getLastRefresh() {
		return String.valueOf(_lastRefresh);
	}

	public int getMaxThreadPoolSize() {
		if (_autoRefresh || (_destinationStatistics == null)) {
			refresh();
		}

		return _destinationStatistics.getMaxThreadPoolSize();
	}

	public int getMinThreadPoolSize() {
		if (_autoRefresh || (_destinationStatistics == null)) {
			refresh();
		}

		return _destinationStatistics.getMinThreadPoolSize();
	}

	public long getPendingMessageCount() {
		if (_autoRefresh || (_destinationStatistics == null)) {
			refresh();
		}

		return _destinationStatistics.getPendingMessageCount();
	}

	public long getSentMessageCount() {
		if (_autoRefresh || (_destinationStatistics == null)) {
			refresh();
		}

		return _destinationStatistics.getSentMessageCount();
	}

	public boolean isAutoRefresh() {
		return _autoRefresh;
	}

	public void refresh() {
		if (System.currentTimeMillis() > _lastRefresh) {
			_lastRefresh = System.currentTimeMillis();
			_destinationStatistics = _destination.getDestinationStatistics();
		}
	}

	public void setAutoRefresh(boolean autoRefresh) {
		_autoRefresh = autoRefresh;
	}

	private static final String _OBJECT_NAME_PREFIX =
		"Liferay:product=Portal,type=MessagingDestinationStatistics,name=";

	private boolean _autoRefresh;
	private Destination _destination;
	private DestinationStatistics _destinationStatistics;
	private long _lastRefresh;

}