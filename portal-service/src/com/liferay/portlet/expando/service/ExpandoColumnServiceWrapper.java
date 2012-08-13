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

package com.liferay.portlet.expando.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ExpandoColumnService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExpandoColumnService
 * @generated
 */
public class ExpandoColumnServiceWrapper implements ExpandoColumnService,
	ServiceWrapper<ExpandoColumnService> {
	public ExpandoColumnServiceWrapper(
		ExpandoColumnService expandoColumnService) {
		_expandoColumnService = expandoColumnService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _expandoColumnService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_expandoColumnService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.expando.model.ExpandoColumn addColumn(
		long tableId, java.lang.String name, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoColumnService.addColumn(tableId, name, type);
	}

	public com.liferay.portlet.expando.model.ExpandoColumn addColumn(
		long tableId, java.lang.String name, int type,
		java.lang.Object defaultData)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoColumnService.addColumn(tableId, name, type, defaultData);
	}

	public void deleteColumn(long columnId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expandoColumnService.deleteColumn(columnId);
	}

	public com.liferay.portlet.expando.model.ExpandoColumn updateColumn(
		long columnId, java.lang.String name, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoColumnService.updateColumn(columnId, name, type);
	}

	public com.liferay.portlet.expando.model.ExpandoColumn updateColumn(
		long columnId, java.lang.String name, int type,
		java.lang.Object defaultData)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoColumnService.updateColumn(columnId, name, type,
			defaultData);
	}

	public com.liferay.portlet.expando.model.ExpandoColumn updateTypeSettings(
		long columnId, java.lang.String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expandoColumnService.updateTypeSettings(columnId, typeSettings);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ExpandoColumnService getWrappedExpandoColumnService() {
		return _expandoColumnService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedExpandoColumnService(
		ExpandoColumnService expandoColumnService) {
		_expandoColumnService = expandoColumnService;
	}

	public ExpandoColumnService getWrappedService() {
		return _expandoColumnService;
	}

	public void setWrappedService(ExpandoColumnService expandoColumnService) {
		_expandoColumnService = expandoColumnService;
	}

	private ExpandoColumnService _expandoColumnService;
}