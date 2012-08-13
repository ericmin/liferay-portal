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

package com.liferay.portlet.blogs.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.blogs.model.BlogsStatsUser;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing BlogsStatsUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BlogsStatsUser
 * @generated
 */
public class BlogsStatsUserCacheModel implements CacheModel<BlogsStatsUser>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{statsUserId=");
		sb.append(statsUserId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", entryCount=");
		sb.append(entryCount);
		sb.append(", lastPostDate=");
		sb.append(lastPostDate);
		sb.append(", ratingsTotalEntries=");
		sb.append(ratingsTotalEntries);
		sb.append(", ratingsTotalScore=");
		sb.append(ratingsTotalScore);
		sb.append(", ratingsAverageScore=");
		sb.append(ratingsAverageScore);
		sb.append("}");

		return sb.toString();
	}

	public BlogsStatsUser toEntityModel() {
		BlogsStatsUserImpl blogsStatsUserImpl = new BlogsStatsUserImpl();

		blogsStatsUserImpl.setStatsUserId(statsUserId);
		blogsStatsUserImpl.setGroupId(groupId);
		blogsStatsUserImpl.setCompanyId(companyId);
		blogsStatsUserImpl.setUserId(userId);
		blogsStatsUserImpl.setEntryCount(entryCount);

		if (lastPostDate == Long.MIN_VALUE) {
			blogsStatsUserImpl.setLastPostDate(null);
		}
		else {
			blogsStatsUserImpl.setLastPostDate(new Date(lastPostDate));
		}

		blogsStatsUserImpl.setRatingsTotalEntries(ratingsTotalEntries);
		blogsStatsUserImpl.setRatingsTotalScore(ratingsTotalScore);
		blogsStatsUserImpl.setRatingsAverageScore(ratingsAverageScore);

		blogsStatsUserImpl.resetOriginalValues();

		return blogsStatsUserImpl;
	}

	public long statsUserId;
	public long groupId;
	public long companyId;
	public long userId;
	public int entryCount;
	public long lastPostDate;
	public int ratingsTotalEntries;
	public double ratingsTotalScore;
	public double ratingsAverageScore;
}