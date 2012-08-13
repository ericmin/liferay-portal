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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.util.JavaDetector;

import java.security.AccessController;

import sun.reflect.Reflection;

/**
 * @author Brian Wing Shun Chan
 */
public class CheckerUtil {

	public static boolean isAccessControllerDoPrivileged(int frame) {
		frame++;

		Class<?> callerClass = Reflection.getCallerClass(frame);

		if (callerClass != AccessController.class) {
			return false;
		}

		Thread currentThread = Thread.currentThread();

		StackTraceElement[] stackTraceElements = currentThread.getStackTrace();

		if (JavaDetector.isIBM()) {
			frame++;
		}

		StackTraceElement stackTraceElement = stackTraceElements[frame];

		String methodName = stackTraceElement.getMethodName();

		return methodName.equals(_METHOD_NAME_DO_PRIVILEGED);
	}

	private static final String _METHOD_NAME_DO_PRIVILEGED = "doPrivileged";

}