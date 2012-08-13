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
import com.liferay.portal.model.PortletPreferences;

import java.io.Serializable;

/**
 * The cache model class for representing PortletPreferences in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PortletPreferences
 * @generated
 */
public class PortletPreferencesCacheModel implements CacheModel<PortletPreferences>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{portletPreferencesId=");
		sb.append(portletPreferencesId);
		sb.append(", ownerId=");
		sb.append(ownerId);
		sb.append(", ownerType=");
		sb.append(ownerType);
		sb.append(", plid=");
		sb.append(plid);
		sb.append(", portletId=");
		sb.append(portletId);
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	public PortletPreferences toEntityModel() {
		PortletPreferencesImpl portletPreferencesImpl = new PortletPreferencesImpl();

		portletPreferencesImpl.setPortletPreferencesId(portletPreferencesId);
		portletPreferencesImpl.setOwnerId(ownerId);
		portletPreferencesImpl.setOwnerType(ownerType);
		portletPreferencesImpl.setPlid(plid);

		if (portletId == null) {
			portletPreferencesImpl.setPortletId(StringPool.BLANK);
		}
		else {
			portletPreferencesImpl.setPortletId(portletId);
		}

		if (preferences == null) {
			portletPreferencesImpl.setPreferences(StringPool.BLANK);
		}
		else {
			portletPreferencesImpl.setPreferences(preferences);
		}

		portletPreferencesImpl.resetOriginalValues();

		return portletPreferencesImpl;
	}

	public long portletPreferencesId;
	public long ownerId;
	public int ownerType;
	public long plid;
	public String portletId;
	public String preferences;
}