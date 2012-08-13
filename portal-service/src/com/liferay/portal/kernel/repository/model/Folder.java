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

package com.liferay.portal.kernel.repository.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Chow
 */
public interface Folder extends RepositoryModel<Folder> {

	public static final Accessor<Folder, Long> FOLDER_ID_ACCESSOR =

		new Accessor<Folder, Long>() {

			public Long get(Folder folder) {
				return folder.getFolderId();
			}

		};

	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException;

	public List<Folder> getAncestors()
		throws PortalException, SystemException;

	public long getCompanyId();

	public Date getCreateDate();

	public String getDescription();

	public long getFolderId();

	public long getGroupId();

	public Date getLastPostDate();

	public Date getModifiedDate();

	public String getName();

	public Folder getParentFolder() throws PortalException, SystemException;

	public long getParentFolderId();

	public long getRepositoryId();

	public long getUserId();

	public String getUserName();

	public String getUserUuid() throws SystemException;

	public String getUuid();

	public boolean hasInheritableLock();

	public boolean hasLock();

	public boolean isDefaultRepository();

	public boolean isLocked();

	public boolean isMountPoint();

	public boolean isRoot();

	public boolean isSupportsLocking();

	public boolean isSupportsMetadata();

	public boolean isSupportsMultipleUpload();

	public boolean isSupportsShortcuts();

	public boolean isSupportsSocial();

}