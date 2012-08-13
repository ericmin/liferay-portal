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

import com.liferay.portlet.social.model.SocialActivityAchievement;

import java.io.Serializable;

/**
 * The cache model class for representing SocialActivityAchievement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityAchievement
 * @generated
 */
public class SocialActivityAchievementCacheModel implements CacheModel<SocialActivityAchievement>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{activityAchievementId=");
		sb.append(activityAchievementId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", firstInGroup=");
		sb.append(firstInGroup);
		sb.append("}");

		return sb.toString();
	}

	public SocialActivityAchievement toEntityModel() {
		SocialActivityAchievementImpl socialActivityAchievementImpl = new SocialActivityAchievementImpl();

		socialActivityAchievementImpl.setActivityAchievementId(activityAchievementId);
		socialActivityAchievementImpl.setGroupId(groupId);
		socialActivityAchievementImpl.setCompanyId(companyId);
		socialActivityAchievementImpl.setUserId(userId);
		socialActivityAchievementImpl.setCreateDate(createDate);

		if (name == null) {
			socialActivityAchievementImpl.setName(StringPool.BLANK);
		}
		else {
			socialActivityAchievementImpl.setName(name);
		}

		socialActivityAchievementImpl.setFirstInGroup(firstInGroup);

		socialActivityAchievementImpl.resetOriginalValues();

		return socialActivityAchievementImpl;
	}

	public long activityAchievementId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public String name;
	public boolean firstInGroup;
}