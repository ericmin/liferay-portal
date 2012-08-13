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

import com.liferay.portlet.polls.model.PollsChoice;

import java.io.Serializable;

/**
 * The cache model class for representing PollsChoice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoice
 * @generated
 */
public class PollsChoiceCacheModel implements CacheModel<PollsChoice>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", choiceId=");
		sb.append(choiceId);
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	public PollsChoice toEntityModel() {
		PollsChoiceImpl pollsChoiceImpl = new PollsChoiceImpl();

		if (uuid == null) {
			pollsChoiceImpl.setUuid(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setUuid(uuid);
		}

		pollsChoiceImpl.setChoiceId(choiceId);
		pollsChoiceImpl.setQuestionId(questionId);

		if (name == null) {
			pollsChoiceImpl.setName(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setName(name);
		}

		if (description == null) {
			pollsChoiceImpl.setDescription(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setDescription(description);
		}

		pollsChoiceImpl.resetOriginalValues();

		return pollsChoiceImpl;
	}

	public String uuid;
	public long choiceId;
	public long questionId;
	public String name;
	public String description;
}