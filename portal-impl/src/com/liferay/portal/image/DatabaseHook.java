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

package com.liferay.portal.image;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.model.Image;

import java.io.InputStream;

/**
 * @author Jorge Ferrer
 */
public class DatabaseHook extends BaseHook {

	public void deleteImage(Image image) {
	}

	public byte[] getImageAsBytes(Image image) {
		return (byte[])Base64.stringToObject(image.getText());
	}

	public InputStream getImageAsStream(Image image) {
		return new UnsyncByteArrayInputStream(getImageAsBytes(image));
	}

	public void updateImage(Image image, String type, byte[] bytes) {
		image.setTextObj(bytes);
	}

}