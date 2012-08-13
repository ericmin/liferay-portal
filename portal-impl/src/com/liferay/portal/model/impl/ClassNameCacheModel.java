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
import com.liferay.portal.model.ClassName;

import java.io.Serializable;

/**
 * The cache model class for representing ClassName in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ClassName
 * @generated
 */
public class ClassNameCacheModel implements CacheModel<ClassName>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{classNameId=");
		sb.append(classNameId);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	public ClassName toEntityModel() {
		ClassNameImpl classNameImpl = new ClassNameImpl();

		classNameImpl.setClassNameId(classNameId);

		if (value == null) {
			classNameImpl.setValue(StringPool.BLANK);
		}
		else {
			classNameImpl.setValue(value);
		}

		classNameImpl.resetOriginalValues();

		return classNameImpl;
	}

	public long classNameId;
	public String value;
}