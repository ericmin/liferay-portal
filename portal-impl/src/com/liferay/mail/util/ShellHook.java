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
import com.liferay.portal.kernel.util.ProcessUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsUtil;

import java.util.List;

/**
 * @author Michael Lawrence
 */
public class ShellHook implements Hook {

	public static final String SHELL_SCRIPT = PropsUtil.get(
		PropsKeys.MAIL_HOOK_SHELL_SCRIPT);

	public void addFilters(long companyId, long userId, List<String> filters) {
	}

	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy) {

		execute(
			new String[] {
				SHELL_SCRIPT, "addForward", String.valueOf(userId),
				StringUtil.merge(emailAddresses)
			}
		);
	}

	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress) {

		execute(
			new String[] {
				SHELL_SCRIPT, "addUser", String.valueOf(userId), password,
				firstName, middleName, lastName, emailAddress
			}
		);
	}

	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage) {

		execute(
			new String[] {
				SHELL_SCRIPT, "addVacationMessage", String.valueOf(userId),
				emailAddress, vacationMessage
			}
		);
	}

	public void deleteEmailAddress(long companyId, long userId) {
		execute(
			new String[] {
				SHELL_SCRIPT, "deleteEmailAddress", String.valueOf(userId)
			}
		);
	}

	public void deleteUser(long companyId, long userId) {
		execute(
			new String[] {
				SHELL_SCRIPT, "deleteUser", String.valueOf(userId)
			}
		);
	}

	public void updateBlocked(
		long companyId, long userId, List<String> blocked) {

		execute(
			new String[] {
				SHELL_SCRIPT, "updateBlocked", String.valueOf(userId),
				StringUtil.merge(blocked)
			}
		);
	}

	public void updateEmailAddress(
		long companyId, long userId, String emailAddress) {

		execute(
			new String[] {
				SHELL_SCRIPT, "updateEmailAddress", String.valueOf(userId),
				emailAddress
			}
		);
	}

	public void updatePassword(long companyId, long userId, String password) {
		execute(
			new String[] {
				SHELL_SCRIPT, "updatePassword", String.valueOf(userId), password
			}
		);
	}

	protected void execute(String cmdLine[]) {
		for (int i = 0; i < cmdLine.length; i++) {
			if (cmdLine[i].trim().length() == 0) {
				cmdLine[i] = StringPool.UNDERLINE;
			}
		}

		try {
			Runtime rt = Runtime.getRuntime();

			Process p = rt.exec(cmdLine);

			ProcessUtil.close(p);

			int exitValue = p.exitValue();

			if (exitValue != 0) {
				StringBundler sb = new StringBundler(cmdLine.length * 2);

				for (int i = 0; i < cmdLine.length; i++) {
					sb.append(cmdLine[i]);
					sb.append(StringPool.SPACE);
				}

				throw new IllegalArgumentException(sb.toString());
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ShellHook.class);

}