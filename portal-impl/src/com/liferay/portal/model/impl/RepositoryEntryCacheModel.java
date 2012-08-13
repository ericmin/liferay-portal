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
import com.liferay.portal.model.RepositoryEntry;

import java.io.Serializable;

/**
 * The cache model class for representing RepositoryEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryEntry
 * @generated
 */
public class RepositoryEntryCacheModel implements CacheModel<RepositoryEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", repositoryEntryId=");
		sb.append(repositoryEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", repositoryId=");
		sb.append(repositoryId);
		sb.append(", mappedId=");
		sb.append(mappedId);
		sb.append("}");

		return sb.toString();
	}

	public RepositoryEntry toEntityModel() {
		RepositoryEntryImpl repositoryEntryImpl = new RepositoryEntryImpl();

		if (uuid == null) {
			repositoryEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			repositoryEntryImpl.setUuid(uuid);
		}

		repositoryEntryImpl.setRepositoryEntryId(repositoryEntryId);
		repositoryEntryImpl.setGroupId(groupId);
		repositoryEntryImpl.setRepositoryId(repositoryId);

		if (mappedId == null) {
			repositoryEntryImpl.setMappedId(StringPool.BLANK);
		}
		else {
			repositoryEntryImpl.setMappedId(mappedId);
		}

		repositoryEntryImpl.resetOriginalValues();

		return repositoryEntryImpl;
	}

	public String uuid;
	public long repositoryEntryId;
	public long groupId;
	public long repositoryId;
	public String mappedId;
}