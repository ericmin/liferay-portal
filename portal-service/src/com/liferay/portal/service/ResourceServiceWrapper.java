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
 * This class is a wrapper for {@link ResourceService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ResourceService
 * @generated
 */
public class ResourceServiceWrapper implements ResourceService,
	ServiceWrapper<ResourceService> {
	public ResourceServiceWrapper(ResourceService resourceService) {
		_resourceService = resourceService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _resourceService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_resourceService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.Resource getResource(long companyId,
		java.lang.String name, int scope, java.lang.String primKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _resourceService.getResource(companyId, name, scope, primKey);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ResourceService getWrappedResourceService() {
		return _resourceService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedResourceService(ResourceService resourceService) {
		_resourceService = resourceService;
	}

	public ResourceService getWrappedService() {
		return _resourceService;
	}

	public void setWrappedService(ResourceService resourceService) {
		_resourceService = resourceService;
	}

	private ResourceService _resourceService;
}