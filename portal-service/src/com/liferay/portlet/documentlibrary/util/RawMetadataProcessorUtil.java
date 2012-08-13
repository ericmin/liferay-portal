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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * Document library processor responsible for the generation of raw metadata
 * associated with all of the the files stored in the document library.
 *
 * <p>
 * This processor automatically and assynchronously extracts the metadata from
 * all of the files stored in the document library. The metadata extraction is
 * done with the help of {@link
 * com.liferay.portal.metadata.TikaRawMetadataProcessor}
 * </p>
 *
 * @author Alexander Chow
 * @author Mika Koivisto
 * @author Miguel Pastor
 */
public class RawMetadataProcessorUtil {

	public static void cleanUp(FileEntry fileEntry) {
		getRawMetadataProcessor().cleanUp(fileEntry);
	}

	public static void cleanUp(FileVersion fileVersion) {
		getRawMetadataProcessor().cleanUp(fileVersion);
	}

	/**
	 * Generates the raw metadata associated with the file entry.
	 *
	 * @param  fileVersion the file version from which the raw metatada is to be
	 *         generated
	 * @throws PortalException if an error occurred in the metadata extraction
	 * @throws SystemException if a system exception occurred
	 */
	public static void generateMetadata(FileVersion fileVersion)
		throws PortalException, SystemException {

		getRawMetadataProcessor().generateMetadata(fileVersion);
	}

	public static RawMetadataProcessor getRawMetadataProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(
			RawMetadataProcessorUtil.class);

		return _rawMetadataProcessor;
	}

	public static boolean isSupported(FileVersion fileVersion) {
		return getRawMetadataProcessor().isSupported(fileVersion);
	}

	public static boolean isSupported(String mimeType) {
		return getRawMetadataProcessor().isSupported(mimeType);
	}

	/**
	 * Saves the raw metadata present in the file version.
	 *
	 * <p>
	 * The raw metadata present in the file version is extracted and persisted
	 * using {@link com.liferay.portal.metadata.TikaRawMetadataProcessor}.
	 * </p>
	 *
	 * @param  fileVersion the file version from which the raw metatada is to be
	 *         extracted and persisted
	 * @throws PortalException if an error occurred in the metadata extraction
	 * @throws SystemException if a system exception occurred
	 */
	public static void saveMetadata(FileVersion fileVersion)
		throws PortalException, SystemException {

		getRawMetadataProcessor().saveMetadata(fileVersion);
	}

	/**
	 * Launches extraction of raw metadata from the file version.
	 *
	 * <p>
	 * The raw metadata extraction is done asynchronously.
	 * </p>
	 *
	 * @param fileVersion the latest file version from which the raw metadata is
	 *        to be generated
	 */
	public static void trigger(FileVersion fileVersion) {
		getRawMetadataProcessor().trigger(fileVersion);
	}

	public void setRawMetadataProcessor(
		RawMetadataProcessor rawMetadataProcessor) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_rawMetadataProcessor = rawMetadataProcessor;
	}

	private static RawMetadataProcessor _rawMetadataProcessor;

}