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

package com.liferay.portal.struts;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mika Koivisto
 */
public class StrutsPortletActionAdapter extends BaseStrutsPortletAction {

	public StrutsPortletActionAdapter(
		PortletAction portletAction, ActionMapping actionMapping,
		ActionForm actionForm) {

		_portletAction = portletAction;
		_actionMapping = actionMapping;
		_actionForm = actionForm;
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		_portletAction.processAction(
			_actionMapping, _actionForm, portletConfig, actionRequest,
			actionResponse);
	}

	@Override
	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		ActionForward actionForward = _portletAction.render(
			_actionMapping, _actionForm, portletConfig, renderRequest,
			renderResponse);

		if (actionForward != null) {
			return actionForward.getPath();
		}

		return null;
	}

	@Override
	public void serveResource(
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		_portletAction.serveResource(
			_actionMapping, _actionForm, portletConfig, resourceRequest,
			resourceResponse);
	}

	private ActionForm _actionForm;
	private ActionMapping _actionMapping;
	private PortletAction _portletAction;

}