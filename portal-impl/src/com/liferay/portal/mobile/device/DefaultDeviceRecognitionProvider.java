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

package com.liferay.portal.mobile.device;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;
import com.liferay.portal.kernel.mobile.device.NoKnownDevices;
import com.liferay.portal.kernel.mobile.device.UnknownDevice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Milen Dyankov
 */
public class DefaultDeviceRecognitionProvider
	implements DeviceRecognitionProvider {

	public Device detectDevice(HttpServletRequest request) {
		if (_log.isWarnEnabled()) {
			_log.warn("Device recognition provider is not available");
		}

		return UnknownDevice.getInstance();
	}

	public KnownDevices getKnownDevices() {
		if (_log.isWarnEnabled()) {
			_log.warn("Device recognition provider is not available");
		}

		return NoKnownDevices.getInstance();
	}

	public void reload() {
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultDeviceRecognitionProvider.class);

}