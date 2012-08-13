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

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Brian Wing Shun Chan
 */
public interface ImageTool {

	public static final String TYPE_BMP = "bmp";

	public static final String TYPE_GIF = "gif";

	public static final String TYPE_JPEG = "jpg";

	public static final String TYPE_NOT_AVAILABLE = "na";

	public static final String TYPE_PNG = "png";

	public static final String TYPE_TIFF = "tiff";

	public BufferedImage convertImageType(BufferedImage sourceImage, int type);

	public void encodeGIF(RenderedImage renderedImage, OutputStream os)
		throws IOException;

	public void encodeWBMP(RenderedImage renderedImage, OutputStream os)
		throws InterruptedException, IOException;

	public BufferedImage getBufferedImage(RenderedImage renderedImage);

	public byte[] getBytes(RenderedImage renderedImage, String contentType)
		throws IOException;

	public ImageBag read(byte[] bytes) throws IOException;

	public ImageBag read(File file) throws IOException;

	/**
	 * Scales the image based on the given width with the height calculated to
	 * preserve aspect ratio.
	 *
	 * @param  renderedImage image to scale
	 * @param  width used as new width and to calculate for new height
	 * @return scaled image
	 */
	public RenderedImage scale(RenderedImage renderedImage, int width);

	/**
	 * Scales the image based on the maximum height and width given while
	 * preserving the aspect ratio. If the image is already larger in both
	 * dimensions, the image will not be scaled.
	 *
	 * @param  renderedImage image to scale
	 * @param  maxHeight maximum height allowed for image
	 * @param  maxWidth maximum width allowed for image
	 * @return scaled image
	 */
	public RenderedImage scale(
		RenderedImage renderedImage, int maxHeight, int maxWidth);

	public abstract void write(
			RenderedImage renderedImage, String contentType, OutputStream os)
		throws IOException;

}