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

import com.liferay.portlet.social.model.SocialActivityLimit;

import java.io.Serializable;

/**
 * The cache model class for representing SocialActivityLimit in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityLimit
 * @generated
 */
public class SocialActivityLimitCacheModel implements CacheModel<SocialActivityLimit>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{activityLimitId=");
		sb.append(activityLimitId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", activityType=");
		sb.append(activityType);
		sb.append(", activityCounterName=");
		sb.append(activityCounterName);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	public SocialActivityLimit toEntityModel() {
		SocialActivityLimitImpl socialActivityLimitImpl = new SocialActivityLimitImpl();

		socialActivityLimitImpl.setActivityLimitId(activityLimitId);
		socialActivityLimitImpl.setGroupId(groupId);
		socialActivityLimitImpl.setCompanyId(companyId);
		socialActivityLimitImpl.setUserId(userId);
		socialActivityLimitImpl.setClassNameId(classNameId);
		socialActivityLimitImpl.setClassPK(classPK);
		socialActivityLimitImpl.setActivityType(activityType);

		if (activityCounterName == null) {
			socialActivityLimitImpl.setActivityCounterName(StringPool.BLANK);
		}
		else {
			socialActivityLimitImpl.setActivityCounterName(activityCounterName);
		}

		if (value == null) {
			socialActivityLimitImpl.setValue(StringPool.BLANK);
		}
		else {
			socialActivityLimitImpl.setValue(value);
		}

		socialActivityLimitImpl.resetOriginalValues();

		return socialActivityLimitImpl;
	}

	public long activityLimitId;
	public long groupId;
	public long companyId;
	public long userId;
	public long classNameId;
	public long classPK;
	public int activityType;
	public String activityCounterName;
	public String value;
}