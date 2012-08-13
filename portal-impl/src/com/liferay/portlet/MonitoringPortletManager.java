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

package com.liferay.portlet;

/**
 * @author Michael C. Han
 */
public class MonitoringPortletManager implements MonitoringPortletManagerMBean {

	public boolean isMonitoringPortletActionRequest() {
		return MonitoringPortlet.isMonitoringPortletActionRequest();
	}

	public boolean isMonitoringPortletEventRequest() {
		return MonitoringPortlet.isMonitoringPortletEventRequest();
	}

	public boolean isMonitoringPortletRenderRequest() {
		return MonitoringPortlet.isMonitoringPortletRenderRequest();
	}

	public boolean isMonitoringPortletResourceRequest() {
		return MonitoringPortlet.isMonitoringPortletResourceRequest();
	}

	public void setMonitoringPortletActionRequest(
		boolean monitoringPortletActionRequest) {

		MonitoringPortlet.setMonitoringPortletActionRequest(
			monitoringPortletActionRequest);
	}

	public void setMonitoringPortletEventRequest(
		boolean monitoringPortletEventRequest) {

		MonitoringPortlet.setMonitoringPortletEventRequest(
			monitoringPortletEventRequest);
	}

	public void setMonitoringPortletRenderRequest(
		boolean monitoringPortletRenderRequest) {

		MonitoringPortlet.setMonitoringPortletRenderRequest(
			monitoringPortletRenderRequest);
	}

	public void setMonitoringPortletResourceRequest(
		boolean monitoringPortletResourceRequest) {

		MonitoringPortlet.setMonitoringPortletResourceRequest(
			monitoringPortletResourceRequest);
	}

}