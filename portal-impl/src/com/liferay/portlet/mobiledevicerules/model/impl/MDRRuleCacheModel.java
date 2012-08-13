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

import com.liferay.portlet.mobiledevicerules.model.MDRRule;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MDRRule in entity cache.
 *
 * @author Edward C. Han
 * @see MDRRule
 * @generated
 */
public class MDRRuleCacheModel implements CacheModel<MDRRule>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ruleId=");
		sb.append(ruleId);
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
		sb.append(", ruleGroupId=");
		sb.append(ruleGroupId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append("}");

		return sb.toString();
	}

	public MDRRule toEntityModel() {
		MDRRuleImpl mdrRuleImpl = new MDRRuleImpl();

		if (uuid == null) {
			mdrRuleImpl.setUuid(StringPool.BLANK);
		}
		else {
			mdrRuleImpl.setUuid(uuid);
		}

		mdrRuleImpl.setRuleId(ruleId);
		mdrRuleImpl.setGroupId(groupId);
		mdrRuleImpl.setCompanyId(companyId);
		mdrRuleImpl.setUserId(userId);

		if (userName == null) {
			mdrRuleImpl.setUserName(StringPool.BLANK);
		}
		else {
			mdrRuleImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mdrRuleImpl.setCreateDate(null);
		}
		else {
			mdrRuleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mdrRuleImpl.setModifiedDate(null);
		}
		else {
			mdrRuleImpl.setModifiedDate(new Date(modifiedDate));
		}

		mdrRuleImpl.setRuleGroupId(ruleGroupId);

		if (name == null) {
			mdrRuleImpl.setName(StringPool.BLANK);
		}
		else {
			mdrRuleImpl.setName(name);
		}

		if (description == null) {
			mdrRuleImpl.setDescription(StringPool.BLANK);
		}
		else {
			mdrRuleImpl.setDescription(description);
		}

		if (type == null) {
			mdrRuleImpl.setType(StringPool.BLANK);
		}
		else {
			mdrRuleImpl.setType(type);
		}

		if (typeSettings == null) {
			mdrRuleImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			mdrRuleImpl.setTypeSettings(typeSettings);
		}

		mdrRuleImpl.resetOriginalValues();

		return mdrRuleImpl;
	}

	public String uuid;
	public long ruleId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long ruleGroupId;
	public String name;
	public String description;
	public String type;
	public String typeSettings;
}