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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileShortcutImpl extends DLFileShortcutBaseImpl {

	public DLFileShortcutImpl() {
	}

	public Folder getFolder() {
		Folder folder = null;

		if (getFolderId() > 0) {
			try {
				folder = DLAppLocalServiceUtil.getFolder(getFolderId());
			}
			catch (Exception e) {
				folder = new LiferayFolder(new DLFolderImpl());

				_log.error(e);
			}
		}
		else {
			folder = new LiferayFolder(new DLFolderImpl());
		}

		return folder;
	}

	public String getToTitle() {
		String toTitle = null;

		try {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				getToFileEntryId());

			toTitle = fileEntry.getTitle();
		}
		catch (Exception e) {
			_log.error(e);
		}

		return toTitle;
	}

	private static Log _log = LogFactoryUtil.getLog(DLFileShortcutImpl.class);

}