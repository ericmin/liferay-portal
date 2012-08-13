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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class BookmarksFolderImpl extends BookmarksFolderBaseImpl {

	public BookmarksFolderImpl() {
	}

	public List<BookmarksFolder> getAncestors()
		throws PortalException, SystemException {

		List<BookmarksFolder> ancestors = new ArrayList<BookmarksFolder>();

		BookmarksFolder folder = this;

		while (true) {
			if (!folder.isRoot()) {
				folder = folder.getParentFolder();

				ancestors.add(folder);
			}
			else {
				break;
			}
		}

		return ancestors;
	}

	public BookmarksFolder getParentFolder()
		throws PortalException, SystemException {

		if (getParentFolderId() ==
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return null;
		}

		return BookmarksFolderLocalServiceUtil.getFolder(getParentFolderId());
	}

	public boolean isRoot() {
		if (getParentFolderId() ==
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			return true;
		}
		else {
			return false;
		}
	}

}