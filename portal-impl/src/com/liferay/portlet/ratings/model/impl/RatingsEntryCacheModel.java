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

package com.liferay.portlet.ratings.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.ratings.model.RatingsEntry;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing RatingsEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsEntry
 * @generated
 */
public class RatingsEntryCacheModel implements CacheModel<RatingsEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{entryId=");
		sb.append(entryId);
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
		sb.append(", score=");
		sb.append(score);
		sb.append("}");

		return sb.toString();
	}

	public RatingsEntry toEntityModel() {
		RatingsEntryImpl ratingsEntryImpl = new RatingsEntryImpl();

		ratingsEntryImpl.setEntryId(entryId);
		ratingsEntryImpl.setCompanyId(companyId);
		ratingsEntryImpl.setUserId(userId);

		if (userName == null) {
			ratingsEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			ratingsEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ratingsEntryImpl.setCreateDate(null);
		}
		else {
			ratingsEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ratingsEntryImpl.setModifiedDate(null);
		}
		else {
			ratingsEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		ratingsEntryImpl.setClassNameId(classNameId);
		ratingsEntryImpl.setClassPK(classPK);
		ratingsEntryImpl.setScore(score);

		ratingsEntryImpl.resetOriginalValues();

		return ratingsEntryImpl;
	}

	public long entryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public double score;
}