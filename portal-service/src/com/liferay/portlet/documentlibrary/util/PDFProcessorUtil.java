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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.InputStream;

import java.util.Properties;

/**
 * @author Sergio González
 */
public class PDFProcessorUtil {

	public static void generateImages(FileVersion fileVersion)
		throws Exception {

		getPDFProcessor().generateImages(fileVersion);
	}

	public static String getGlobalSearchPath() throws Exception {
		return getPDFProcessor().getGlobalSearchPath();
	}

	public static PDFProcessor getPDFProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(PDFProcessorUtil.class);

		return _pdfProcessor;
	}

	public static InputStream getPreviewAsStream(
			FileVersion fileVersion, int index)
		throws Exception {

		return getPDFProcessor().getPreviewAsStream(fileVersion, index);
	}

	public static int getPreviewFileCount(FileVersion fileVersion) {
		return getPDFProcessor().getPreviewFileCount(fileVersion);
	}

	public static long getPreviewFileSize(FileVersion fileVersion, int index)
		throws Exception {

		return getPDFProcessor().getPreviewFileSize(fileVersion, index);
	}

	public static Properties getResourceLimitsProperties() throws Exception {
		return getPDFProcessor().getResourceLimitsProperties();
	}

	public static InputStream getThumbnailAsStream(
			FileVersion fileVersion, int index)
		throws Exception {

		return getPDFProcessor().getThumbnailAsStream(fileVersion, index);
	}

	public static long getThumbnailFileSize(FileVersion fileVersion, int index)
		throws Exception {

		return getPDFProcessor().getThumbnailFileSize(fileVersion, index);
	}

	public static boolean hasImages(FileVersion fileVersion) {
		return getPDFProcessor().hasImages(fileVersion);
	}

	public static boolean isDocumentSupported(FileVersion fileVersion) {
		return getPDFProcessor().isDocumentSupported(fileVersion);
	}

	public static boolean isDocumentSupported(String mimeType) {
		return getPDFProcessor().isDocumentSupported(mimeType);
	}

	public static boolean isImageMagickEnabled() throws Exception {
		return getPDFProcessor().isImageMagickEnabled();
	}

	public static boolean isSupported(String mimeType) {
		return getPDFProcessor().isSupported(mimeType);
	}

	public static void reset() throws Exception {
		getPDFProcessor().reset();
	}

	public static void trigger(FileVersion fileVersion) {
		getPDFProcessor().trigger(fileVersion);
	}

	public void setPDFProcessor(PDFProcessor pdfProcessor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_pdfProcessor = pdfProcessor;
	}

	private static PDFProcessor _pdfProcessor;

}