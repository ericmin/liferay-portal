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

package com.liferay.portlet.documentlibrary.workflow;

import com.liferay.portal.NoSuchWorkflowDefinitionLinkException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.WorkflowDefinitionLink;
import com.liferay.portal.security.permission.ResourceActionsUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

/**
 * @author Bruno Farache
 * @author Jorge Ferrer
 * @author Alexander Chow
 */
public class DLFileEntryWorkflowHandler extends BaseWorkflowHandler {

	public static final String CLASS_NAME = DLFileEntry.class.getName();

	public String getClassName() {
		return CLASS_NAME;
	}

	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, CLASS_NAME);
	}

	@Override
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException, SystemException {

		DLFileVersion dlFileVersion =
			DLFileVersionLocalServiceUtil.getFileVersion(classPK);

		long folderId = dlFileVersion.getFolderId();

		while (folderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			DLFolder dlFolder = DLFolderLocalServiceUtil.getFolder(folderId);

			if (dlFolder.isOverrideFileEntryTypes()) {
				break;
			}

			folderId = dlFolder.getParentFolderId();
		}

		try {
			return WorkflowDefinitionLinkLocalServiceUtil.
				getWorkflowDefinitionLink(
					companyId, groupId, DLFolder.class.getName(), folderId,
					dlFileVersion.getFileEntryTypeId(), true);
		}
		catch (NoSuchWorkflowDefinitionLinkException nswdle) {
			return WorkflowDefinitionLinkLocalServiceUtil.
				getWorkflowDefinitionLink(
					companyId, groupId, DLFolder.class.getName(), folderId,
					DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_ALL, true);
		}
	}

	@Override
	public boolean isVisible() {
		return _VISIBLE;
	}

	public DLFileEntry updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException, SystemException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			"serviceContext");

		return DLFileEntryLocalServiceUtil.updateStatus(
			userId, classPK, status, workflowContext, serviceContext);
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/common/clip.png";
	}

	private static final boolean _VISIBLE = false;

}