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

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class ImageToolUtil {

	public static BufferedImage convertImageType(
		BufferedImage sourceImage, int type) {

		return getImageTool().convertImageType(sourceImage, type);
	}

	public static void encodeGIF(RenderedImage renderedImage, OutputStream os)
		throws IOException {

		getImageTool().encodeGIF(renderedImage, os);
	}

	public static void encodeWBMP(RenderedImage renderedImage, OutputStream os)
		throws InterruptedException, IOException {

		getImageTool().encodeWBMP(renderedImage, os);
	}

	public static BufferedImage getBufferedImage(RenderedImage renderedImage) {
		return getImageTool().getBufferedImage(renderedImage);
	}

	public static byte[] getBytes(
			RenderedImage renderedImage, String contentType)
		throws IOException {

		return getImageTool().getBytes(renderedImage, contentType);
	}

	public static ImageTool getImageTool() {
		PortalRuntimePermission.checkGetBeanProperty(ImageToolUtil.class);

		return _imageTool;
	}

	public static ImageBag read(byte[] bytes) throws IOException {
		return getImageTool().read(bytes);
	}

	public static ImageBag read(File file) throws IOException {
		return getImageTool().read(file);
	}

	public static RenderedImage scale(RenderedImage renderedImage, int width) {
		return getImageTool().scale(renderedImage, width);
	}

	public static RenderedImage scale(
		RenderedImage renderedImage, int maxHeight, int maxWidth) {

		return getImageTool().scale(renderedImage, maxHeight, maxWidth);
	}

	public static void write(
			RenderedImage renderedImage, String contentType, OutputStream os)
		throws IOException {

		getImageTool().write(renderedImage, contentType, os);
	}

	public void setImageTool(ImageTool imageTool) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_imageTool = imageTool;
	}

	private static ImageTool _imageTool;

}