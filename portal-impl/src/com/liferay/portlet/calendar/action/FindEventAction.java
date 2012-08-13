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

package com.liferay.portlet.calendar.action;

import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class FindEventAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		CalEvent event = CalEventLocalServiceUtil.getEvent(primaryKey);

		return event.getGroupId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "eventId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		return "/calendar/view_event";
	}

	@Override
	protected String[] initPortletIds() {
		return new String[] {PortletKeys.CALENDAR};
	}

}