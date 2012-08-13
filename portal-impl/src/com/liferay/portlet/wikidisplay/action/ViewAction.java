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

package com.liferay.portlet.wikidisplay.action;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.struts.PortletAction;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.wiki.NoSuchNodeException;
import com.liferay.portlet.wiki.NoSuchPageException;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageConstants;
import com.liferay.portlet.wiki.service.WikiNodeServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class ViewAction extends PortletAction {

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		try {
			PortletPreferences preferences = renderRequest.getPreferences();

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long nodeId = GetterUtil.getLong(
				preferences.getValue("nodeId", StringPool.BLANK));
			String title = ParamUtil.getString(
				renderRequest, "title",
				preferences.getValue("title", WikiPageConstants.FRONT_PAGE));

			WikiNode node = WikiNodeServiceUtil.getNode(nodeId);

			if (node.getGroupId() != themeDisplay.getScopeGroupId()) {
				throw new NoSuchNodeException();
			}

			WikiPage wikiPage = null;

			try {
				wikiPage = WikiPageServiceUtil.getPage(nodeId, title);
			}
			catch (NoSuchPageException nspe) {
				wikiPage = WikiPageServiceUtil.getPage(
					nodeId, WikiPageConstants.FRONT_PAGE);
			}

			renderRequest.setAttribute(WebKeys.WIKI_NODE, node);
			renderRequest.setAttribute(WebKeys.WIKI_PAGE, wikiPage);

			return mapping.findForward("portlet.wiki_display.view");
		}
		catch (NoSuchNodeException nsne) {
			return mapping.findForward("/portal/portlet_not_setup");
		}
		catch (NoSuchPageException nspe) {
			return mapping.findForward("/portal/portlet_not_setup");
		}
		catch (PrincipalException pe) {
			SessionErrors.add(renderRequest, pe.getClass());

			return mapping.findForward("portlet.wiki_display.error");
		}
	}

}