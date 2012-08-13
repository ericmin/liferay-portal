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

import java.util.Collections;
import java.util.Map;

/**
 * Class represents unknown device
 *
 * @author Milen Dyankov
 */
public class UnknownDevice extends AbstractDevice {

	public static UnknownDevice getInstance() {
		return _instance;
	}

	public String getBrand() {
		return VersionableName.UNKNOWN.getName();
	}

	public String getBrowser() {
		return VersionableName.UNKNOWN.getName();
	}

	public String getBrowserVersion() {
		return VersionableName.UNKNOWN.getName();
	}

	public Map<String, Capability> getCapabilities() {
		return Collections.emptyMap();
	}

	public String getCapability(String name) {
		return null;
	}

	public String getModel() {
		return VersionableName.UNKNOWN.getName();
	}

	public String getOS() {
		return VersionableName.UNKNOWN.getName();
	}

	public String getOSVersion() {
		return VersionableName.UNKNOWN.getName();
	}

	public String getPointingMethod() {
		return VersionableName.UNKNOWN.getName();
	}

	public Dimensions getScreenSize() {
		return Dimensions.UNKNOWN;
	}

	public boolean hasQwertyKeyboard() {
		return true;
	}

	public boolean isTablet() {
		return false;
	}

	private UnknownDevice() {
	}

	private static UnknownDevice _instance = new UnknownDevice();

}