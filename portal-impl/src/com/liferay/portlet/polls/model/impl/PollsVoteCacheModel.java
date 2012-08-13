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

import com.liferay.portlet.polls.model.PollsVote;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing PollsVote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PollsVote
 * @generated
 */
public class PollsVoteCacheModel implements CacheModel<PollsVote>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{voteId=");
		sb.append(voteId);
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
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", choiceId=");
		sb.append(choiceId);
		sb.append(", voteDate=");
		sb.append(voteDate);
		sb.append("}");

		return sb.toString();
	}

	public PollsVote toEntityModel() {
		PollsVoteImpl pollsVoteImpl = new PollsVoteImpl();

		pollsVoteImpl.setVoteId(voteId);
		pollsVoteImpl.setCompanyId(companyId);
		pollsVoteImpl.setUserId(userId);

		if (userName == null) {
			pollsVoteImpl.setUserName(StringPool.BLANK);
		}
		else {
			pollsVoteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsVoteImpl.setCreateDate(null);
		}
		else {
			pollsVoteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsVoteImpl.setModifiedDate(null);
		}
		else {
			pollsVoteImpl.setModifiedDate(new Date(modifiedDate));
		}

		pollsVoteImpl.setQuestionId(questionId);
		pollsVoteImpl.setChoiceId(choiceId);

		if (voteDate == Long.MIN_VALUE) {
			pollsVoteImpl.setVoteDate(null);
		}
		else {
			pollsVoteImpl.setVoteDate(new Date(voteDate));
		}

		pollsVoteImpl.resetOriginalValues();

		return pollsVoteImpl;
	}

	public long voteId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long questionId;
	public long choiceId;
	public long voteDate;
}