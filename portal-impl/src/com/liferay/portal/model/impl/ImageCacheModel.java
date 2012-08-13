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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Image;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Image in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Image
 * @generated
 */
public class ImageCacheModel implements CacheModel<Image>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{imageId=");
		sb.append(imageId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", text=");
		sb.append(text);
		sb.append(", type=");
		sb.append(type);
		sb.append(", height=");
		sb.append(height);
		sb.append(", width=");
		sb.append(width);
		sb.append(", size=");
		sb.append(size);
		sb.append("}");

		return sb.toString();
	}

	public Image toEntityModel() {
		ImageImpl imageImpl = new ImageImpl();

		imageImpl.setImageId(imageId);

		if (modifiedDate == Long.MIN_VALUE) {
			imageImpl.setModifiedDate(null);
		}
		else {
			imageImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (text == null) {
			imageImpl.setText(StringPool.BLANK);
		}
		else {
			imageImpl.setText(text);
		}

		if (type == null) {
			imageImpl.setType(StringPool.BLANK);
		}
		else {
			imageImpl.setType(type);
		}

		imageImpl.setHeight(height);
		imageImpl.setWidth(width);
		imageImpl.setSize(size);

		imageImpl.resetOriginalValues();

		return imageImpl;
	}

	public long imageId;
	public long modifiedDate;
	public String text;
	public String type;
	public int height;
	public int width;
	public int size;
}