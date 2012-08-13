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

package com.liferay.portlet.mobiledevicerules.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MDRRuleGroup in entity cache.
 *
 * @author Edward C. Han
 * @see MDRRuleGroup
 * @generated
 */
public class MDRRuleGroupCacheModel implements CacheModel<MDRRuleGroup>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ruleGroupId=");
		sb.append(ruleGroupId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public MDRRuleGroup toEntityModel() {
		MDRRuleGroupImpl mdrRuleGroupImpl = new MDRRuleGroupImpl();

		if (uuid == null) {
			mdrRuleGroupImpl.setUuid(StringPool.BLANK);
		}
		else {
			mdrRuleGroupImpl.setUuid(uuid);
		}

		mdrRuleGroupImpl.setRuleGroupId(ruleGroupId);
		mdrRuleGroupImpl.setGroupId(groupId);
		mdrRuleGroupImpl.setCompanyId(companyId);
		mdrRuleGroupImpl.setUserId(userId);

		if (userName == null) {
			mdrRuleGroupImpl.setUserName(StringPool.BLANK);
		}
		else {
			mdrRuleGroupImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mdrRuleGroupImpl.setCreateDate(null);
		}
		else {
			mdrRuleGroupImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mdrRuleGroupImpl.setModifiedDate(null);
		}
		else {
			mdrRuleGroupImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			mdrRuleGroupImpl.setName(StringPool.BLANK);
		}
		else {
			mdrRuleGroupImpl.setName(name);
		}

		if (description == null) {
			mdrRuleGroupImpl.setDescription(StringPool.BLANK);
		}
		else {
			mdrRuleGroupImpl.setDescription(description);
		}

		mdrRuleGroupImpl.resetOriginalValues();

		return mdrRuleGroupImpl;
	}

	public String uuid;
	public long ruleGroupId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
}