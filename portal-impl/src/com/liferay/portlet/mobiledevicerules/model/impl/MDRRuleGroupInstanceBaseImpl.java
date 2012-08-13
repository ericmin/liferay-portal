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

package com.liferay.portlet.mobiledevicerules.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupInstanceLocalServiceUtil;

/**
 * The extended model base implementation for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MDRRuleGroupInstanceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceImpl
 * @see com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance
 * @generated
 */
public abstract class MDRRuleGroupInstanceBaseImpl
	extends MDRRuleGroupInstanceModelImpl implements MDRRuleGroupInstance {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a m d r rule group instance model instance should use the {@link MDRRuleGroupInstance} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			MDRRuleGroupInstanceLocalServiceUtil.addMDRRuleGroupInstance(this);
		}
		else {
			MDRRuleGroupInstanceLocalServiceUtil.updateMDRRuleGroupInstance(this);
		}
	}
}