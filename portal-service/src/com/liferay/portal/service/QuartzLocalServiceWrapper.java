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
 * This class is a wrapper for {@link QuartzLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       QuartzLocalService
 * @generated
 */
public class QuartzLocalServiceWrapper implements QuartzLocalService,
	ServiceWrapper<QuartzLocalService> {
	public QuartzLocalServiceWrapper(QuartzLocalService quartzLocalService) {
		_quartzLocalService = quartzLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _quartzLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_quartzLocalService.setBeanIdentifier(beanIdentifier);
	}

	public void checkQuartzTables()
		throws com.liferay.portal.kernel.exception.SystemException {
		_quartzLocalService.checkQuartzTables();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public QuartzLocalService getWrappedQuartzLocalService() {
		return _quartzLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedQuartzLocalService(
		QuartzLocalService quartzLocalService) {
		_quartzLocalService = quartzLocalService;
	}

	public QuartzLocalService getWrappedService() {
		return _quartzLocalService;
	}

	public void setWrappedService(QuartzLocalService quartzLocalService) {
		_quartzLocalService = quartzLocalService;
	}

	private QuartzLocalService _quartzLocalService;
}