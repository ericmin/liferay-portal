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

package com.liferay.portal.kernel.monitoring.statistics;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class DataSampleThreadLocal implements Cloneable {

	public static void addDataSample(DataSample dataSample) {
		if (!_monitoringDataSampleThreadLocal) {
			return;
		}

		_dataSampleThreadLocal.get()._addDataSample(dataSample);
	}

	public static void clearDataSamples() {
		_dataSampleThreadLocal.remove();
	}

	public static List<DataSample> getDataSamples() {
		if (!_monitoringDataSampleThreadLocal) {
			return Collections.emptyList();
		}

		return _dataSampleThreadLocal.get()._getDataSamples();
	}

	public static boolean isMonitoringDataSampleThreadLocal() {
		return _monitoringDataSampleThreadLocal;
	}

	public static void setMonitoringDataSampleThreadLocal(
		boolean monitoringDataSampleThreadLocal) {

		_monitoringDataSampleThreadLocal = monitoringDataSampleThreadLocal;
	}

	@Override
	public Object clone() {
		return new DataSampleThreadLocal();
	}

	public long getMonitorTime() {
		return _monitorTime;
	}

	private DataSampleThreadLocal() {
		_monitorTime = System.currentTimeMillis();
	}

	private void _addDataSample(DataSample dataSample) {
		_dataSamples.add(dataSample);
	}

	private List<DataSample> _getDataSamples() {
		return _dataSamples;
	}

	private static ThreadLocal<DataSampleThreadLocal> _dataSampleThreadLocal =
		new AutoResetThreadLocal<DataSampleThreadLocal>(
			DataSampleThreadLocal.class + "._dataSampleThreadLocal",
			new DataSampleThreadLocal());
	private static boolean _monitoringDataSampleThreadLocal;

	private List<DataSample> _dataSamples = new ArrayList<DataSample>();
	private long _monitorTime;

}