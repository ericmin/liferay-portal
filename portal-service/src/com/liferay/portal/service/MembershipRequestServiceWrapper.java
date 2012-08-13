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
 * This class is a wrapper for {@link MembershipRequestService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MembershipRequestService
 * @generated
 */
public class MembershipRequestServiceWrapper implements MembershipRequestService,
	ServiceWrapper<MembershipRequestService> {
	public MembershipRequestServiceWrapper(
		MembershipRequestService membershipRequestService) {
		_membershipRequestService = membershipRequestService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _membershipRequestService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_membershipRequestService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.MembershipRequest addMembershipRequest(
		long groupId, java.lang.String comments,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _membershipRequestService.addMembershipRequest(groupId,
			comments, serviceContext);
	}

	public void deleteMembershipRequests(long groupId, int statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_membershipRequestService.deleteMembershipRequests(groupId, statusId);
	}

	public com.liferay.portal.model.MembershipRequest getMembershipRequest(
		long membershipRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _membershipRequestService.getMembershipRequest(membershipRequestId);
	}

	public void updateStatus(long membershipRequestId,
		java.lang.String reviewComments, int statusId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_membershipRequestService.updateStatus(membershipRequestId,
			reviewComments, statusId, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MembershipRequestService getWrappedMembershipRequestService() {
		return _membershipRequestService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMembershipRequestService(
		MembershipRequestService membershipRequestService) {
		_membershipRequestService = membershipRequestService;
	}

	public MembershipRequestService getWrappedService() {
		return _membershipRequestService;
	}

	public void setWrappedService(
		MembershipRequestService membershipRequestService) {
		_membershipRequestService = membershipRequestService;
	}

	private MembershipRequestService _membershipRequestService;
}