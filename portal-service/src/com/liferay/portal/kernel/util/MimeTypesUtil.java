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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;
import java.io.InputStream;

import java.util.Set;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class MimeTypesUtil {

	public static String getContentType(File file) {
		return getMimeTypes().getContentType(file);
	}

	public static String getContentType(File file, String fileName) {
		return getMimeTypes().getContentType(file, fileName);
	}

	/**
	 * Determine the content type from an input stream and file name.
	 *
	 * @param  fileName full name or extension of file (e.g., "Test.doc",
	 *         ".doc")
	 * @return content type if it is a supported format or an empty string if it
	 *         is an unsupported format
	 */
	public static String getContentType(
		InputStream inputStream, String fileName) {

		return getMimeTypes().getContentType(inputStream, fileName);
	}

	/**
	 * Determine the content type from a file name.
	 *
	 * @param  fileName full name or extension of file (e.g., "Test.doc",
	 *         ".doc")
	 * @return content type if it is a supported format or an empty string if it
	 *         is an unsupported format
	 */
	public static String getContentType(String fileName) {
		return getMimeTypes().getContentType(fileName);
	}

	/**
	 * Determine the possible file extensions for a given content type.
	 *
	 * @param  contentType content type of file (e.g., "image/jpeg")
	 * @return set of extensions if it is a known content type or an empty set
	 *         if it is an unknown content type
	 */
	public static Set<String> getExtensions(String contentType) {
		return getMimeTypes().getExtensions(contentType);
	}

	public static MimeTypes getMimeTypes() {
		PortalRuntimePermission.checkGetBeanProperty(MimeTypesUtil.class);

		return _mimeTypes;
	}

	public void setMimeTypes(MimeTypes mimeTypes) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_mimeTypes = mimeTypes;
	}

	private static MimeTypes _mimeTypes;

}