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

import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactory;
import com.liferay.portal.security.pacl.PACLClassLoaderUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public class ZipReaderFactoryImpl implements ZipReaderFactory {

	public ZipReader getZipReader(File file) {
		ClassLoader portalClassLoader =
			PACLClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipReaderImpl(file);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public ZipReader getZipReader(InputStream inputStream) throws IOException {
		ClassLoader portalClassLoader =
			PACLClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			PACLClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipReaderImpl(inputStream);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				PACLClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

}