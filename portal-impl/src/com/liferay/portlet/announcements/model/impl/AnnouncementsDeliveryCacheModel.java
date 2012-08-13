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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.announcements.model.AnnouncementsDelivery;

import java.io.Serializable;

/**
 * The cache model class for representing AnnouncementsDelivery in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsDelivery
 * @generated
 */
public class AnnouncementsDeliveryCacheModel implements CacheModel<AnnouncementsDelivery>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{deliveryId=");
		sb.append(deliveryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", email=");
		sb.append(email);
		sb.append(", sms=");
		sb.append(sms);
		sb.append(", website=");
		sb.append(website);
		sb.append("}");

		return sb.toString();
	}

	public AnnouncementsDelivery toEntityModel() {
		AnnouncementsDeliveryImpl announcementsDeliveryImpl = new AnnouncementsDeliveryImpl();

		announcementsDeliveryImpl.setDeliveryId(deliveryId);
		announcementsDeliveryImpl.setCompanyId(companyId);
		announcementsDeliveryImpl.setUserId(userId);

		if (type == null) {
			announcementsDeliveryImpl.setType(StringPool.BLANK);
		}
		else {
			announcementsDeliveryImpl.setType(type);
		}

		announcementsDeliveryImpl.setEmail(email);
		announcementsDeliveryImpl.setSms(sms);
		announcementsDeliveryImpl.setWebsite(website);

		announcementsDeliveryImpl.resetOriginalValues();

		return announcementsDeliveryImpl;
	}

	public long deliveryId;
	public long companyId;
	public long userId;
	public String type;
	public boolean email;
	public boolean sms;
	public boolean website;
}