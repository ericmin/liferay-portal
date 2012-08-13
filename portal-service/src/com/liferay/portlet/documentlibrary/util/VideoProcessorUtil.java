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

import java.util.Set;

/**
 * @author Sergio González
 */
public class VideoProcessorUtil {

	public static void generateVideo(FileVersion fileVersion) throws Exception {
		getVideoProcessor().generateVideo(fileVersion);
	}

	public static InputStream getPreviewAsStream(
			FileVersion fileVersion, String type)
		throws Exception {

		return getVideoProcessor().getPreviewAsStream(fileVersion, type);
	}

	public static long getPreviewFileSize(FileVersion fileVersion, String type)
		throws Exception {

		return getVideoProcessor().getPreviewFileSize(fileVersion, type);
	}

	public static InputStream getThumbnailAsStream(
			FileVersion fileVersion, int index)
		throws Exception {

		return getVideoProcessor().getThumbnailAsStream(fileVersion, index);
	}

	public static long getThumbnailFileSize(FileVersion fileVersion, int index)
		throws Exception {

		return getVideoProcessor().getThumbnailFileSize(fileVersion, index);
	}

	public static Set<String> getVideoMimeTypes() {
		return getVideoProcessor().getVideoMimeTypes();
	}

	public static VideoProcessor getVideoProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(VideoProcessorUtil.class);

		return _videoProcessor;
	}

	public static boolean hasVideo(FileVersion fileVersion) {
		return getVideoProcessor().hasVideo(fileVersion);
	}

	public static boolean isSupported(String mimeType) {
		return getVideoProcessor().isSupported(mimeType);
	}

	public static boolean isVideoSupported(FileVersion fileVersion) {
		return getVideoProcessor().isVideoSupported(fileVersion);
	}

	public static boolean isVideoSupported(String mimeType) {
		return getVideoProcessor().isVideoSupported(mimeType);
	}

	public static void trigger(FileVersion fileVersion) {
		getVideoProcessor().trigger(fileVersion);
	}

	public void setVideoProcessor(VideoProcessor videoProcessor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_videoProcessor = videoProcessor;
	}

	private static VideoProcessor _videoProcessor;

}