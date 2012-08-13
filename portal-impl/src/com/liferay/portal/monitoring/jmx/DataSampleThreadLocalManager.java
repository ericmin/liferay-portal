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

import com.liferay.portal.kernel.monitoring.statistics.DataSampleThreadLocal;
import com.liferay.portal.util.PropsValues;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class DataSampleThreadLocalManager
	implements DataSampleThreadLocalManagerMBean {

	public DataSampleThreadLocalManager() {
		setMonitoringDataSampleThreadLocal(
			PropsValues.MONITORING_DATA_SAMPLE_THREAD_LOCAL);
	}

	public boolean isMonitoringDataSampleThreadLocal() {
		return DataSampleThreadLocal.isMonitoringDataSampleThreadLocal();
	}

	public void setMonitoringDataSampleThreadLocal(
		boolean monitoringDataSampleThreadLocal) {

		DataSampleThreadLocal.setMonitoringDataSampleThreadLocal(
			monitoringDataSampleThreadLocal);
	}

}