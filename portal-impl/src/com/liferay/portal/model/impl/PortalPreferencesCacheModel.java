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
import com.liferay.portal.model.PortalPreferences;

import java.io.Serializable;

/**
 * The cache model class for representing PortalPreferences in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferences
 * @generated
 */
public class PortalPreferencesCacheModel implements CacheModel<PortalPreferences>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{portalPreferencesId=");
		sb.append(portalPreferencesId);
		sb.append(", ownerId=");
		sb.append(ownerId);
		sb.append(", ownerType=");
		sb.append(ownerType);
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	public PortalPreferences toEntityModel() {
		PortalPreferencesImpl portalPreferencesImpl = new PortalPreferencesImpl();

		portalPreferencesImpl.setPortalPreferencesId(portalPreferencesId);
		portalPreferencesImpl.setOwnerId(ownerId);
		portalPreferencesImpl.setOwnerType(ownerType);

		if (preferences == null) {
			portalPreferencesImpl.setPreferences(StringPool.BLANK);
		}
		else {
			portalPreferencesImpl.setPreferences(preferences);
		}

		portalPreferencesImpl.resetOriginalValues();

		return portalPreferencesImpl;
	}

	public long portalPreferencesId;
	public long ownerId;
	public int ownerType;
	public String preferences;
}