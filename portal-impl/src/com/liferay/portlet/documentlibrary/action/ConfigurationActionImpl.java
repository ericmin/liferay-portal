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

package com.liferay.portlet.documentlibrary.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Jorge Ferrer
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		if (portletResource.equals(PortletKeys.DOCUMENT_LIBRARY)) {
			validateDisplayStyleViews(actionRequest);
		}

		validateRootFolder(actionRequest);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateDisplayStyleViews(ActionRequest actionRequest)
		throws Exception {

		String displayViews = GetterUtil.getString(
			getParameter(actionRequest, "displayViews"));

		if (Validator.isNull(displayViews)) {
			SessionErrors.add(actionRequest, "displayViewsInvalid");
		}
	}

	protected void validateRootFolder(ActionRequest actionRequest)
		throws Exception {

		long rootFolderId = GetterUtil.getLong(
			getParameter(actionRequest, "rootFolderId"));

		if (rootFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			try {
				DLAppLocalServiceUtil.getFolder(rootFolderId);
			}
			catch (NoSuchFolderException nsfe) {
				SessionErrors.add(actionRequest, "rootFolderIdInvalid");
			}
		}
	}

}