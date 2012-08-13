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

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
@MessagingProxy(mode = ProxyMode.SYNC)
public interface DeviceRecognitionProvider {

	public Device detectDevice(HttpServletRequest request);

	public KnownDevices getKnownDevices();

	public void reload() throws Exception;

}