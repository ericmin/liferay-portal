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

package com.liferay.portlet.mobiledevicerules.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceModel
 * @see com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupInstanceImpl
 * @see com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupInstanceModelImpl
 * @generated
 */
public interface MDRRuleGroupInstance extends MDRRuleGroupInstanceModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.mobiledevicerules.model.impl.MDRRuleGroupInstanceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.portlet.mobiledevicerules.model.MDRAction> getActions()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup getRuleGroup()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}