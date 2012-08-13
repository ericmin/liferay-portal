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
import com.liferay.portal.model.ResourceCode;

import java.io.Serializable;

/**
 * The cache model class for representing ResourceCode in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ResourceCode
 * @generated
 */
public class ResourceCodeCacheModel implements CacheModel<ResourceCode>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{codeId=");
		sb.append(codeId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", scope=");
		sb.append(scope);
		sb.append("}");

		return sb.toString();
	}

	public ResourceCode toEntityModel() {
		ResourceCodeImpl resourceCodeImpl = new ResourceCodeImpl();

		resourceCodeImpl.setCodeId(codeId);
		resourceCodeImpl.setCompanyId(companyId);

		if (name == null) {
			resourceCodeImpl.setName(StringPool.BLANK);
		}
		else {
			resourceCodeImpl.setName(name);
		}

		resourceCodeImpl.setScope(scope);

		resourceCodeImpl.resetOriginalValues();

		return resourceCodeImpl;
	}

	public long codeId;
	public long companyId;
	public String name;
	public int scope;
}