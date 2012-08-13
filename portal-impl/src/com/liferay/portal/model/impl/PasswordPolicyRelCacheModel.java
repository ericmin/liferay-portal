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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.PasswordPolicyRel;

import java.io.Serializable;

/**
 * The cache model class for representing PasswordPolicyRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PasswordPolicyRel
 * @generated
 */
public class PasswordPolicyRelCacheModel implements CacheModel<PasswordPolicyRel>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{passwordPolicyRelId=");
		sb.append(passwordPolicyRelId);
		sb.append(", passwordPolicyId=");
		sb.append(passwordPolicyId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	public PasswordPolicyRel toEntityModel() {
		PasswordPolicyRelImpl passwordPolicyRelImpl = new PasswordPolicyRelImpl();

		passwordPolicyRelImpl.setPasswordPolicyRelId(passwordPolicyRelId);
		passwordPolicyRelImpl.setPasswordPolicyId(passwordPolicyId);
		passwordPolicyRelImpl.setClassNameId(classNameId);
		passwordPolicyRelImpl.setClassPK(classPK);

		passwordPolicyRelImpl.resetOriginalValues();

		return passwordPolicyRelImpl;
	}

	public long passwordPolicyRelId;
	public long passwordPolicyId;
	public long classNameId;
	public long classPK;
}