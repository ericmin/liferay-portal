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

package com.liferay.portal.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Team service. Represents a row in the &quot;Team&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.TeamModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.TeamImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Team
 * @see com.liferay.portal.model.impl.TeamImpl
 * @see com.liferay.portal.model.impl.TeamModelImpl
 * @generated
 */
public interface TeamModel extends BaseModel<Team>, GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a team model instance should use the {@link Team} interface instead.
	 */

	/**
	 * Returns the primary key of this team.
	 *
	 * @return the primary key of this team
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this team.
	 *
	 * @param primaryKey the primary key of this team
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the team ID of this team.
	 *
	 * @return the team ID of this team
	 */
	public long getTeamId();

	/**
	 * Sets the team ID of this team.
	 *
	 * @param teamId the team ID of this team
	 */
	public void setTeamId(long teamId);

	/**
	 * Returns the company ID of this team.
	 *
	 * @return the company ID of this team
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this team.
	 *
	 * @param companyId the company ID of this team
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this team.
	 *
	 * @return the user ID of this team
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this team.
	 *
	 * @param userId the user ID of this team
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this team.
	 *
	 * @return the user uuid of this team
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this team.
	 *
	 * @param userUuid the user uuid of this team
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this team.
	 *
	 * @return the user name of this team
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this team.
	 *
	 * @param userName the user name of this team
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this team.
	 *
	 * @return the create date of this team
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this team.
	 *
	 * @param createDate the create date of this team
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this team.
	 *
	 * @return the modified date of this team
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this team.
	 *
	 * @param modifiedDate the modified date of this team
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the group ID of this team.
	 *
	 * @return the group ID of this team
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this team.
	 *
	 * @param groupId the group ID of this team
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the name of this team.
	 *
	 * @return the name of this team
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this team.
	 *
	 * @param name the name of this team
	 */
	public void setName(String name);

	/**
	 * Returns the description of this team.
	 *
	 * @return the description of this team
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this team.
	 *
	 * @param description the description of this team
	 */
	public void setDescription(String description);

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

	public int compareTo(Team team);

	public int hashCode();

	public CacheModel<Team> toCacheModel();

	public Team toEscapedModel();

	public String toString();

	public String toXmlString();
}