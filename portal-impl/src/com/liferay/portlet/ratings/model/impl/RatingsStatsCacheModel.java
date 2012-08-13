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
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.ratings.model.RatingsStats;

import java.io.Serializable;

/**
 * The cache model class for representing RatingsStats in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RatingsStats
 * @generated
 */
public class RatingsStatsCacheModel implements CacheModel<RatingsStats>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{statsId=");
		sb.append(statsId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", totalEntries=");
		sb.append(totalEntries);
		sb.append(", totalScore=");
		sb.append(totalScore);
		sb.append(", averageScore=");
		sb.append(averageScore);
		sb.append("}");

		return sb.toString();
	}

	public RatingsStats toEntityModel() {
		RatingsStatsImpl ratingsStatsImpl = new RatingsStatsImpl();

		ratingsStatsImpl.setStatsId(statsId);
		ratingsStatsImpl.setClassNameId(classNameId);
		ratingsStatsImpl.setClassPK(classPK);
		ratingsStatsImpl.setTotalEntries(totalEntries);
		ratingsStatsImpl.setTotalScore(totalScore);
		ratingsStatsImpl.setAverageScore(averageScore);

		ratingsStatsImpl.resetOriginalValues();

		return ratingsStatsImpl;
	}

	public long statsId;
	public long classNameId;
	public long classPK;
	public int totalEntries;
	public double totalScore;
	public double averageScore;
}