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
 * This class is a wrapper for {@link LayoutSetPrototypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutSetPrototypeService
 * @generated
 */
public class LayoutSetPrototypeServiceWrapper
	implements LayoutSetPrototypeService,
		ServiceWrapper<LayoutSetPrototypeService> {
	public LayoutSetPrototypeServiceWrapper(
		LayoutSetPrototypeService layoutSetPrototypeService) {
		_layoutSetPrototypeService = layoutSetPrototypeService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _layoutSetPrototypeService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutSetPrototypeService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.LayoutSetPrototype addLayoutSetPrototype(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String description, boolean active,
		boolean layoutsUpdateable,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.addLayoutSetPrototype(nameMap,
			description, active, layoutsUpdateable, serviceContext);
	}

	public void deleteLayoutSetPrototype(long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_layoutSetPrototypeService.deleteLayoutSetPrototype(layoutSetPrototypeId);
	}

	public com.liferay.portal.model.LayoutSetPrototype getLayoutSetPrototype(
		long layoutSetPrototypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.getLayoutSetPrototype(layoutSetPrototypeId);
	}

	public java.util.List<com.liferay.portal.model.LayoutSetPrototype> search(
		long companyId, java.lang.Boolean active,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.search(companyId, active, obc);
	}

	public com.liferay.portal.model.LayoutSetPrototype updateLayoutSetPrototype(
		long layoutSetPrototypeId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String description, boolean active,
		boolean layoutsUpdateable,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.updateLayoutSetPrototype(layoutSetPrototypeId,
			nameMap, description, active, layoutsUpdateable, serviceContext);
	}

	public com.liferay.portal.model.LayoutSetPrototype updateLayoutSetPrototype(
		long layoutSetPrototypeId, java.lang.String settings)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutSetPrototypeService.updateLayoutSetPrototype(layoutSetPrototypeId,
			settings);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LayoutSetPrototypeService getWrappedLayoutSetPrototypeService() {
		return _layoutSetPrototypeService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLayoutSetPrototypeService(
		LayoutSetPrototypeService layoutSetPrototypeService) {
		_layoutSetPrototypeService = layoutSetPrototypeService;
	}

	public LayoutSetPrototypeService getWrappedService() {
		return _layoutSetPrototypeService;
	}

	public void setWrappedService(
		LayoutSetPrototypeService layoutSetPrototypeService) {
		_layoutSetPrototypeService = layoutSetPrototypeService;
	}

	private LayoutSetPrototypeService _layoutSetPrototypeService;
}