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

package com.liferay.portlet.announcements.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourceFinder;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.announcements.model.AnnouncementsFlag;
import com.liferay.portlet.announcements.service.AnnouncementsDeliveryLocalService;
import com.liferay.portlet.announcements.service.AnnouncementsDeliveryService;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalService;
import com.liferay.portlet.announcements.service.AnnouncementsEntryService;
import com.liferay.portlet.announcements.service.AnnouncementsFlagLocalService;
import com.liferay.portlet.announcements.service.AnnouncementsFlagService;
import com.liferay.portlet.announcements.service.persistence.AnnouncementsDeliveryPersistence;
import com.liferay.portlet.announcements.service.persistence.AnnouncementsEntryFinder;
import com.liferay.portlet.announcements.service.persistence.AnnouncementsEntryPersistence;
import com.liferay.portlet.announcements.service.persistence.AnnouncementsFlagPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the announcements flag local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.announcements.service.impl.AnnouncementsFlagLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.announcements.service.impl.AnnouncementsFlagLocalServiceImpl
 * @see com.liferay.portlet.announcements.service.AnnouncementsFlagLocalServiceUtil
 * @generated
 */
public abstract class AnnouncementsFlagLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements AnnouncementsFlagLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.announcements.service.AnnouncementsFlagLocalServiceUtil} to access the announcements flag local service.
	 */

	/**
	 * Adds the announcements flag to the database. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AnnouncementsFlag addAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag) throws SystemException {
		announcementsFlag.setNew(true);

		return announcementsFlagPersistence.update(announcementsFlag, false);
	}

	/**
	 * Creates a new announcements flag with the primary key. Does not add the announcements flag to the database.
	 *
	 * @param flagId the primary key for the new announcements flag
	 * @return the new announcements flag
	 */
	public AnnouncementsFlag createAnnouncementsFlag(long flagId) {
		return announcementsFlagPersistence.create(flagId);
	}

	/**
	 * Deletes the announcements flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param flagId the primary key of the announcements flag
	 * @return the announcements flag that was removed
	 * @throws PortalException if a announcements flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public AnnouncementsFlag deleteAnnouncementsFlag(long flagId)
		throws PortalException, SystemException {
		return announcementsFlagPersistence.remove(flagId);
	}

	/**
	 * Deletes the announcements flag from the database. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	public AnnouncementsFlag deleteAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag) throws SystemException {
		return announcementsFlagPersistence.remove(announcementsFlag);
	}

	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(AnnouncementsFlag.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return announcementsFlagPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return announcementsFlagPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return announcementsFlagPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return announcementsFlagPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public AnnouncementsFlag fetchAnnouncementsFlag(long flagId)
		throws SystemException {
		return announcementsFlagPersistence.fetchByPrimaryKey(flagId);
	}

	/**
	 * Returns the announcements flag with the primary key.
	 *
	 * @param flagId the primary key of the announcements flag
	 * @return the announcements flag
	 * @throws PortalException if a announcements flag with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AnnouncementsFlag getAnnouncementsFlag(long flagId)
		throws PortalException, SystemException {
		return announcementsFlagPersistence.findByPrimaryKey(flagId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return announcementsFlagPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the announcements flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of announcements flags
	 * @param end the upper bound of the range of announcements flags (not inclusive)
	 * @return the range of announcements flags
	 * @throws SystemException if a system exception occurred
	 */
	public List<AnnouncementsFlag> getAnnouncementsFlags(int start, int end)
		throws SystemException {
		return announcementsFlagPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of announcements flags.
	 *
	 * @return the number of announcements flags
	 * @throws SystemException if a system exception occurred
	 */
	public int getAnnouncementsFlagsCount() throws SystemException {
		return announcementsFlagPersistence.countAll();
	}

	/**
	 * Updates the announcements flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AnnouncementsFlag updateAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag) throws SystemException {
		return updateAnnouncementsFlag(announcementsFlag, true);
	}

	/**
	 * Updates the announcements flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @param merge whether to merge the announcements flag with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the announcements flag that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AnnouncementsFlag updateAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag, boolean merge)
		throws SystemException {
		announcementsFlag.setNew(false);

		return announcementsFlagPersistence.update(announcementsFlag, merge);
	}

	/**
	 * Returns the announcements delivery local service.
	 *
	 * @return the announcements delivery local service
	 */
	public AnnouncementsDeliveryLocalService getAnnouncementsDeliveryLocalService() {
		return announcementsDeliveryLocalService;
	}

	/**
	 * Sets the announcements delivery local service.
	 *
	 * @param announcementsDeliveryLocalService the announcements delivery local service
	 */
	public void setAnnouncementsDeliveryLocalService(
		AnnouncementsDeliveryLocalService announcementsDeliveryLocalService) {
		this.announcementsDeliveryLocalService = announcementsDeliveryLocalService;
	}

	/**
	 * Returns the announcements delivery remote service.
	 *
	 * @return the announcements delivery remote service
	 */
	public AnnouncementsDeliveryService getAnnouncementsDeliveryService() {
		return announcementsDeliveryService;
	}

	/**
	 * Sets the announcements delivery remote service.
	 *
	 * @param announcementsDeliveryService the announcements delivery remote service
	 */
	public void setAnnouncementsDeliveryService(
		AnnouncementsDeliveryService announcementsDeliveryService) {
		this.announcementsDeliveryService = announcementsDeliveryService;
	}

	/**
	 * Returns the announcements delivery persistence.
	 *
	 * @return the announcements delivery persistence
	 */
	public AnnouncementsDeliveryPersistence getAnnouncementsDeliveryPersistence() {
		return announcementsDeliveryPersistence;
	}

	/**
	 * Sets the announcements delivery persistence.
	 *
	 * @param announcementsDeliveryPersistence the announcements delivery persistence
	 */
	public void setAnnouncementsDeliveryPersistence(
		AnnouncementsDeliveryPersistence announcementsDeliveryPersistence) {
		this.announcementsDeliveryPersistence = announcementsDeliveryPersistence;
	}

	/**
	 * Returns the announcements entry local service.
	 *
	 * @return the announcements entry local service
	 */
	public AnnouncementsEntryLocalService getAnnouncementsEntryLocalService() {
		return announcementsEntryLocalService;
	}

	/**
	 * Sets the announcements entry local service.
	 *
	 * @param announcementsEntryLocalService the announcements entry local service
	 */
	public void setAnnouncementsEntryLocalService(
		AnnouncementsEntryLocalService announcementsEntryLocalService) {
		this.announcementsEntryLocalService = announcementsEntryLocalService;
	}

	/**
	 * Returns the announcements entry remote service.
	 *
	 * @return the announcements entry remote service
	 */
	public AnnouncementsEntryService getAnnouncementsEntryService() {
		return announcementsEntryService;
	}

	/**
	 * Sets the announcements entry remote service.
	 *
	 * @param announcementsEntryService the announcements entry remote service
	 */
	public void setAnnouncementsEntryService(
		AnnouncementsEntryService announcementsEntryService) {
		this.announcementsEntryService = announcementsEntryService;
	}

	/**
	 * Returns the announcements entry persistence.
	 *
	 * @return the announcements entry persistence
	 */
	public AnnouncementsEntryPersistence getAnnouncementsEntryPersistence() {
		return announcementsEntryPersistence;
	}

	/**
	 * Sets the announcements entry persistence.
	 *
	 * @param announcementsEntryPersistence the announcements entry persistence
	 */
	public void setAnnouncementsEntryPersistence(
		AnnouncementsEntryPersistence announcementsEntryPersistence) {
		this.announcementsEntryPersistence = announcementsEntryPersistence;
	}

	/**
	 * Returns the announcements entry finder.
	 *
	 * @return the announcements entry finder
	 */
	public AnnouncementsEntryFinder getAnnouncementsEntryFinder() {
		return announcementsEntryFinder;
	}

	/**
	 * Sets the announcements entry finder.
	 *
	 * @param announcementsEntryFinder the announcements entry finder
	 */
	public void setAnnouncementsEntryFinder(
		AnnouncementsEntryFinder announcementsEntryFinder) {
		this.announcementsEntryFinder = announcementsEntryFinder;
	}

	/**
	 * Returns the announcements flag local service.
	 *
	 * @return the announcements flag local service
	 */
	public AnnouncementsFlagLocalService getAnnouncementsFlagLocalService() {
		return announcementsFlagLocalService;
	}

	/**
	 * Sets the announcements flag local service.
	 *
	 * @param announcementsFlagLocalService the announcements flag local service
	 */
	public void setAnnouncementsFlagLocalService(
		AnnouncementsFlagLocalService announcementsFlagLocalService) {
		this.announcementsFlagLocalService = announcementsFlagLocalService;
	}

	/**
	 * Returns the announcements flag remote service.
	 *
	 * @return the announcements flag remote service
	 */
	public AnnouncementsFlagService getAnnouncementsFlagService() {
		return announcementsFlagService;
	}

	/**
	 * Sets the announcements flag remote service.
	 *
	 * @param announcementsFlagService the announcements flag remote service
	 */
	public void setAnnouncementsFlagService(
		AnnouncementsFlagService announcementsFlagService) {
		this.announcementsFlagService = announcementsFlagService;
	}

	/**
	 * Returns the announcements flag persistence.
	 *
	 * @return the announcements flag persistence
	 */
	public AnnouncementsFlagPersistence getAnnouncementsFlagPersistence() {
		return announcementsFlagPersistence;
	}

	/**
	 * Sets the announcements flag persistence.
	 *
	 * @param announcementsFlagPersistence the announcements flag persistence
	 */
	public void setAnnouncementsFlagPersistence(
		AnnouncementsFlagPersistence announcementsFlagPersistence) {
		this.announcementsFlagPersistence = announcementsFlagPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the resource finder.
	 *
	 * @return the resource finder
	 */
	public ResourceFinder getResourceFinder() {
		return resourceFinder;
	}

	/**
	 * Sets the resource finder.
	 *
	 * @param resourceFinder the resource finder
	 */
	public void setResourceFinder(ResourceFinder resourceFinder) {
		this.resourceFinder = resourceFinder;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.announcements.model.AnnouncementsFlag",
			announcementsFlagLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.announcements.model.AnnouncementsFlag");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return AnnouncementsFlag.class;
	}

	protected String getModelClassName() {
		return AnnouncementsFlag.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = announcementsFlagPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AnnouncementsDeliveryLocalService.class)
	protected AnnouncementsDeliveryLocalService announcementsDeliveryLocalService;
	@BeanReference(type = AnnouncementsDeliveryService.class)
	protected AnnouncementsDeliveryService announcementsDeliveryService;
	@BeanReference(type = AnnouncementsDeliveryPersistence.class)
	protected AnnouncementsDeliveryPersistence announcementsDeliveryPersistence;
	@BeanReference(type = AnnouncementsEntryLocalService.class)
	protected AnnouncementsEntryLocalService announcementsEntryLocalService;
	@BeanReference(type = AnnouncementsEntryService.class)
	protected AnnouncementsEntryService announcementsEntryService;
	@BeanReference(type = AnnouncementsEntryPersistence.class)
	protected AnnouncementsEntryPersistence announcementsEntryPersistence;
	@BeanReference(type = AnnouncementsEntryFinder.class)
	protected AnnouncementsEntryFinder announcementsEntryFinder;
	@BeanReference(type = AnnouncementsFlagLocalService.class)
	protected AnnouncementsFlagLocalService announcementsFlagLocalService;
	@BeanReference(type = AnnouncementsFlagService.class)
	protected AnnouncementsFlagService announcementsFlagService;
	@BeanReference(type = AnnouncementsFlagPersistence.class)
	protected AnnouncementsFlagPersistence announcementsFlagPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceFinder.class)
	protected ResourceFinder resourceFinder;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}