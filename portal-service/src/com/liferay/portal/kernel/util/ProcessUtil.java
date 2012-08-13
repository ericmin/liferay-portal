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

package com.liferay.portal.kernel.util;

import java.io.IOException;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ProcessUtil {

	public static void close(Process process) {
		try {
			process.waitFor();
		}
		catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		try {
			process.getInputStream().close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		try {
			process.getOutputStream().close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

		try {
			process.getErrorStream().close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static int getProcessId() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		String name = runtimeMXBean.getName();

		int index = name.indexOf(CharPool.AT);

		if (index == -1) {
			throw new RuntimeException("Unable to parse process name " + name);
		}

		String id = name.substring(0, index);

		try {
			return GetterUtil.getInteger(id);
		}
		catch (NumberFormatException nfe) {
			throw new RuntimeException(
				"Unable to parse process name " + name, nfe);
		}
	}

}