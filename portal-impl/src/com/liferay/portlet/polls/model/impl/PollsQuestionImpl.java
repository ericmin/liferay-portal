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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class PollsQuestionImpl extends PollsQuestionBaseImpl {

	public PollsQuestionImpl() {
	}

	public List<PollsChoice> getChoices() throws SystemException {
		return PollsChoiceLocalServiceUtil.getChoices(getQuestionId());
	}

	public int getVotesCount() throws SystemException {
		return PollsVoteLocalServiceUtil.getQuestionVotesCount(getQuestionId());
	}

	public boolean isExpired() {
		Date expirationDate = getExpirationDate();

		if ((expirationDate != null) && (expirationDate.before(new Date()))) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isExpired(
		ServiceContext serviceContext, Date defaultCreateDate) {

		Date expirationDate = getExpirationDate();

		if (expirationDate == null) {
			return false;
		}

		Date createDate = serviceContext.getCreateDate(defaultCreateDate);

		if (createDate.after(expirationDate)) {
			return true;
		}
		else {
			return false;
		}
	}

}