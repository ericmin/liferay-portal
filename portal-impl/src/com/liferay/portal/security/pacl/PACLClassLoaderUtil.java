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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.security.lang.PortalSecurityManagerThreadLocal;

/**
 * @author Raymond Augé
 */
public class PACLClassLoaderUtil {

	public static ClassLoader getClassLoader(Class<?> clazz) {
		boolean checkGetClassLoader =
			PortalSecurityManagerThreadLocal.isCheckGetClassLoader();

		try {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(false);

			return clazz.getClassLoader();
		}
		finally {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(
				checkGetClassLoader);
		}
	}

	public static ClassLoader getContextClassLoader() {
		boolean checkGetClassLoader =
			PortalSecurityManagerThreadLocal.isCheckGetClassLoader();

		try {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(false);

			Thread thread = Thread.currentThread();

			return thread.getContextClassLoader();
		}
		finally {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(
				checkGetClassLoader);
		}
	}

	public static ClassLoader getPortalClassLoader() {
		boolean checkGetClassLoader =
			PortalSecurityManagerThreadLocal.isCheckGetClassLoader();

		try {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(false);

			return PortalClassLoaderUtil.getClassLoader();
		}
		finally {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(
				checkGetClassLoader);
		}
	}

	public static void setContextClassLoader(ClassLoader classLoader) {
		boolean checkGetClassLoader =
			PortalSecurityManagerThreadLocal.isCheckGetClassLoader();

		try {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(false);

			Thread thread = Thread.currentThread();

			thread.setContextClassLoader(classLoader);
		}
		finally {
			PortalSecurityManagerThreadLocal.setCheckGetClassLoader(
				checkGetClassLoader);
		}
	}

}