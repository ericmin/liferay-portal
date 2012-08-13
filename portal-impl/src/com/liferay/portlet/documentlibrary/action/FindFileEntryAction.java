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

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juan Fernández
 * @author Ryan Park
 */
public class FindFileEntryAction extends FindFolderAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(primaryKey);

		return fileEntry.getRepositoryId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "fileEntryId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY)) {
			return "/document_library_display/view_file_entry";
		}

		return "/document_library/view_file_entry";
	}

}