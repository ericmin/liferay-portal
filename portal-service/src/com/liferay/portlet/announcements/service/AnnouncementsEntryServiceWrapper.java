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

package com.liferay.portlet.announcements.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AnnouncementsEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AnnouncementsEntryService
 * @generated
 */
public class AnnouncementsEntryServiceWrapper
	implements AnnouncementsEntryService,
		ServiceWrapper<AnnouncementsEntryService> {
	public AnnouncementsEntryServiceWrapper(
		AnnouncementsEntryService announcementsEntryService) {
		_announcementsEntryService = announcementsEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _announcementsEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_announcementsEntryService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.announcements.model.AnnouncementsEntry addEntry(
		long plid, long classNameId, long classPK, java.lang.String title,
		java.lang.String content, java.lang.String url, java.lang.String type,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, int priority, boolean alert)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _announcementsEntryService.addEntry(plid, classNameId, classPK,
			title, content, url, type, displayDateMonth, displayDateDay,
			displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, priority, alert);
	}

	public void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_announcementsEntryService.deleteEntry(entryId);
	}

	public com.liferay.portlet.announcements.model.AnnouncementsEntry updateEntry(
		long entryId, java.lang.String title, java.lang.String content,
		java.lang.String url, java.lang.String type, int displayDateMonth,
		int displayDateDay, int displayDateYear, int displayDateHour,
		int displayDateMinute, int expirationDateMonth, int expirationDateDay,
		int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, int priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _announcementsEntryService.updateEntry(entryId, title, content,
			url, type, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, priority);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AnnouncementsEntryService getWrappedAnnouncementsEntryService() {
		return _announcementsEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAnnouncementsEntryService(
		AnnouncementsEntryService announcementsEntryService) {
		_announcementsEntryService = announcementsEntryService;
	}

	public AnnouncementsEntryService getWrappedService() {
		return _announcementsEntryService;
	}

	public void setWrappedService(
		AnnouncementsEntryService announcementsEntryService) {
		_announcementsEntryService = announcementsEntryService;
	}

	private AnnouncementsEntryService _announcementsEntryService;
}