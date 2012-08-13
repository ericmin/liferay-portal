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

package com.liferay.portlet.polls.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.polls.model.PollsQuestion;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PollsQuestion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestion
 * @generated
 */
public class PollsQuestionCacheModel implements CacheModel<PollsQuestion>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", questionId=");
		sb.append(questionId);
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
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append(", lastVoteDate=");
		sb.append(lastVoteDate);
		sb.append("}");

		return sb.toString();
	}

	public PollsQuestion toEntityModel() {
		PollsQuestionImpl pollsQuestionImpl = new PollsQuestionImpl();

		if (uuid == null) {
			pollsQuestionImpl.setUuid(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setUuid(uuid);
		}

		pollsQuestionImpl.setQuestionId(questionId);
		pollsQuestionImpl.setGroupId(groupId);
		pollsQuestionImpl.setCompanyId(companyId);
		pollsQuestionImpl.setUserId(userId);

		if (userName == null) {
			pollsQuestionImpl.setUserName(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setCreateDate(null);
		}
		else {
			pollsQuestionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setModifiedDate(null);
		}
		else {
			pollsQuestionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (title == null) {
			pollsQuestionImpl.setTitle(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setTitle(title);
		}

		if (description == null) {
			pollsQuestionImpl.setDescription(StringPool.BLANK);
		}
		else {
			pollsQuestionImpl.setDescription(description);
		}

		if (expirationDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setExpirationDate(null);
		}
		else {
			pollsQuestionImpl.setExpirationDate(new Date(expirationDate));
		}

		if (lastVoteDate == Long.MIN_VALUE) {
			pollsQuestionImpl.setLastVoteDate(null);
		}
		else {
			pollsQuestionImpl.setLastVoteDate(new Date(lastVoteDate));
		}

		pollsQuestionImpl.resetOriginalValues();

		return pollsQuestionImpl;
	}

	public String uuid;
	public long questionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public String description;
	public long expirationDate;
	public long lastVoteDate;
}