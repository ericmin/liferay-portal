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

package com.liferay.portal.upgrade.v5_1_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeMessageBoards extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateGroupId();
		updateMessageClassNameId();
		updateMessageFlagThreadId();
		updateMessagePriority();
	}

	protected void updateGroupId() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("update MBCategory set threadCount = (select count(*) from ");
		sb.append("MBThread where MBThread.categoryId = ");
		sb.append("MBCategory.categoryId)");

		runSQL(sb.toString());

		sb = new StringBuilder();

		sb.append("update MBCategory set messageCount = (select count(*) ");
		sb.append("from MBMessage where MBMessage.categoryId = ");
		sb.append("MBCategory.categoryId)");

		runSQL(sb.toString());

		sb = new StringBuilder();

		sb.append("update MBMessage set groupId = (select groupId from ");
		sb.append("MBCategory where MBCategory.categoryId = ");
		sb.append("MBMessage.categoryId)");

		runSQL(sb.toString());

		sb = new StringBuilder();

		sb.append("update MBThread set groupId = (select groupId from ");
		sb.append("MBCategory where MBCategory.categoryId = ");
		sb.append("MBThread.categoryId)");

		runSQL(sb.toString());
	}

	protected void updateMessageClassNameId() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("update MBMessage set classNameId = (select classNameId ");
		sb.append("from MBDiscussion where MBDiscussion.threadId = ");
		sb.append("MBMessage.threadId), classPK = (select classPK from ");
		sb.append("MBDiscussion where MBDiscussion.threadId = ");
		sb.append("MBMessage.threadId)");

		runSQL(sb.toString());
	}

	protected void updateMessageFlagThreadId() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("update MBMessageFlag set threadId = (select threadId from ");
		sb.append("MBMessage where MBMessage.messageId = ");
		sb.append("MBMessageFlag.messageId)");

		runSQL(sb.toString());
	}

	protected void updateMessagePriority() throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("update MBMessage set priority = (select priority from ");
		sb.append("MBThread where MBThread.threadId = MBMessage.threadId)");

		runSQL(sb.toString());
	}

}