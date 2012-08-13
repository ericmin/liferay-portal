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

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class SpriteProcessorUtil {

	public static Properties generate(
			ServletContext servletContext, List<File> imageFiles,
			String spriteFileName, String spritePropertiesFileName,
			String spritePropertiesRootPath, int maxHeight, int maxWidth,
			int maxSize)
		throws IOException {

		return getSpriteProcessor().generate(
			servletContext, imageFiles, spriteFileName,
			spritePropertiesFileName, spritePropertiesRootPath, maxHeight,
			maxWidth, maxSize);
	}

	public static SpriteProcessor getSpriteProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(SpriteProcessorUtil.class);

		return _spriteProcessor;
	}

	public void setSpriteProcessor(SpriteProcessor spriteProcessor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_spriteProcessor = spriteProcessor;
	}

	private static SpriteProcessor _spriteProcessor;

}