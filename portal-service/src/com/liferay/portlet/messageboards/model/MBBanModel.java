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

package com.liferay.portlet.messageboards.model;

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
 * The base model interface for the MBBan service. Represents a row in the &quot;MBBan&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.messageboards.model.impl.MBBanModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.messageboards.model.impl.MBBanImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBBan
 * @see com.liferay.portlet.messageboards.model.impl.MBBanImpl
 * @see com.liferay.portlet.messageboards.model.impl.MBBanModelImpl
 * @generated
 */
public interface MBBanModel extends BaseModel<MBBan>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a message boards ban model instance should use the {@link MBBan} interface instead.
	 */

	/**
	 * Returns the primary key of this message boards ban.
	 *
	 * @return the primary key of this message boards ban
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this message boards ban.
	 *
	 * @param primaryKey the primary key of this message boards ban
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ban ID of this message boards ban.
	 *
	 * @return the ban ID of this message boards ban
	 */
	public long getBanId();

	/**
	 * Sets the ban ID of this message boards ban.
	 *
	 * @param banId the ban ID of this message boards ban
	 */
	public void setBanId(long banId);

	/**
	 * Returns the group ID of this message boards ban.
	 *
	 * @return the group ID of this message boards ban
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this message boards ban.
	 *
	 * @param groupId the group ID of this message boards ban
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this message boards ban.
	 *
	 * @return the company ID of this message boards ban
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this message boards ban.
	 *
	 * @param companyId the company ID of this message boards ban
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this message boards ban.
	 *
	 * @return the user ID of this message boards ban
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this message boards ban.
	 *
	 * @param userId the user ID of this message boards ban
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this message boards ban.
	 *
	 * @return the user uuid of this message boards ban
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this message boards ban.
	 *
	 * @param userUuid the user uuid of this message boards ban
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this message boards ban.
	 *
	 * @return the user name of this message boards ban
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this message boards ban.
	 *
	 * @param userName the user name of this message boards ban
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this message boards ban.
	 *
	 * @return the create date of this message boards ban
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this message boards ban.
	 *
	 * @param createDate the create date of this message boards ban
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this message boards ban.
	 *
	 * @return the modified date of this message boards ban
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this message boards ban.
	 *
	 * @param modifiedDate the modified date of this message boards ban
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ban user ID of this message boards ban.
	 *
	 * @return the ban user ID of this message boards ban
	 */
	public long getBanUserId();

	/**
	 * Sets the ban user ID of this message boards ban.
	 *
	 * @param banUserId the ban user ID of this message boards ban
	 */
	public void setBanUserId(long banUserId);

	/**
	 * Returns the ban user uuid of this message boards ban.
	 *
	 * @return the ban user uuid of this message boards ban
	 * @throws SystemException if a system exception occurred
	 */
	public String getBanUserUuid() throws SystemException;

	/**
	 * Sets the ban user uuid of this message boards ban.
	 *
	 * @param banUserUuid the ban user uuid of this message boards ban
	 */
	public void setBanUserUuid(String banUserUuid);

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

	public int compareTo(MBBan mbBan);

	public int hashCode();

	public CacheModel<MBBan> toCacheModel();

	public MBBan toEscapedModel();

	public String toString();

	public String toXmlString();
}