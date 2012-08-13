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

package com.liferay.portal.servlet.filters.monitoring;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleThreadLocal;
import com.liferay.portal.monitoring.statistics.portal.PortalRequestDataSample;
import com.liferay.portal.servlet.filters.BasePortalFilter;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rajesh Thiagarajan
 * @author Michael C. Han
 */
public class MonitoringFilter extends BasePortalFilter {

	public static boolean isMonitoringPortalRequest() {
		return _monitoringPortalRequest;
	}

	public static void setMonitoringPortalRequest(
		boolean monitoringPortalRequest) {

		_monitoringPortalRequest = monitoringPortalRequest;
	}

	@Override
	public boolean isFilterEnabled() {
		if (!super.isFilterEnabled()) {
			return false;
		}

		if (!_monitoringPortalRequest) {
			return false;
		}

		return true;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws IOException, ServletException {

		long companyId = PortalUtil.getCompanyId(request);

		PortalRequestDataSample portalRequestDataSample =
			new PortalRequestDataSample(
				companyId, request.getRemoteUser(), request.getRequestURI(),
				request.getRequestURL().toString());

		try {
			portalRequestDataSample.prepare();

			processFilter(
				MonitoringFilter.class, request, response, filterChain);

			portalRequestDataSample.capture(RequestStatus.SUCCESS);
		}
		catch (Exception e) {
			portalRequestDataSample.capture(RequestStatus.ERROR);

			if (e instanceof IOException) {
				throw (IOException)e;
			}
			else if (e instanceof ServletException) {
				throw (ServletException)e;
			}
			else {
				throw new ServletException("Unable to execute request", e);
			}
		}
		finally {
			MessageBusUtil.sendMessage(
				DestinationNames.MONITORING, portalRequestDataSample);

			DataSampleThreadLocal.addDataSample(portalRequestDataSample);
		}
	}

	private static boolean _monitoringPortalRequest =
		PropsValues.MONITORING_PORTAL_REQUEST;

}