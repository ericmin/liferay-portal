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

/**
 * <p>
 * This class is a wrapper for {@link EmailAddressLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EmailAddressLocalService
 * @generated
 */
public class EmailAddressLocalServiceWrapper implements EmailAddressLocalService,
	ServiceWrapper<EmailAddressLocalService> {
	public EmailAddressLocalServiceWrapper(
		EmailAddressLocalService emailAddressLocalService) {
		_emailAddressLocalService = emailAddressLocalService;
	}

	/**
	* Adds the email address to the database. Also notifies the appropriate model listeners.
	*
	* @param emailAddress the email address
	* @return the email address that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.EmailAddress addEmailAddress(
		com.liferay.portal.model.EmailAddress emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.addEmailAddress(emailAddress);
	}

	/**
	* Creates a new email address with the primary key. Does not add the email address to the database.
	*
	* @param emailAddressId the primary key for the new email address
	* @return the new email address
	*/
	public com.liferay.portal.model.EmailAddress createEmailAddress(
		long emailAddressId) {
		return _emailAddressLocalService.createEmailAddress(emailAddressId);
	}

	/**
	* Deletes the email address with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param emailAddressId the primary key of the email address
	* @return the email address that was removed
	* @throws PortalException if a email address with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.EmailAddress deleteEmailAddress(
		long emailAddressId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.deleteEmailAddress(emailAddressId);
	}

	/**
	* Deletes the email address from the database. Also notifies the appropriate model listeners.
	*
	* @param emailAddress the email address
	* @return the email address that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.EmailAddress deleteEmailAddress(
		com.liferay.portal.model.EmailAddress emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.deleteEmailAddress(emailAddress);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _emailAddressLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.model.EmailAddress fetchEmailAddress(
		long emailAddressId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.fetchEmailAddress(emailAddressId);
	}

	/**
	* Returns the email address with the primary key.
	*
	* @param emailAddressId the primary key of the email address
	* @return the email address
	* @throws PortalException if a email address with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.EmailAddress getEmailAddress(
		long emailAddressId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.getEmailAddress(emailAddressId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the email addresses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of email addresses
	* @param end the upper bound of the range of email addresses (not inclusive)
	* @return the range of email addresses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.model.EmailAddress> getEmailAddresses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.getEmailAddresses(start, end);
	}

	/**
	* Returns the number of email addresses.
	*
	* @return the number of email addresses
	* @throws SystemException if a system exception occurred
	*/
	public int getEmailAddressesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.getEmailAddressesCount();
	}

	/**
	* Updates the email address in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param emailAddress the email address
	* @return the email address that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.EmailAddress updateEmailAddress(
		com.liferay.portal.model.EmailAddress emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.updateEmailAddress(emailAddress);
	}

	/**
	* Updates the email address in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param emailAddress the email address
	* @param merge whether to merge the email address with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the email address that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.model.EmailAddress updateEmailAddress(
		com.liferay.portal.model.EmailAddress emailAddress, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.updateEmailAddress(emailAddress, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _emailAddressLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_emailAddressLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.EmailAddress addEmailAddress(long userId,
		java.lang.String className, long classPK, java.lang.String address,
		int typeId, boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.addEmailAddress(userId, className,
			classPK, address, typeId, primary);
	}

	public void deleteEmailAddresses(long companyId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		_emailAddressLocalService.deleteEmailAddresses(companyId, className,
			classPK);
	}

	public java.util.List<com.liferay.portal.model.EmailAddress> getEmailAddresses()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.getEmailAddresses();
	}

	public java.util.List<com.liferay.portal.model.EmailAddress> getEmailAddresses(
		long companyId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.getEmailAddresses(companyId,
			className, classPK);
	}

	public com.liferay.portal.model.EmailAddress updateEmailAddress(
		long emailAddressId, java.lang.String address, int typeId,
		boolean primary)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _emailAddressLocalService.updateEmailAddress(emailAddressId,
			address, typeId, primary);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public EmailAddressLocalService getWrappedEmailAddressLocalService() {
		return _emailAddressLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedEmailAddressLocalService(
		EmailAddressLocalService emailAddressLocalService) {
		_emailAddressLocalService = emailAddressLocalService;
	}

	public EmailAddressLocalService getWrappedService() {
		return _emailAddressLocalService;
	}

	public void setWrappedService(
		EmailAddressLocalService emailAddressLocalService) {
		_emailAddressLocalService = emailAddressLocalService;
	}

	private EmailAddressLocalService _emailAddressLocalService;
}