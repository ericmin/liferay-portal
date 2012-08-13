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

package com.liferay.portlet.calendar.asset;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.asset.model.BaseAssetRenderer;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.permission.CalEventPermission;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

/**
 * @author Juan Fernández
 * @author Sergio González
 */
public class CalEventAssetRenderer extends BaseAssetRenderer {

	public CalEventAssetRenderer(CalEvent event) {
		_event = event;
	}

	public long getClassPK() {
		return _event.getEventId();
	}

	@Override
	public String getDiscussionPath() {
		if (PropsValues.CALENDAR_EVENT_COMMENTS_ENABLED) {
			return "edit_event_discussion";
		}
		else {
			return null;
		}
	}

	public long getGroupId() {
		return _event.getGroupId();
	}

	public String getSummary(Locale locale) {
		return HtmlUtil.extractText(_event.getDescription());
	}

	public String getTitle(Locale locale) {
		return _event.getTitle();
	}

	@Override
	public PortletURL getURLEdit(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest), PortletKeys.CALENDAR,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("struts_action", "/calendar/edit_event");
		portletURL.setParameter("eventId", String.valueOf(_event.getEventId()));

		return portletURL;
	}

	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			PortletKeys.CALENDAR, PortletRequest.RENDER_PHASE);

		portletURL.setWindowState(windowState);

		portletURL.setParameter("struts_action", "/calendar/view_event");
		portletURL.setParameter("eventId", String.valueOf(_event.getEventId()));

		return portletURL;
	}

	@Override
	public String getURLViewInContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		String noSuchEntryRedirect) {

		return getURLViewInContext(
			liferayPortletRequest, noSuchEntryRedirect, "/calendar/find_event",
			"eventId", _event.getEventId());
	}

	public long getUserId() {
		return _event.getUserId();
	}

	public String getUserName() {
		return _event.getUserName();
	}

	public String getUuid() {
		return _event.getUuid();
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		return CalEventPermission.contains(
			permissionChecker, _event, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		return CalEventPermission.contains(
			permissionChecker, _event, ActionKeys.VIEW);
	}

	@Override
	public boolean isPrintable() {
		return true;
	}

	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String template)
		throws Exception {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			renderRequest.setAttribute(WebKeys.CALENDAR_EVENT, _event);

			return "/html/portlet/calendar/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/date.png";
	}

	private CalEvent _event;

}