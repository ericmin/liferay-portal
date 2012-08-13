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

import java.awt.image.RenderedImage;

/**
 * @author Brian Wing Shun Chan
 */
public class ImageBag {

	public ImageBag(RenderedImage renderedImage, String type) {
		_renderedImage = renderedImage;
		_type = type;
	}

	public RenderedImage getRenderedImage() {
		return _renderedImage;
	}

	public String getType() {
		return _type;
	}

	private RenderedImage _renderedImage;
	private String _type;

}