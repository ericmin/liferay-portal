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

package com.liferay.portal.monitoring.jmx;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.monitoring.statistics.service.ServerStatistics;
import com.liferay.portal.monitoring.statistics.service.ServiceMonitorAdvice;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class ServiceManager implements ServiceManagerMBean {

	public void addMonitoredClass(String className) {
		_serviceMonitorAdvice.addMonitoredClass(className);
	}

	public void addMonitoredMethod(
			String className, String methodName, String[] parameterTypes)
		throws SystemException {

		_serviceMonitorAdvice.addMonitoredMethod(
			className, methodName, parameterTypes);
	}

	public long getErrorCount(
			String className, String methodName, String[] parameterTypes)
		throws SystemException {

		return _serverStatistics.getErrorCount(
			className, methodName, parameterTypes);
	}

	public long getMaxTime(
			String className, String methodName, String[] parameterTypes)
		throws SystemException {

		return _serverStatistics.getMaxTime(
			className, methodName, parameterTypes);
	}

	public long getMinTime(
			String className, String methodName, String[] parameterTypes)
		throws SystemException {

		return _serverStatistics.getMinTime(
			className, methodName, parameterTypes);
	}

	public Set<String> getMonitoredClasses() {
		return _serviceMonitorAdvice.getMonitoredClasses();
	}

	public Set<MethodKey> getMonitoredMethods() {
		return _serviceMonitorAdvice.getMonitoredMethods();
	}

	public long getRequestCount(
			String className, String methodName, String[] parameterTypes)
		throws SystemException {

		return _serverStatistics.getRequestCount(
			className, methodName, parameterTypes);
	}

	public boolean isActive() {
		return _serviceMonitorAdvice.isActive();
	}

	public boolean isPermissiveMode() {
		return _serviceMonitorAdvice.isPermissiveMode();
	}

	public void setActive(boolean active) {
		_serviceMonitorAdvice.setActive(active);
	}

	public void setPermissiveMode(boolean permissiveMode) {
		_serviceMonitorAdvice.setPermissiveMode(permissiveMode);
	}

	public void setServerStatistics(ServerStatistics serverStatistics) {
		_serverStatistics = serverStatistics;
	}

	public void setServiceMonitorAdvice(
		ServiceMonitorAdvice serviceMonitorAdvice) {

		_serviceMonitorAdvice = serviceMonitorAdvice;
	}

	private ServerStatistics _serverStatistics;
	private ServiceMonitorAdvice _serviceMonitorAdvice;

}