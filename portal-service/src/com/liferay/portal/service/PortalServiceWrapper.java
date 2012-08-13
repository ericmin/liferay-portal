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
 * This class is a wrapper for {@link PortalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PortalService
 * @generated
 */
public class PortalServiceWrapper implements PortalService,
	ServiceWrapper<PortalService> {
	public PortalServiceWrapper(PortalService portalService) {
		_portalService = portalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _portalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_portalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.String getAutoDeployDirectory()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portalService.getAutoDeployDirectory();
	}

	public int getBuildNumber() {
		return _portalService.getBuildNumber();
	}

	public void testAddClassName_Rollback(java.lang.String classNameValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalService.testAddClassName_Rollback(classNameValue);
	}

	public void testAddClassName_Success(java.lang.String classNameValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalService.testAddClassName_Success(classNameValue);
	}

	public void testAddClassNameAndTestTransactionPortletBar_PortalRollback(
		java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalService.testAddClassNameAndTestTransactionPortletBar_PortalRollback(transactionPortletBarText);
	}

	public void testAddClassNameAndTestTransactionPortletBar_PortletRollback(
		java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalService.testAddClassNameAndTestTransactionPortletBar_PortletRollback(transactionPortletBarText);
	}

	public void testAddClassNameAndTestTransactionPortletBar_Success(
		java.lang.String transactionPortletBarText)
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalService.testAddClassNameAndTestTransactionPortletBar_Success(transactionPortletBarText);
	}

	public void testCounterIncrement_Rollback()
		throws com.liferay.portal.kernel.exception.SystemException {
		_portalService.testCounterIncrement_Rollback();
	}

	public void testDeleteClassName()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_portalService.testDeleteClassName();
	}

	public int testGetBuildNumber() {
		return _portalService.testGetBuildNumber();
	}

	public void testGetUserId() {
		_portalService.testGetUserId();
	}

	public boolean testHasClassName()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _portalService.testHasClassName();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PortalService getWrappedPortalService() {
		return _portalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPortalService(PortalService portalService) {
		_portalService = portalService;
	}

	public PortalService getWrappedService() {
		return _portalService;
	}

	public void setWrappedService(PortalService portalService) {
		_portalService = portalService;
	}

	private PortalService _portalService;
}