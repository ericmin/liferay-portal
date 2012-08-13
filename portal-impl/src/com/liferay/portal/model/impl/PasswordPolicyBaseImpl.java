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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.service.PasswordPolicyLocalServiceUtil;

/**
 * The extended model base implementation for the PasswordPolicy service. Represents a row in the &quot;PasswordPolicy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PasswordPolicyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyImpl
 * @see com.liferay.portal.model.PasswordPolicy
 * @generated
 */
public abstract class PasswordPolicyBaseImpl extends PasswordPolicyModelImpl
	implements PasswordPolicy {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a password policy model instance should use the {@link PasswordPolicy} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			PasswordPolicyLocalServiceUtil.addPasswordPolicy(this);
		}
		else {
			PasswordPolicyLocalServiceUtil.updatePasswordPolicy(this);
		}
	}
}