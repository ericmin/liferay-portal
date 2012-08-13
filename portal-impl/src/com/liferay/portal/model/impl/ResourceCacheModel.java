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
import com.liferay.portal.model.Resource;

import java.io.Serializable;

/**
 * The cache model class for representing Resource in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Resource
 * @generated
 */
public class ResourceCacheModel implements CacheModel<Resource>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{resourceId=");
		sb.append(resourceId);
		sb.append(", codeId=");
		sb.append(codeId);
		sb.append(", primKey=");
		sb.append(primKey);
		sb.append("}");

		return sb.toString();
	}

	public Resource toEntityModel() {
		ResourceImpl resourceImpl = new ResourceImpl();

		resourceImpl.setResourceId(resourceId);
		resourceImpl.setCodeId(codeId);

		if (primKey == null) {
			resourceImpl.setPrimKey(StringPool.BLANK);
		}
		else {
			resourceImpl.setPrimKey(primKey);
		}

		resourceImpl.resetOriginalValues();

		return resourceImpl;
	}

	public long resourceId;
	public long codeId;
	public String primKey;
}