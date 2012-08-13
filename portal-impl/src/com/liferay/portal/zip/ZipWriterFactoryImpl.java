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

package com.liferay.portal.zip;

import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactory;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;

import java.io.File;

/**
 * @author Raymond Augé
 */
public class ZipWriterFactoryImpl implements ZipWriterFactory {

	public ZipWriter getZipWriter() {
		ClassLoader portalClassLoader =
			PACLClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipWriterImpl();
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public ZipWriter getZipWriter(File file) {
		ClassLoader portalClassLoader =
			PACLClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipWriterImpl(file);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

}