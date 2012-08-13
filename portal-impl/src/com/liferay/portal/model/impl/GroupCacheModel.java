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
import com.liferay.portal.model.Group;

import java.io.Serializable;

/**
 * The cache model class for representing Group in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Group
 * @generated
 */
public class GroupCacheModel implements CacheModel<Group>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", creatorUserId=");
		sb.append(creatorUserId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", parentGroupId=");
		sb.append(parentGroupId);
		sb.append(", liveGroupId=");
		sb.append(liveGroupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append(", friendlyURL=");
		sb.append(friendlyURL);
		sb.append(", site=");
		sb.append(site);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	public Group toEntityModel() {
		GroupImpl groupImpl = new GroupImpl();

		groupImpl.setGroupId(groupId);
		groupImpl.setCompanyId(companyId);
		groupImpl.setCreatorUserId(creatorUserId);
		groupImpl.setClassNameId(classNameId);
		groupImpl.setClassPK(classPK);
		groupImpl.setParentGroupId(parentGroupId);
		groupImpl.setLiveGroupId(liveGroupId);

		if (name == null) {
			groupImpl.setName(StringPool.BLANK);
		}
		else {
			groupImpl.setName(name);
		}

		if (description == null) {
			groupImpl.setDescription(StringPool.BLANK);
		}
		else {
			groupImpl.setDescription(description);
		}

		groupImpl.setType(type);

		if (typeSettings == null) {
			groupImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			groupImpl.setTypeSettings(typeSettings);
		}

		if (friendlyURL == null) {
			groupImpl.setFriendlyURL(StringPool.BLANK);
		}
		else {
			groupImpl.setFriendlyURL(friendlyURL);
		}

		groupImpl.setSite(site);
		groupImpl.setActive(active);

		groupImpl.resetOriginalValues();

		return groupImpl;
	}

	public long groupId;
	public long companyId;
	public long creatorUserId;
	public long classNameId;
	public long classPK;
	public long parentGroupId;
	public long liveGroupId;
	public String name;
	public String description;
	public int type;
	public String typeSettings;
	public String friendlyURL;
	public boolean site;
	public boolean active;
}