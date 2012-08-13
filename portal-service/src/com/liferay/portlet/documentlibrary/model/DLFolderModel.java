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

package com.liferay.portlet.documentlibrary.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the DLFolder service. Represents a row in the &quot;DLFolder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.documentlibrary.model.impl.DLFolderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLFolder
 * @see com.liferay.portlet.documentlibrary.model.impl.DLFolderImpl
 * @see com.liferay.portlet.documentlibrary.model.impl.DLFolderModelImpl
 * @generated
 */
public interface DLFolderModel extends BaseModel<DLFolder>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a document library folder model instance should use the {@link DLFolder} interface instead.
	 */

	/**
	 * Returns the primary key of this document library folder.
	 *
	 * @return the primary key of this document library folder
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this document library folder.
	 *
	 * @param primaryKey the primary key of this document library folder
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this document library folder.
	 *
	 * @return the uuid of this document library folder
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this document library folder.
	 *
	 * @param uuid the uuid of this document library folder
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the folder ID of this document library folder.
	 *
	 * @return the folder ID of this document library folder
	 */
	public long getFolderId();

	/**
	 * Sets the folder ID of this document library folder.
	 *
	 * @param folderId the folder ID of this document library folder
	 */
	public void setFolderId(long folderId);

	/**
	 * Returns the group ID of this document library folder.
	 *
	 * @return the group ID of this document library folder
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this document library folder.
	 *
	 * @param groupId the group ID of this document library folder
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this document library folder.
	 *
	 * @return the company ID of this document library folder
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this document library folder.
	 *
	 * @param companyId the company ID of this document library folder
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this document library folder.
	 *
	 * @return the user ID of this document library folder
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this document library folder.
	 *
	 * @param userId the user ID of this document library folder
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this document library folder.
	 *
	 * @return the user uuid of this document library folder
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this document library folder.
	 *
	 * @param userUuid the user uuid of this document library folder
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this document library folder.
	 *
	 * @return the user name of this document library folder
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this document library folder.
	 *
	 * @param userName the user name of this document library folder
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this document library folder.
	 *
	 * @return the create date of this document library folder
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this document library folder.
	 *
	 * @param createDate the create date of this document library folder
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this document library folder.
	 *
	 * @return the modified date of this document library folder
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this document library folder.
	 *
	 * @param modifiedDate the modified date of this document library folder
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the repository ID of this document library folder.
	 *
	 * @return the repository ID of this document library folder
	 */
	public long getRepositoryId();

	/**
	 * Sets the repository ID of this document library folder.
	 *
	 * @param repositoryId the repository ID of this document library folder
	 */
	public void setRepositoryId(long repositoryId);

	/**
	 * Returns the mount point of this document library folder.
	 *
	 * @return the mount point of this document library folder
	 */
	public boolean getMountPoint();

	/**
	 * Returns <code>true</code> if this document library folder is mount point.
	 *
	 * @return <code>true</code> if this document library folder is mount point; <code>false</code> otherwise
	 */
	public boolean isMountPoint();

	/**
	 * Sets whether this document library folder is mount point.
	 *
	 * @param mountPoint the mount point of this document library folder
	 */
	public void setMountPoint(boolean mountPoint);

	/**
	 * Returns the parent folder ID of this document library folder.
	 *
	 * @return the parent folder ID of this document library folder
	 */
	public long getParentFolderId();

	/**
	 * Sets the parent folder ID of this document library folder.
	 *
	 * @param parentFolderId the parent folder ID of this document library folder
	 */
	public void setParentFolderId(long parentFolderId);

	/**
	 * Returns the name of this document library folder.
	 *
	 * @return the name of this document library folder
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this document library folder.
	 *
	 * @param name the name of this document library folder
	 */
	public void setName(String name);

	/**
	 * Returns the description of this document library folder.
	 *
	 * @return the description of this document library folder
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this document library folder.
	 *
	 * @param description the description of this document library folder
	 */
	public void setDescription(String description);

	/**
	 * Returns the last post date of this document library folder.
	 *
	 * @return the last post date of this document library folder
	 */
	public Date getLastPostDate();

	/**
	 * Sets the last post date of this document library folder.
	 *
	 * @param lastPostDate the last post date of this document library folder
	 */
	public void setLastPostDate(Date lastPostDate);

	/**
	 * Returns the default file entry type ID of this document library folder.
	 *
	 * @return the default file entry type ID of this document library folder
	 */
	public long getDefaultFileEntryTypeId();

	/**
	 * Sets the default file entry type ID of this document library folder.
	 *
	 * @param defaultFileEntryTypeId the default file entry type ID of this document library folder
	 */
	public void setDefaultFileEntryTypeId(long defaultFileEntryTypeId);

	/**
	 * Returns the override file entry types of this document library folder.
	 *
	 * @return the override file entry types of this document library folder
	 */
	public boolean getOverrideFileEntryTypes();

	/**
	 * Returns <code>true</code> if this document library folder is override file entry types.
	 *
	 * @return <code>true</code> if this document library folder is override file entry types; <code>false</code> otherwise
	 */
	public boolean isOverrideFileEntryTypes();

	/**
	 * Sets whether this document library folder is override file entry types.
	 *
	 * @param overrideFileEntryTypes the override file entry types of this document library folder
	 */
	public void setOverrideFileEntryTypes(boolean overrideFileEntryTypes);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(DLFolder dlFolder);

	public int hashCode();

	public CacheModel<DLFolder> toCacheModel();

	public DLFolder toEscapedModel();

	public String toString();

	public String toXmlString();
}