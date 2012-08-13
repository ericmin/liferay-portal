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

package com.liferay.portlet.polls.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PollsChoiceService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PollsChoiceService
 * @generated
 */
public class PollsChoiceServiceWrapper implements PollsChoiceService,
	ServiceWrapper<PollsChoiceService> {
	public PollsChoiceServiceWrapper(PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pollsChoiceService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsChoiceService.setBeanIdentifier(beanIdentifier);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PollsChoiceService getWrappedPollsChoiceService() {
		return _pollsChoiceService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPollsChoiceService(
		PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	public PollsChoiceService getWrappedService() {
		return _pollsChoiceService;
	}

	public void setWrappedService(PollsChoiceService pollsChoiceService) {
		_pollsChoiceService = pollsChoiceService;
	}

	private PollsChoiceService _pollsChoiceService;
}