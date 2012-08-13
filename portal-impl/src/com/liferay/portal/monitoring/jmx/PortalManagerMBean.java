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

import com.liferay.portal.kernel.monitoring.MonitoringException;
import com.liferay.portal.kernel.monitoring.statistics.SummaryStatistics;

/**
 * @author Karthik Sudarshan
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public interface PortalManagerMBean extends SummaryStatistics {

	public long[] getCompanyIds() throws MonitoringException;

	public long getUptime(long companyId) throws MonitoringException;

	public long getUptime(String companyWebId) throws MonitoringException;

	public String[] getWebIds() throws MonitoringException;

	public void reset();

	public void reset(long companyId);

	public void reset(String webId);

}