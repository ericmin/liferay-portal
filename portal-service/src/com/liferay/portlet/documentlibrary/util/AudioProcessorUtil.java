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
public class AudioProcessorUtil {

	public static void generateAudio(FileVersion fileVersion) throws Exception {
		getAudioProcessor().generateAudio(fileVersion);
	}

	public static Set<String> getAudioMimeTypes() {
		return getAudioProcessor().getAudioMimeTypes();
	}

	public static AudioProcessor getAudioProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(AudioProcessorUtil.class);

		return _audioProcessor;
	}

	public static InputStream getPreviewAsStream(
			FileVersion fileVersion, String type)
		throws Exception {

		return getAudioProcessor().getPreviewAsStream(fileVersion, type);
	}

	public static long getPreviewFileSize(FileVersion fileVersion, String type)
		throws Exception {

		return getAudioProcessor().getPreviewFileSize(fileVersion, type);
	}

	public static boolean hasAudio(FileVersion fileVersion) {
		return getAudioProcessor().hasAudio(fileVersion);
	}

	public static boolean isAudioSupported(FileVersion fileVersion) {
		return getAudioProcessor().isAudioSupported(fileVersion);
	}

	public static boolean isAudioSupported(String mimeType) {
		return getAudioProcessor().isAudioSupported(mimeType);
	}

	public static boolean isSupported(String mimeType) {
		return getAudioProcessor().isSupported(mimeType);
	}

	public static void trigger(FileVersion fileVersion) {
		getAudioProcessor().trigger(fileVersion);
	}

	public void setAudioProcessor(AudioProcessor audioProcessor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_audioProcessor = audioProcessor;
	}

	private static AudioProcessor _audioProcessor;

}