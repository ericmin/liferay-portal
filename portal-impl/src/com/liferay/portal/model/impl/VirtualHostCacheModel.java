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
import com.liferay.portal.model.VirtualHost;

import java.io.Serializable;

/**
 * The cache model class for representing VirtualHost in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHost
 * @generated
 */
public class VirtualHostCacheModel implements CacheModel<VirtualHost>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{virtualHostId=");
		sb.append(virtualHostId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", layoutSetId=");
		sb.append(layoutSetId);
		sb.append(", hostname=");
		sb.append(hostname);
		sb.append("}");

		return sb.toString();
	}

	public VirtualHost toEntityModel() {
		VirtualHostImpl virtualHostImpl = new VirtualHostImpl();

		virtualHostImpl.setVirtualHostId(virtualHostId);
		virtualHostImpl.setCompanyId(companyId);
		virtualHostImpl.setLayoutSetId(layoutSetId);

		if (hostname == null) {
			virtualHostImpl.setHostname(StringPool.BLANK);
		}
		else {
			virtualHostImpl.setHostname(hostname);
		}

		virtualHostImpl.resetOriginalValues();

		return virtualHostImpl;
	}

	public long virtualHostId;
	public long companyId;
	public long layoutSetId;
	public String hostname;
}