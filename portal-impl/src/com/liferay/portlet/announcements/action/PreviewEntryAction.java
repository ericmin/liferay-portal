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

package com.liferay.portlet.announcements.action;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.model.impl.AnnouncementsEntryImpl;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author David Truong
 */
public class PreviewEntryAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();
		Date now = new Date();

		String[] distributionScopeParts = StringUtil.split(
			ParamUtil.getString(actionRequest, "distributionScope"));

		long classNameId = 0;
		long classPK = 0;

		if (distributionScopeParts.length == 2) {
			classNameId = GetterUtil.getLong(distributionScopeParts[0]);

			if (classNameId > 0) {
				classPK = GetterUtil.getLong(distributionScopeParts[1]);
			}
		}

		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String url = ParamUtil.getString(actionRequest, "url");
		String type = ParamUtil.getString(actionRequest, "type");
		int priority = ParamUtil.getInteger(actionRequest, "priority");
		boolean alert = ParamUtil.getBoolean(actionRequest, "alert");

		AnnouncementsEntry entry = new AnnouncementsEntryImpl();

		entry.setCompanyId(user.getCompanyId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(now);
		entry.setModifiedDate(now);
		entry.setClassNameId(classNameId);
		entry.setClassPK(classPK);
		entry.setTitle(title);
		entry.setContent(content);
		entry.setUrl(url);
		entry.setType(type);
		entry.setDisplayDate(now);
		entry.setExpirationDate(now);
		entry.setPriority(priority);
		entry.setAlert(alert);

		actionRequest.setAttribute(WebKeys.ANNOUNCEMENTS_ENTRY, entry);
	}

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		return mapping.findForward(
			getForward(renderRequest, "portlet.announcements.preview_entry"));
	}

}