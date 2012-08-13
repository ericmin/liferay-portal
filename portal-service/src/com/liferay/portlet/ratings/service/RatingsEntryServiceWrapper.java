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

package com.liferay.portlet.ratings.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link RatingsEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       RatingsEntryService
 * @generated
 */
public class RatingsEntryServiceWrapper implements RatingsEntryService,
	ServiceWrapper<RatingsEntryService> {
	public RatingsEntryServiceWrapper(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ratingsEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ratingsEntryService.setBeanIdentifier(beanIdentifier);
	}

	public void deleteEntry(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ratingsEntryService.deleteEntry(className, classPK);
	}

	public com.liferay.portlet.ratings.model.RatingsEntry updateEntry(
		java.lang.String className, long classPK, double score)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ratingsEntryService.updateEntry(className, classPK, score);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public RatingsEntryService getWrappedRatingsEntryService() {
		return _ratingsEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedRatingsEntryService(
		RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	public RatingsEntryService getWrappedService() {
		return _ratingsEntryService;
	}

	public void setWrappedService(RatingsEntryService ratingsEntryService) {
		_ratingsEntryService = ratingsEntryService;
	}

	private RatingsEntryService _ratingsEntryService;
}