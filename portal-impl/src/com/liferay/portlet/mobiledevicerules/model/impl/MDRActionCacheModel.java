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

import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing MDRAction in entity cache.
 *
 * @author Edward C. Han
 * @see MDRAction
 * @generated
 */
public class MDRActionCacheModel implements CacheModel<MDRAction>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", actionId=");
		sb.append(actionId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", ruleGroupInstanceId=");
		sb.append(ruleGroupInstanceId);
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

	public MDRAction toEntityModel() {
		MDRActionImpl mdrActionImpl = new MDRActionImpl();

		if (uuid == null) {
			mdrActionImpl.setUuid(StringPool.BLANK);
		}
		else {
			mdrActionImpl.setUuid(uuid);
		}

		mdrActionImpl.setActionId(actionId);
		mdrActionImpl.setGroupId(groupId);
		mdrActionImpl.setCompanyId(companyId);
		mdrActionImpl.setUserId(userId);

		if (userName == null) {
			mdrActionImpl.setUserName(StringPool.BLANK);
		}
		else {
			mdrActionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mdrActionImpl.setCreateDate(null);
		}
		else {
			mdrActionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mdrActionImpl.setModifiedDate(null);
		}
		else {
			mdrActionImpl.setModifiedDate(new Date(modifiedDate));
		}

		mdrActionImpl.setClassNameId(classNameId);
		mdrActionImpl.setClassPK(classPK);
		mdrActionImpl.setRuleGroupInstanceId(ruleGroupInstanceId);

		if (name == null) {
			mdrActionImpl.setName(StringPool.BLANK);
		}
		else {
			mdrActionImpl.setName(name);
		}

		if (description == null) {
			mdrActionImpl.setDescription(StringPool.BLANK);
		}
		else {
			mdrActionImpl.setDescription(description);
		}

		if (type == null) {
			mdrActionImpl.setType(StringPool.BLANK);
		}
		else {
			mdrActionImpl.setType(type);
		}

		if (typeSettings == null) {
			mdrActionImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			mdrActionImpl.setTypeSettings(typeSettings);
		}

		mdrActionImpl.resetOriginalValues();

		return mdrActionImpl;
	}

	public String uuid;
	public long actionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long ruleGroupInstanceId;
	public String name;
	public String description;
	public String type;
	public String typeSettings;
}