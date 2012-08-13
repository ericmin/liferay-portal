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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Release;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Release in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Release
 * @generated
 */
public class ReleaseCacheModel implements CacheModel<Release>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{releaseId=");
		sb.append(releaseId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", servletContextName=");
		sb.append(servletContextName);
		sb.append(", buildNumber=");
		sb.append(buildNumber);
		sb.append(", buildDate=");
		sb.append(buildDate);
		sb.append(", verified=");
		sb.append(verified);
		sb.append(", state=");
		sb.append(state);
		sb.append(", testString=");
		sb.append(testString);
		sb.append("}");

		return sb.toString();
	}

	public Release toEntityModel() {
		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setReleaseId(releaseId);

		if (createDate == Long.MIN_VALUE) {
			releaseImpl.setCreateDate(null);
		}
		else {
			releaseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			releaseImpl.setModifiedDate(null);
		}
		else {
			releaseImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (servletContextName == null) {
			releaseImpl.setServletContextName(StringPool.BLANK);
		}
		else {
			releaseImpl.setServletContextName(servletContextName);
		}

		releaseImpl.setBuildNumber(buildNumber);

		if (buildDate == Long.MIN_VALUE) {
			releaseImpl.setBuildDate(null);
		}
		else {
			releaseImpl.setBuildDate(new Date(buildDate));
		}

		releaseImpl.setVerified(verified);
		releaseImpl.setState(state);

		if (testString == null) {
			releaseImpl.setTestString(StringPool.BLANK);
		}
		else {
			releaseImpl.setTestString(testString);
		}

		releaseImpl.resetOriginalValues();

		return releaseImpl;
	}

	public long releaseId;
	public long createDate;
	public long modifiedDate;
	public String servletContextName;
	public int buildNumber;
	public long buildDate;
	public boolean verified;
	public int state;
	public String testString;
}