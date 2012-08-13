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

package com.liferay.portlet.messageboards.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portlet.messageboards.LockedThreadException;
import com.liferay.portlet.messageboards.service.MBThreadServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Deepak Gothe
 * @author Sergio González
 */
public class DeleteThreadAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			deleteThreads(actionRequest, actionResponse);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof LockedThreadException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				setForward(actionRequest, "portlet.message_boards.error");
			}
			else {
				throw e;
			}
		}
	}

	protected void deleteThreads(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long threadId = ParamUtil.getLong(actionRequest, "threadId");

		if (threadId > 0) {
			MBThreadServiceUtil.deleteThread(threadId);
		}
		else {
			long[] deleteThreadIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "threadIds"), 0L);

			for (int i = 0; i < deleteThreadIds.length; i++) {
				MBThreadServiceUtil.deleteThread(deleteThreadIds[i]);
			}
		}
	}

}