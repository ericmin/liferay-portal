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

package com.liferay.portal.monitoring.statistics.service;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleProcessor;
import com.liferay.portal.kernel.monitoring.statistics.RequestStatistics;
import com.liferay.portal.kernel.util.MethodKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class ServiceStatistics
	implements DataSampleProcessor<ServiceRequestDataSample> {

	public ServiceStatistics(String className) {
		_className = className;
	}

	public long getAverageTime(String methodName, String[] parameterTypes)
		throws SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				_className, methodName, parameterTypes);

			RequestStatistics requestStatistics = _methodRequestStatistics.get(
				methodKey);

			if (requestStatistics != null) {
				return requestStatistics.getAverageTime();
			}
		}
		catch (ClassNotFoundException cnfe) {
			throw new SystemException(cnfe);
		}

		return -1;
	}

	public long getErrorCount(String methodName, String[] parameterTypes)
		throws SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				_className, methodName, parameterTypes);

			RequestStatistics requestStatistics = _methodRequestStatistics.get(
				methodKey);

			if (requestStatistics != null) {
				return requestStatistics.getErrorCount();
			}
		}
		catch (ClassNotFoundException cnfe) {
			throw new SystemException(cnfe);
		}

		return -1;
	}

	public long getMaxTime(String methodName, String[] parameterTypes)
		throws SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				_className, methodName, parameterTypes);

			RequestStatistics requestStatistics = _methodRequestStatistics.get(
				methodKey);

			if (requestStatistics != null) {
				return requestStatistics.getMaxTime();
			}
		}
		catch (ClassNotFoundException cnfe) {
			throw new SystemException(cnfe);
		}

		return -1;
	}

	public long getMinTime(String methodName, String[] parameterTypes)
		throws SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				_className, methodName, parameterTypes);

			RequestStatistics requestStatistics = _methodRequestStatistics.get(
				methodKey);

			if (requestStatistics != null) {
				return requestStatistics.getMinTime();
			}
		}
		catch (ClassNotFoundException cnfe) {
			throw new SystemException(cnfe);
		}

		return -1;
	}

	public long getRequestCount(String methodName, String[] parameterTypes)
		throws SystemException {

		try {
			MethodKey methodKey = new MethodKey(
				_className, methodName, parameterTypes);

			RequestStatistics requestStatistics = _methodRequestStatistics.get(
				methodKey);

			if (requestStatistics != null) {
				return requestStatistics.getRequestCount();
			}
		}
		catch (ClassNotFoundException cnfe) {
			throw new SystemException(cnfe);
		}

		return -1;
	}

	public void processDataSample(
		ServiceRequestDataSample serviceRequestDataSample) {

		MethodKey methodKey = serviceRequestDataSample.getMethodKey();

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodKey);

		if (requestStatistics == null) {
			requestStatistics = new RequestStatistics(methodKey.toString());

			_methodRequestStatistics.put(methodKey, requestStatistics);
		}

		RequestStatus requestStatus =
			serviceRequestDataSample.getRequestStatus();

		if (requestStatus == RequestStatus.ERROR) {
			requestStatistics.incrementError();
		}
		else if (requestStatus == RequestStatus.TIMEOUT) {
			requestStatistics.incrementTimeout();
		}
		else if (requestStatus == RequestStatus.SUCCESS) {
			requestStatistics.incrementSuccessDuration(
				serviceRequestDataSample.getDuration());
		}
	}

	private String _className;
	private Map<MethodKey, RequestStatistics> _methodRequestStatistics =
		new ConcurrentHashMap<MethodKey, RequestStatistics>();

}