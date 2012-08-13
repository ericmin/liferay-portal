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

package com.liferay.portlet.asset.model;

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
 * The base model interface for the AssetTag service. Represents a row in the &quot;AssetTag&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.asset.model.impl.AssetTagModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.asset.model.impl.AssetTagImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetTag
 * @see com.liferay.portlet.asset.model.impl.AssetTagImpl
 * @see com.liferay.portlet.asset.model.impl.AssetTagModelImpl
 * @generated
 */
public interface AssetTagModel extends BaseModel<AssetTag>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a asset tag model instance should use the {@link AssetTag} interface instead.
	 */

	/**
	 * Returns the primary key of this asset tag.
	 *
	 * @return the primary key of this asset tag
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this asset tag.
	 *
	 * @param primaryKey the primary key of this asset tag
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the tag ID of this asset tag.
	 *
	 * @return the tag ID of this asset tag
	 */
	public long getTagId();

	/**
	 * Sets the tag ID of this asset tag.
	 *
	 * @param tagId the tag ID of this asset tag
	 */
	public void setTagId(long tagId);

	/**
	 * Returns the group ID of this asset tag.
	 *
	 * @return the group ID of this asset tag
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this asset tag.
	 *
	 * @param groupId the group ID of this asset tag
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this asset tag.
	 *
	 * @return the company ID of this asset tag
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this asset tag.
	 *
	 * @param companyId the company ID of this asset tag
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this asset tag.
	 *
	 * @return the user ID of this asset tag
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this asset tag.
	 *
	 * @param userId the user ID of this asset tag
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this asset tag.
	 *
	 * @return the user uuid of this asset tag
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this asset tag.
	 *
	 * @param userUuid the user uuid of this asset tag
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this asset tag.
	 *
	 * @return the user name of this asset tag
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this asset tag.
	 *
	 * @param userName the user name of this asset tag
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this asset tag.
	 *
	 * @return the create date of this asset tag
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this asset tag.
	 *
	 * @param createDate the create date of this asset tag
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this asset tag.
	 *
	 * @return the modified date of this asset tag
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this asset tag.
	 *
	 * @param modifiedDate the modified date of this asset tag
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this asset tag.
	 *
	 * @return the name of this asset tag
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this asset tag.
	 *
	 * @param name the name of this asset tag
	 */
	public void setName(String name);

	/**
	 * Returns the asset count of this asset tag.
	 *
	 * @return the asset count of this asset tag
	 */
	public int getAssetCount();

	/**
	 * Sets the asset count of this asset tag.
	 *
	 * @param assetCount the asset count of this asset tag
	 */
	public void setAssetCount(int assetCount);

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

	public int compareTo(AssetTag assetTag);

	public int hashCode();

	public CacheModel<AssetTag> toCacheModel();

	public AssetTag toEscapedModel();

	public String toString();

	public String toXmlString();
}