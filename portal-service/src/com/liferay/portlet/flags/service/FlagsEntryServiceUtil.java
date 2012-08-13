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

package com.liferay.portlet.flags.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the flags entry remote service. This utility wraps {@link com.liferay.portlet.flags.service.impl.FlagsEntryServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FlagsEntryService
 * @see com.liferay.portlet.flags.service.base.FlagsEntryServiceBaseImpl
 * @see com.liferay.portlet.flags.service.impl.FlagsEntryServiceImpl
 * @generated
 */
public class FlagsEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portlet.flags.service.impl.FlagsEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static void addEntry(java.lang.String className, long classPK,
		java.lang.String reporterEmailAddress, long reportedUserId,
		java.lang.String contentTitle, java.lang.String contentURL,
		java.lang.String reason,
		com.liferay.portal.service.ServiceContext serviceContext) {
		getService()
			.addEntry(className, classPK, reporterEmailAddress, reportedUserId,
			contentTitle, contentURL, reason, serviceContext);
	}

	public static FlagsEntryService getService() {
		if (_service == null) {
			_service = (FlagsEntryService)PortalBeanLocatorUtil.locate(FlagsEntryService.class.getName());

			ReferenceRegistry.registerReference(FlagsEntryServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(FlagsEntryService service) {
	}

	private static FlagsEntryService _service;
}