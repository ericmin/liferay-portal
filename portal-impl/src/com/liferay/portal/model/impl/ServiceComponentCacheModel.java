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
import com.liferay.portal.model.ServiceComponent;

import java.io.Serializable;

/**
 * The cache model class for representing ServiceComponent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceComponent
 * @generated
 */
public class ServiceComponentCacheModel implements CacheModel<ServiceComponent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{serviceComponentId=");
		sb.append(serviceComponentId);
		sb.append(", buildNamespace=");
		sb.append(buildNamespace);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", buildDate=");
		sb.append(buildDate);
		sb.append(", data=");
		sb.append(data);
		sb.append("}");

		return sb.toString();
	}

	public ServiceComponent toEntityModel() {
		ServiceComponentImpl serviceComponentImpl = new ServiceComponentImpl();

		serviceComponentImpl.setServiceComponentId(serviceComponentId);

		if (buildNamespace == null) {
			serviceComponentImpl.setBuildNamespace(StringPool.BLANK);
		}
		else {
			serviceComponentImpl.setBuildNamespace(buildNamespace);
		}

		serviceComponentImpl.setBuildNumber(buildNumber);
		serviceComponentImpl.setBuildDate(buildDate);

		if (data == null) {
			serviceComponentImpl.setData(StringPool.BLANK);
		}
		else {
			serviceComponentImpl.setData(data);
		}

		serviceComponentImpl.resetOriginalValues();

		return serviceComponentImpl;
	}

	public long serviceComponentId;
	public String buildNamespace;
	public long buildNumber;
	public long buildDate;
	public String data;
}