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

package com.liferay.portlet.bookmarks.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class BookmarksEntryImpl extends BookmarksEntryBaseImpl {

	public BookmarksEntryImpl() {
	}

	public BookmarksFolder getFolder() {
		BookmarksFolder folder = null;

		if (getFolderId() > 0) {
			try {
				folder = BookmarksFolderLocalServiceUtil.getFolder(
					getFolderId());
			}
			catch (Exception e) {
				folder = new BookmarksFolderImpl();

				_log.error(e);
			}
		}
		else {
			folder = new BookmarksFolderImpl();
		}

		return folder;
	}

	private static Log _log = LogFactoryUtil.getLog(BookmarksEntryImpl.class);

}