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

package com.liferay.portal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the user group remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupServiceUtil
 * @see com.liferay.portal.service.base.UserGroupServiceBaseImpl
 * @see com.liferay.portal.service.impl.UserGroupServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface UserGroupService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserGroupServiceUtil} to access the user group remote service. Add custom service methods to {@link com.liferay.portal.service.impl.UserGroupServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	/**
	* Adds the user groups to the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	* @throws PortalException if a group or user group with the primary key
	could not be found, or if the user did not have permission to
	assign group members
	* @throws SystemException if a system exception occurred
	*/
	public void addGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds the user groups to the team
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	* @throws PortalException if a team or user group with the primary key
	could not be found, or if the user did not have permission to
	assign team members
	* @throws SystemException if a system exception occurred
	*/
	public void addTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds a user group.
	*
	* <p>
	* This method handles the creation and bookkeeping of the user group,
	* including its resources, metadata, and internal data structures.
	* </p>
	*
	* @param name the user group's name
	* @param description the user group's description
	* @return the user group
	* @throws PortalException if the user group's information was invalid or if
	the user did not have permission to add the user group
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.UserGroup addUserGroup(
		java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the user group.
	*
	* @param userGroupId the primary key of the user group
	* @throws PortalException if a user group with the primary key could not be
	found, if the user did not have permission to delete the user
	group, or if the user group had a workflow in approved status
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user group with the primary key.
	*
	* @param userGroupId the primary key of the user group
	* @return Returns the user group with the primary key
	* @throws PortalException if a user group with the primary key could not be
	found or if the user did not have permission to view the user
	group
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.UserGroup getUserGroup(long userGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user group with the name.
	*
	* @param name the user group's name
	* @return Returns the user group with the name
	* @throws PortalException if a user group with the name could not be found
	or if the user did not have permission to view the user group
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.UserGroup getUserGroup(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user groups to which the user belongs.
	*
	* @param userId the primary key of the user
	* @return the user groups to which the user belongs
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.model.UserGroup> getUserUserGroups(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user groups from the group.
	*
	* @param groupId the primary key of the group
	* @param userGroupIds the primary keys of the user groups
	* @throws PortalException if the user did not have permission to assign
	group members
	* @throws SystemException if a system exception occurred
	*/
	public void unsetGroupUserGroups(long groupId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the user groups from the team.
	*
	* @param teamId the primary key of the team
	* @param userGroupIds the primary keys of the user groups
	* @throws PortalException if the user did not have permission to assign
	team members
	* @throws SystemException if a system exception occurred
	*/
	public void unsetTeamUserGroups(long teamId, long[] userGroupIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the user group.
	*
	* @param userGroupId the primary key of the user group
	* @param name the user group's name
	* @param description the the user group's description
	* @return the user group
	* @throws PortalException if a user group with the primary key was not
	found, if the new information was invalid, or if the user did not
	have permission to update the user group information
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.UserGroup updateUserGroup(
		long userGroupId, java.lang.String name, java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}