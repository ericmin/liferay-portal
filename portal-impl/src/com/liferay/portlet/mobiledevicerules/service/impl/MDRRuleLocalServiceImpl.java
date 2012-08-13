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

package com.liferay.portlet.mobiledevicerules.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.mobiledevicerules.model.MDRRule;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.base.MDRRuleLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Edward C. Han
 */
public class MDRRuleLocalServiceImpl extends MDRRuleLocalServiceBaseImpl {

	public MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);
		Date now = new Date();

		long ruleId = counterLocalService.increment();

		MDRRule rule = mdrRulePersistence.create(ruleId);

		rule.setUuid(serviceContext.getUuid());
		rule.setGroupId(ruleGroup.getGroupId());
		rule.setCompanyId(serviceContext.getCompanyId());
		rule.setCreateDate(serviceContext.getCreateDate(now));
		rule.setModifiedDate(serviceContext.getModifiedDate(now));
		rule.setUserId(user.getUserId());
		rule.setUserName(user.getFullName());
		rule.setRuleGroupId(ruleGroupId);
		rule.setNameMap(nameMap);
		rule.setDescriptionMap(descriptionMap);
		rule.setType(type);
		rule.setTypeSettings(typeSettings);

		rule = updateMDRRule(rule, false);

		ruleGroup.setModifiedDate(now);

		mdrRuleGroupPersistence.update(ruleGroup, false);

		return rule;
	}

	public MDRRule addRule(
			long ruleGroupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addRule(
			ruleGroupId, nameMap, descriptionMap, type,
			typeSettingsProperties.toString(), serviceContext);
	}

	public MDRRule copyRule(
			long ruleId, long ruleGroupId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		return copyRule(rule, ruleGroupId, serviceContext);
	}

	public MDRRule copyRule(
			MDRRule rule, long ruleGroupId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			ruleGroupId);

		MDRRule newRule = addRule(
			ruleGroup.getRuleGroupId(), rule.getNameMap(),
			rule.getDescriptionMap(), rule.getType(), rule.getTypeSettings(),
			serviceContext);

		return newRule;
	}

	public void deleteRule(long ruleId) throws SystemException {
		MDRRule rule = mdrRulePersistence.fetchByPrimaryKey(ruleId);

		if (rule != null) {
			deleteRule(rule);
		}
	}

	public void deleteRule(MDRRule rule) throws SystemException {
		mdrRulePersistence.remove(rule);

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.fetchByPrimaryKey(
			rule.getRuleGroupId());

		if (ruleGroup != null) {
			ruleGroup.setModifiedDate(new Date());

			mdrRuleGroupPersistence.update(ruleGroup, false);
		}
	}

	public void deleteRules(long ruleGroupId) throws SystemException {
		List<MDRRule> rules = mdrRulePersistence.findByRuleGroupId(ruleGroupId);

		for (MDRRule rule : rules) {
			deleteRule(rule);
		}
	}

	public MDRRule fetchRule(long ruleId) throws SystemException {
		return mdrRulePersistence.fetchByPrimaryKey(ruleId);
	}

	public MDRRule getRule(long ruleId)
		throws PortalException, SystemException {

		return mdrRulePersistence.findByPrimaryKey(ruleId);
	}

	public List<MDRRule> getRules(long ruleGroupId) throws SystemException {
		return mdrRulePersistence.findByRuleGroupId(ruleGroupId);
	}

	public List<MDRRule> getRules(long ruleGroupId, int start, int end)
		throws SystemException {

		return mdrRulePersistence.findByRuleGroupId(ruleGroupId, start, end);
	}

	public int getRulesCount(long ruleGroupId) throws SystemException {
		return mdrRulePersistence.countByRuleGroupId(ruleGroupId);
	}

	public MDRRule updateRule(
			long ruleId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		MDRRule rule = mdrRulePersistence.findByPrimaryKey(ruleId);

		rule.setModifiedDate(serviceContext.getModifiedDate(null));
		rule.setNameMap(nameMap);
		rule.setDescriptionMap(descriptionMap);
		rule.setType(type);
		rule.setTypeSettings(typeSettings);

		mdrRulePersistence.update(rule, false);

		MDRRuleGroup ruleGroup = mdrRuleGroupPersistence.findByPrimaryKey(
			rule.getRuleGroupId());

		ruleGroup.setModifiedDate(serviceContext.getModifiedDate(null));

		mdrRuleGroupPersistence.update(ruleGroup, false);

		return rule;
	}

	public MDRRule updateRule(
			long ruleId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, String type,
			UnicodeProperties typeSettingsProperties,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return updateRule(
			ruleId, nameMap, descriptionMap, type,
			typeSettingsProperties.toString(), serviceContext);
	}

}