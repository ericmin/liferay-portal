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

package com.liferay.portal.service.impl;

import com.liferay.portal.NoSuchPasswordPolicyRelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.service.base.PasswordPolicyRelLocalServiceBaseImpl;
import com.liferay.portal.util.PortalUtil;

import java.util.List;

/**
 * @author Scott Lee
 */
public class PasswordPolicyRelLocalServiceImpl
	extends PasswordPolicyRelLocalServiceBaseImpl {

	public PasswordPolicyRel addPasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelPersistence.fetchByP_C_C(
				passwordPolicyId, classNameId, classPK);

		if (passwordPolicyRel != null) {
			return null;
		}

		try {

			// Ensure that models only have one password policy

			passwordPolicyRelPersistence.removeByC_C(classNameId, classPK);
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}

		long passwordPolicyRelId = counterLocalService.increment();

		passwordPolicyRel = passwordPolicyRelPersistence.create(
			passwordPolicyRelId);

		passwordPolicyRel.setPasswordPolicyId(passwordPolicyId);
		passwordPolicyRel.setClassNameId(classNameId);
		passwordPolicyRel.setClassPK(classPK);

		passwordPolicyRelPersistence.update(passwordPolicyRel, false);

		return passwordPolicyRel;
	}

	public void addPasswordPolicyRels(
			long passwordPolicyId, String className, long[] classPKs)
		throws SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			addPasswordPolicyRel(passwordPolicyId, className, classPKs[i]);
		}
	}

	public void deletePasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws SystemException {

		try {
			long classNameId = PortalUtil.getClassNameId(className);

			PasswordPolicyRel passwordPolicyRel =
				passwordPolicyRelPersistence.findByP_C_C(
					passwordPolicyId, classNameId, classPK);

			deletePasswordPolicyRel(passwordPolicyRel);
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}
	}

	public void deletePasswordPolicyRel(String className, long classPK)
		throws SystemException {

		try {
			long classNameId = PortalUtil.getClassNameId(className);

			PasswordPolicyRel passwordPolicyRel =
				passwordPolicyRelPersistence.findByC_C(classNameId, classPK);

			deletePasswordPolicyRel(passwordPolicyRel);
		}
		catch (NoSuchPasswordPolicyRelException nsppre) {
		}
	}

	public void deletePasswordPolicyRels(long passwordPolicyId)
		throws SystemException {

		List<PasswordPolicyRel> passwordPolicyRels =
			passwordPolicyRelPersistence.findByPasswordPolicyId(
				passwordPolicyId);

		for (PasswordPolicyRel passwordPolicyRel : passwordPolicyRels) {
			deletePasswordPolicyRel(passwordPolicyRel);
		}
	}

	public void deletePasswordPolicyRels(
			long passwordPolicyId, String className, long[] classPKs)
		throws SystemException {

		for (int i = 0; i < classPKs.length; i++) {
			deletePasswordPolicyRel(passwordPolicyId, className, classPKs[i]);
		}
	}

	public PasswordPolicyRel fetchPasswordPolicyRel(
			String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return passwordPolicyRelPersistence.fetchByC_C(classNameId, classPK);
	}

	public PasswordPolicyRel getPasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return passwordPolicyRelPersistence.findByP_C_C(
			passwordPolicyId, classNameId, classPK);
	}

	public PasswordPolicyRel getPasswordPolicyRel(
			String className, long classPK)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return passwordPolicyRelPersistence.findByC_C(classNameId, classPK);
	}

	public boolean hasPasswordPolicyRel(
			long passwordPolicyId, String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		PasswordPolicyRel passwordPolicyRel =
			passwordPolicyRelPersistence.fetchByP_C_C(
				passwordPolicyId, classNameId, classPK);

		if (passwordPolicyRel != null) {
			return true;
		}
		else {
			return false;
		}
	}

}