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

package com.liferay.mail.util;

import com.liferay.mail.model.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DummyHook implements Hook {

	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy) {

		if (_log.isDebugEnabled()) {
			_log.debug("addForward");
		}
	}

	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress) {

		if (_log.isDebugEnabled()) {
			_log.debug("addUser");
		}
	}

	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage) {

		if (_log.isDebugEnabled()) {
			_log.debug("addVacationMessage");
		}
	}

	public void deleteEmailAddress(long companyId, long userId) {
		if (_log.isDebugEnabled()) {
			_log.debug("deleteEmailAddress");
		}
	}

	public void deleteUser(long companyId, long userId) {
		if (_log.isDebugEnabled()) {
			_log.debug("deleteUser");
		}
	}

	public void updateBlocked(
		long companyId, long userId, List<String> blocked) {

		if (_log.isDebugEnabled()) {
			_log.debug("updateBlocked");
		}
	}

	public void updateEmailAddress(
		long companyId, long userId, String emailAddress) {

		if (_log.isDebugEnabled()) {
			_log.debug("updateEmailAddress");
		}
	}

	public void updatePassword(long companyId, long userId, String password) {
		if (_log.isDebugEnabled()) {
			_log.debug("updatePassword");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DummyHook.class);

}