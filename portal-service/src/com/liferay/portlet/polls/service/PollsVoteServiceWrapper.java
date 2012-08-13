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
 * This class is a wrapper for {@link PollsVoteService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PollsVoteService
 * @generated
 */
public class PollsVoteServiceWrapper implements PollsVoteService,
	ServiceWrapper<PollsVoteService> {
	public PollsVoteServiceWrapper(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pollsVoteService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsVoteService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.polls.model.PollsVote addVote(long questionId,
		long choiceId, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteService.addVote(questionId, choiceId, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PollsVoteService getWrappedPollsVoteService() {
		return _pollsVoteService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPollsVoteService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	public PollsVoteService getWrappedService() {
		return _pollsVoteService;
	}

	public void setWrappedService(PollsVoteService pollsVoteService) {
		_pollsVoteService = pollsVoteService;
	}

	private PollsVoteService _pollsVoteService;
}