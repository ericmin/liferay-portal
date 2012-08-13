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

package com.liferay.counter.model.impl;

import com.liferay.counter.model.Counter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Counter in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Counter
 * @generated
 */
public class CounterCacheModel implements CacheModel<Counter>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{name=");
		sb.append(name);
		sb.append(", currentId=");
		sb.append(currentId);
		sb.append("}");

		return sb.toString();
	}

	public Counter toEntityModel() {
		CounterImpl counterImpl = new CounterImpl();

		if (name == null) {
			counterImpl.setName(StringPool.BLANK);
		}
		else {
			counterImpl.setName(name);
		}

		counterImpl.setCurrentId(currentId);

		counterImpl.resetOriginalValues();

		return counterImpl;
	}

	public String name;
	public long currentId;
}