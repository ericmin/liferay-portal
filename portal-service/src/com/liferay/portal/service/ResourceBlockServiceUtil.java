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

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the resource block remote service. This utility wraps {@link com.liferay.portal.service.impl.ResourceBlockServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockService
 * @see com.liferay.portal.service.base.ResourceBlockServiceBaseImpl
 * @see com.liferay.portal.service.impl.ResourceBlockServiceImpl
 * @generated
 */
public class ResourceBlockServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.ResourceBlockServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static void addCompanyScopePermission(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addCompanyScopePermission(scopeGroupId, companyId, name, roleId,
			actionId);
	}

	public static void addGroupScopePermission(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addGroupScopePermission(scopeGroupId, companyId, groupId, name,
			roleId, actionId);
	}

	public static void addIndividualScopePermission(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addIndividualScopePermission(companyId, groupId, name, primKey,
			roleId, actionId);
	}

	public static void removeAllGroupScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeAllGroupScopePermissions(scopeGroupId, companyId, name,
			roleId, actionId);
	}

	public static void removeCompanyScopePermission(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeCompanyScopePermission(scopeGroupId, companyId, name,
			roleId, actionId);
	}

	public static void removeGroupScopePermission(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeGroupScopePermission(scopeGroupId, companyId, groupId, name,
			roleId, actionId);
	}

	public static void removeIndividualScopePermission(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.lang.String actionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.removeIndividualScopePermission(companyId, groupId, name, primKey,
			roleId, actionId);
	}

	public static void setCompanyScopePermissions(long scopeGroupId,
		long companyId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setCompanyScopePermissions(scopeGroupId, companyId, name, roleId,
			actionIds);
	}

	public static void setGroupScopePermissions(long scopeGroupId,
		long companyId, long groupId, java.lang.String name, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setGroupScopePermissions(scopeGroupId, companyId, groupId, name,
			roleId, actionIds);
	}

	public static void setIndividualScopePermissions(long companyId,
		long groupId, java.lang.String name, long primKey, long roleId,
		java.util.List<java.lang.String> actionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setIndividualScopePermissions(companyId, groupId, name, primKey,
			roleId, actionIds);
	}

	public static void setIndividualScopePermissions(long companyId,
		long groupId, java.lang.String name, long primKey,
		java.util.Map<java.lang.Long, java.lang.String[]> roleIdsToActionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setIndividualScopePermissions(companyId, groupId, name, primKey,
			roleIdsToActionIds);
	}

	public static ResourceBlockService getService() {
		if (_service == null) {
			_service = (ResourceBlockService)PortalBeanLocatorUtil.locate(ResourceBlockService.class.getName());

			ReferenceRegistry.registerReference(ResourceBlockServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(ResourceBlockService service) {
	}

	private static ResourceBlockService _service;
}