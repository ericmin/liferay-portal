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
import com.liferay.portal.model.Portlet;

import java.io.Serializable;

/**
 * The cache model class for representing Portlet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Portlet
 * @generated
 */
public class PortletCacheModel implements CacheModel<Portlet>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{id=");
		sb.append(id);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", portletId=");
		sb.append(portletId);
		sb.append(", roles=");
		sb.append(roles);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public Portlet toEntityModel() {
		PortletImpl portletImpl = new PortletImpl();

		portletImpl.setId(id);
		portletImpl.setCompanyId(companyId);

		if (portletId == null) {
			portletImpl.setPortletId(StringPool.BLANK);
		}
		else {
			portletImpl.setPortletId(portletId);
		}

		if (roles == null) {
			portletImpl.setRoles(StringPool.BLANK);
		}
		else {
			portletImpl.setRoles(roles);
		}

		portletImpl.setActive(active);

		portletImpl.resetOriginalValues();

		return portletImpl;
	}

	public long id;
	public long companyId;
	public String portletId;
	public String roles;
	public boolean active;
}