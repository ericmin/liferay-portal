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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.RepositoryEntryLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Michael C. Han
 */
public class RepositoryEntryLocalServiceImpl
	extends RepositoryEntryLocalServiceBaseImpl {

	public RepositoryEntry addRepositoryEntry(
			long groupId, long repositoryId, String mappedId,
			ServiceContext serviceContext)
		throws SystemException {

		long repositoryEntryId = counterLocalService.increment();

		RepositoryEntry repositoryEntry = repositoryEntryPersistence.create(
			repositoryEntryId);

		repositoryEntry.setGroupId(groupId);
		repositoryEntry.setUuid(serviceContext.getUuid());
		repositoryEntry.setRepositoryId(repositoryId);
		repositoryEntry.setMappedId(mappedId);

		repositoryEntryPersistence.update(repositoryEntry, false);

		return repositoryEntry;
	}

	public RepositoryEntry updateRepositoryEntry(
			long repositoryEntryId, String mappedId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry =
			repositoryEntryPersistence.findByPrimaryKey(repositoryEntryId);

		repositoryEntry.setMappedId(mappedId);

		repositoryEntryPersistence.update(repositoryEntry, false);

		return repositoryEntry;
	}

}