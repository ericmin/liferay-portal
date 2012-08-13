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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.softwarecatalog.model.SCProductVersion;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SCProductVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SCProductVersion
 * @generated
 */
public class SCProductVersionCacheModel implements CacheModel<SCProductVersion>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{productVersionId=");
		sb.append(productVersionId);
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
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append(", version=");
		sb.append(version);
		sb.append(", changeLog=");
		sb.append(changeLog);
		sb.append(", downloadPageURL=");
		sb.append(downloadPageURL);
		sb.append(", directDownloadURL=");
		sb.append(directDownloadURL);
		sb.append(", repoStoreArtifact=");
		sb.append(repoStoreArtifact);
		sb.append("}");

		return sb.toString();
	}

	public SCProductVersion toEntityModel() {
		SCProductVersionImpl scProductVersionImpl = new SCProductVersionImpl();

		scProductVersionImpl.setProductVersionId(productVersionId);
		scProductVersionImpl.setCompanyId(companyId);
		scProductVersionImpl.setUserId(userId);

		if (userName == null) {
			scProductVersionImpl.setUserName(StringPool.BLANK);
		}
		else {
			scProductVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			scProductVersionImpl.setCreateDate(null);
		}
		else {
			scProductVersionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			scProductVersionImpl.setModifiedDate(null);
		}
		else {
			scProductVersionImpl.setModifiedDate(new Date(modifiedDate));
		}

		scProductVersionImpl.setProductEntryId(productEntryId);

		if (version == null) {
			scProductVersionImpl.setVersion(StringPool.BLANK);
		}
		else {
			scProductVersionImpl.setVersion(version);
		}

		if (changeLog == null) {
			scProductVersionImpl.setChangeLog(StringPool.BLANK);
		}
		else {
			scProductVersionImpl.setChangeLog(changeLog);
		}

		if (downloadPageURL == null) {
			scProductVersionImpl.setDownloadPageURL(StringPool.BLANK);
		}
		else {
			scProductVersionImpl.setDownloadPageURL(downloadPageURL);
		}

		if (directDownloadURL == null) {
			scProductVersionImpl.setDirectDownloadURL(StringPool.BLANK);
		}
		else {
			scProductVersionImpl.setDirectDownloadURL(directDownloadURL);
		}

		scProductVersionImpl.setRepoStoreArtifact(repoStoreArtifact);

		scProductVersionImpl.resetOriginalValues();

		return scProductVersionImpl;
	}

	public long productVersionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long productEntryId;
	public String version;
	public String changeLog;
	public String downloadPageURL;
	public String directDownloadURL;
	public boolean repoStoreArtifact;
}