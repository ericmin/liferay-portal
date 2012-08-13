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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.PasswordTracker;
import com.liferay.portal.model.User;
import com.liferay.portal.security.pwd.PwdEncryptor;
import com.liferay.portal.service.base.PasswordTrackerLocalServiceBaseImpl;

import java.util.Date;
import java.util.Iterator;

/**
 * @author Brian Wing Shun Chan
 * @author Scott Lee
 */
public class PasswordTrackerLocalServiceImpl
	extends PasswordTrackerLocalServiceBaseImpl {

	public void deletePasswordTrackers(long userId) throws SystemException {
		passwordTrackerPersistence.removeByUserId(userId);
	}

	public boolean isSameAsCurrentPassword(long userId, String newClearTextPwd)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		String currentPwd = user.getPassword();

		if (user.isPasswordEncrypted()) {
			String newEncPwd = PwdEncryptor.encrypt(
				newClearTextPwd, user.getPassword());

			if (currentPwd.equals(newEncPwd)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (currentPwd.equals(newClearTextPwd)) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	public boolean isValidPassword(long userId, String newClearTextPwd)
		throws PortalException, SystemException {

		PasswordPolicy passwordPolicy =
			passwordPolicyLocalService.getPasswordPolicyByUserId(userId);

		if (!passwordPolicy.getHistory()) {
			return true;
		}

		// Check password history

		int historyCount = 1;

		Iterator<PasswordTracker> itr = passwordTrackerPersistence.findByUserId(
			userId).iterator();

		while (itr.hasNext()) {
			if (historyCount >= passwordPolicy.getHistoryCount()) {
				break;
			}

			PasswordTracker passwordTracker = itr.next();

			String oldEncPwd = passwordTracker.getPassword();
			String newEncPwd = PwdEncryptor.encrypt(newClearTextPwd, oldEncPwd);

			if (oldEncPwd.equals(newEncPwd)) {
				return false;
			}

			historyCount++;
		}

		return true;
	}

	public void trackPassword(long userId, String encPassword)
		throws PortalException, SystemException {

		PasswordPolicy passwordPolicy =
			passwordPolicyLocalService.getPasswordPolicyByUserId(userId);

		if ((passwordPolicy != null) && passwordPolicy.isHistory()) {
			long passwordTrackerId = counterLocalService.increment();

			PasswordTracker passwordTracker = passwordTrackerPersistence.create(
				passwordTrackerId);

			passwordTracker.setUserId(userId);
			passwordTracker.setCreateDate(new Date());
			passwordTracker.setPassword(encPassword);

			passwordTrackerPersistence.update(passwordTracker, false);
		}
	}

}