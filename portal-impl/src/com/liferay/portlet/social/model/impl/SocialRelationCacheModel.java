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

import com.liferay.portlet.social.model.SocialRelation;

import java.io.Serializable;

/**
 * The cache model class for representing SocialRelation in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialRelation
 * @generated
 */
public class SocialRelationCacheModel implements CacheModel<SocialRelation>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", relationId=");
		sb.append(relationId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", userId1=");
		sb.append(userId1);
		sb.append(", userId2=");
		sb.append(userId2);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public SocialRelation toEntityModel() {
		SocialRelationImpl socialRelationImpl = new SocialRelationImpl();

		if (uuid == null) {
			socialRelationImpl.setUuid(StringPool.BLANK);
		}
		else {
			socialRelationImpl.setUuid(uuid);
		}

		socialRelationImpl.setRelationId(relationId);
		socialRelationImpl.setCompanyId(companyId);
		socialRelationImpl.setCreateDate(createDate);
		socialRelationImpl.setUserId1(userId1);
		socialRelationImpl.setUserId2(userId2);
		socialRelationImpl.setType(type);

		socialRelationImpl.resetOriginalValues();

		return socialRelationImpl;
	}

	public String uuid;
	public long relationId;
	public long companyId;
	public long createDate;
	public long userId1;
	public long userId2;
	public int type;
}