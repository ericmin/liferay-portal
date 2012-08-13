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

package com.liferay.portlet.mobiledevicerules.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MDRRuleService}.
 * </p>
 *
 * @author    Edward C. Han
 * @see       MDRRuleService
 * @generated
 */
public class MDRRuleServiceWrapper implements MDRRuleService,
	ServiceWrapper<MDRRuleService> {
	public MDRRuleServiceWrapper(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _mdrRuleService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mdrRuleService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRule addRule(
		long ruleGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleService.addRule(ruleGroupId, nameMap, descriptionMap,
			type, typeSettings, serviceContext);
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRule addRule(
		long ruleGroupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleService.addRule(ruleGroupId, nameMap, descriptionMap,
			type, typeSettings, serviceContext);
	}

	public void deleteRule(long ruleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_mdrRuleService.deleteRule(ruleId);
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRule fetchRule(
		long ruleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleService.fetchRule(ruleId);
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRule getRule(
		long ruleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleService.getRule(ruleId);
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRule updateRule(
		long ruleId, java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleService.updateRule(ruleId, nameMap, descriptionMap,
			type, typeSettings, serviceContext);
	}

	public com.liferay.portlet.mobiledevicerules.model.MDRRule updateRule(
		long ruleId, java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type,
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mdrRuleService.updateRule(ruleId, nameMap, descriptionMap,
			type, typeSettingsProperties, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MDRRuleService getWrappedMDRRuleService() {
		return _mdrRuleService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMDRRuleService(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	public MDRRuleService getWrappedService() {
		return _mdrRuleService;
	}

	public void setWrappedService(MDRRuleService mdrRuleService) {
		_mdrRuleService = mdrRuleService;
	}

	private MDRRuleService _mdrRuleService;
}