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
 * This class is a wrapper for {@link LayoutRevisionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LayoutRevisionService
 * @generated
 */
public class LayoutRevisionServiceWrapper implements LayoutRevisionService,
	ServiceWrapper<LayoutRevisionService> {
	public LayoutRevisionServiceWrapper(
		LayoutRevisionService layoutRevisionService) {
		_layoutRevisionService = layoutRevisionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _layoutRevisionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_layoutRevisionService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portal.model.LayoutRevision addLayoutRevision(
		long userId, long layoutSetBranchId, long layoutBranchId,
		long parentLayoutRevisionId, boolean head, long plid,
		long portletPreferencesPlid, boolean privateLayout,
		java.lang.String name, java.lang.String title,
		java.lang.String description, java.lang.String keywords,
		java.lang.String robots, java.lang.String typeSettings,
		boolean iconImage, long iconImageId, java.lang.String themeId,
		java.lang.String colorSchemeId, java.lang.String wapThemeId,
		java.lang.String wapColorSchemeId, java.lang.String css,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _layoutRevisionService.addLayoutRevision(userId,
			layoutSetBranchId, layoutBranchId, parentLayoutRevisionId, head,
			plid, portletPreferencesPlid, privateLayout, name, title,
			description, keywords, robots, typeSettings, iconImage,
			iconImageId, themeId, colorSchemeId, wapThemeId, wapColorSchemeId,
			css, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LayoutRevisionService getWrappedLayoutRevisionService() {
		return _layoutRevisionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLayoutRevisionService(
		LayoutRevisionService layoutRevisionService) {
		_layoutRevisionService = layoutRevisionService;
	}

	public LayoutRevisionService getWrappedService() {
		return _layoutRevisionService;
	}

	public void setWrappedService(LayoutRevisionService layoutRevisionService) {
		_layoutRevisionService = layoutRevisionService;
	}

	private LayoutRevisionService _layoutRevisionService;
}