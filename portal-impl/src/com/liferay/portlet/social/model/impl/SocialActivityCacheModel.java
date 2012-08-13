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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.social.model.SocialActivity;

import java.io.Serializable;

/**
 * The cache model class for representing SocialActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivity
 * @generated
 */
public class SocialActivityCacheModel implements CacheModel<SocialActivity>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{activityId=");
		sb.append(activityId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", mirrorActivityId=");
		sb.append(mirrorActivityId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append(", receiverUserId=");
		sb.append(receiverUserId);
		sb.append("}");

		return sb.toString();
	}

	public SocialActivity toEntityModel() {
		SocialActivityImpl socialActivityImpl = new SocialActivityImpl();

		socialActivityImpl.setActivityId(activityId);
		socialActivityImpl.setGroupId(groupId);
		socialActivityImpl.setCompanyId(companyId);
		socialActivityImpl.setUserId(userId);
		socialActivityImpl.setCreateDate(createDate);
		socialActivityImpl.setMirrorActivityId(mirrorActivityId);
		socialActivityImpl.setClassNameId(classNameId);
		socialActivityImpl.setClassPK(classPK);
		socialActivityImpl.setType(type);

		if (extraData == null) {
			socialActivityImpl.setExtraData(StringPool.BLANK);
		}
		else {
			socialActivityImpl.setExtraData(extraData);
		}

		socialActivityImpl.setReceiverUserId(receiverUserId);

		socialActivityImpl.resetOriginalValues();

		return socialActivityImpl;
	}

	public long activityId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long mirrorActivityId;
	public long classNameId;
	public long classPK;
	public int type;
	public String extraData;
	public long receiverUserId;
}