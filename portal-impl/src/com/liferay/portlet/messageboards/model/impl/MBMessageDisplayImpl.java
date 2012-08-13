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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageDisplay;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.model.MBThreadConstants;
import com.liferay.portlet.messageboards.model.MBTreeWalker;
import com.liferay.portlet.messageboards.service.MBMessageLocalService;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class MBMessageDisplayImpl implements MBMessageDisplay {

	public MBMessageDisplayImpl(
		MBMessage message, MBMessage parentMessage, MBCategory category,
		MBThread thread, MBThread previousThread, MBThread nextThread,
		int status, String threadView,
		MBMessageLocalService messageLocalService) {

		_message = message;
		_parentMessage = parentMessage;
		_category = category;
		_thread = thread;

		if (!threadView.equals(MBThreadConstants.THREAD_VIEW_FLAT)) {
			_treeWalker = new MBTreeWalkerImpl(
				message, status, messageLocalService);
		}

		_previousThread = previousThread;
		_nextThread = nextThread;
		_threadView = threadView;
	}

	public MBCategory getCategory() {
		return _category;
	}

	public MBMessage getMessage() {
		return _message;
	}

	public MBThread getNextThread() {
		return _nextThread;
	}

	public MBMessage getParentMessage() {
		return _parentMessage;
	}

	public MBThread getPreviousThread() {
		return _previousThread;
	}

	public MBThread getThread() {
		return _thread;
	}

	public String getThreadView() {
		return _threadView;
	}

	public MBTreeWalker getTreeWalker() {
		return _treeWalker;
	}

	private MBCategory _category;
	private MBMessage _message;
	private MBThread _nextThread;
	private MBMessage _parentMessage;
	private MBThread _previousThread;
	private MBThread _thread;
	private String _threadView;
	private MBTreeWalker _treeWalker;

}