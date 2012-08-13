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
 * This class is a wrapper for {@link LayoutSetBranchService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutSetBranchService
 * @generated
 */
public class LayoutSetBranchServiceWrapper implements LayoutSetBranchService,
	ServiceWrapper<LayoutSetBranchService> {
	public LayoutSetBranchServiceWrapper(
		LayoutSetBranchService layoutSetBranchService) {
		_layoutSetBranchService = layoutSetBranchService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _layoutSetBranchService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutSetBranchService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name,
		java.lang.String description, boolean master,
		long copyLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchService.addLayoutSetBranch(groupId,
			privateLayout, name, description, master, copyLayoutSetBranchId,
			serviceContext);
	}

	public void deleteLayoutSetBranch(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetBranchService.deleteLayoutSetBranch(layoutSetBranchId);
	}

	public java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranches(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchService.getLayoutSetBranches(groupId,
			privateLayout);
	}

	public com.liferay.portal.model.LayoutSetBranch mergeLayoutSetBranch(
		long layoutSetBranchId, long mergeLayoutSetBranchId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchService.mergeLayoutSetBranch(layoutSetBranchId,
			mergeLayoutSetBranchId, serviceContext);
	}

	public com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		long groupId, long layoutSetBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetBranchService.updateLayoutSetBranch(groupId,
			layoutSetBranchId, name, description, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LayoutSetBranchService getWrappedLayoutSetBranchService() {
		return _layoutSetBranchService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLayoutSetBranchService(
		LayoutSetBranchService layoutSetBranchService) {
		_layoutSetBranchService = layoutSetBranchService;
	}

	public LayoutSetBranchService getWrappedService() {
		return _layoutSetBranchService;
	}

	public void setWrappedService(LayoutSetBranchService layoutSetBranchService) {
		_layoutSetBranchService = layoutSetBranchService;
	}

	private LayoutSetBranchService _layoutSetBranchService;
}