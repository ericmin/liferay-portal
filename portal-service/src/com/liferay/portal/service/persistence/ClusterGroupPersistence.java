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

package com.liferay.portal.service.persistence;

import com.liferay.portal.model.ClusterGroup;

/**
 * The persistence interface for the cluster group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ClusterGroupPersistenceImpl
 * @see ClusterGroupUtil
 * @generated
 */
public interface ClusterGroupPersistence extends BasePersistence<ClusterGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ClusterGroupUtil} to access the cluster group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cluster group in the entity cache if it is enabled.
	*
	* @param clusterGroup the cluster group
	*/
	public void cacheResult(com.liferay.portal.model.ClusterGroup clusterGroup);

	/**
	* Caches the cluster groups in the entity cache if it is enabled.
	*
	* @param clusterGroups the cluster groups
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.model.ClusterGroup> clusterGroups);

	/**
	* Creates a new cluster group with the primary key. Does not add the cluster group to the database.
	*
	* @param clusterGroupId the primary key for the new cluster group
	* @return the new cluster group
	*/
	public com.liferay.portal.model.ClusterGroup create(long clusterGroupId);

	/**
	* Removes the cluster group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param clusterGroupId the primary key of the cluster group
	* @return the cluster group that was removed
	* @throws com.liferay.portal.NoSuchClusterGroupException if a cluster group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ClusterGroup remove(long clusterGroupId)
		throws com.liferay.portal.NoSuchClusterGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.model.ClusterGroup updateImpl(
		com.liferay.portal.model.ClusterGroup clusterGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the cluster group with the primary key or throws a {@link com.liferay.portal.NoSuchClusterGroupException} if it could not be found.
	*
	* @param clusterGroupId the primary key of the cluster group
	* @return the cluster group
	* @throws com.liferay.portal.NoSuchClusterGroupException if a cluster group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ClusterGroup findByPrimaryKey(
		long clusterGroupId)
		throws com.liferay.portal.NoSuchClusterGroupException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the cluster group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param clusterGroupId the primary key of the cluster group
	* @return the cluster group, or <code>null</code> if a cluster group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.ClusterGroup fetchByPrimaryKey(
		long clusterGroupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cluster groups.
	*
	* @return the cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ClusterGroup> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the cluster groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of cluster groups
	* @param end the upper bound of the range of cluster groups (not inclusive)
	* @return the range of cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ClusterGroup> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the cluster groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of cluster groups
	* @param end the upper bound of the range of cluster groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.ClusterGroup> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cluster groups from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cluster groups.
	*
	* @return the number of cluster groups
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}