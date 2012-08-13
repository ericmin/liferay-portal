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

package com.liferay.portlet.documentlibrary.service;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;

import org.junit.After;
import org.junit.Before;

/**
 * @author Alexander Chow
 */
public abstract class BaseDLAppTestCase {

	@Before
	public void setUp() throws Exception {
		parentFolder = addFolder(
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, "Test Folder", true);
	}

	@After
	public void tearDown() throws Exception {
		if (parentFolder != null) {
			DLAppServiceUtil.deleteFolder(parentFolder.getFolderId());
		}
	}

	protected FileEntry addFileEntry(boolean rootFolder, String fileName)
		throws Exception {

		return addFileEntry(rootFolder, fileName, fileName);
	}

	protected FileEntry addFileEntry(
			boolean rootFolder, String sourceFileName, String title)
		throws Exception {

		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (!rootFolder) {
			folderId = parentFolder.getFolderId();
		}

		return addFileEntry(folderId, sourceFileName, title);
	}

	protected FileEntry addFileEntry(long folderId, String fileName)
		throws Exception {

		return addFileEntry(folderId, fileName, fileName);
	}

	protected FileEntry addFileEntry(
			long folderId, String sourceFileName, String title)
		throws Exception {

		return addFileEntry(folderId, sourceFileName, title, null);
	}

	protected FileEntry addFileEntry(
			long folderId, String sourceFileName, String title, byte[] bytes)
		throws Exception {

		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;

		if ((bytes == null) && Validator.isNotNull(sourceFileName)) {
			bytes = CONTENT.getBytes();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DLAppServiceUtil.addFileEntry(
			TestPropsValues.getGroupId(), folderId, sourceFileName,
			ContentTypes.TEXT_PLAIN, title, description, changeLog, bytes,
			serviceContext);
	}

	protected DLFileRank addFileRank(long fileEntryId) throws Exception {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DLAppLocalServiceUtil.addFileRank(
			TestPropsValues.getGroupId(), TestPropsValues.getCompanyId(),
			TestPropsValues.getUserId(), fileEntryId, serviceContext);
	}

	protected DLFileShortcut addFileShortcut(FileEntry fileEntry)
		throws Exception {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DLAppServiceUtil.addFileShortcut(
			TestPropsValues.getGroupId(), fileEntry.getFolderId(),
			fileEntry.getFileEntryId(), serviceContext);
	}

	protected Folder addFolder(boolean rootFolder, String name)
		throws Exception {

		long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (!rootFolder) {
			parentFolderId = parentFolder.getFolderId();
		}

		return addFolder(parentFolderId, name);
	}

	protected Folder addFolder(long parentFolderId, String name)
		throws Exception {

		return addFolder(parentFolderId, name, false);
	}

	protected Folder addFolder(
			long parentFolderId, String name, boolean deleteExisting)
		throws Exception {

		String description = StringPool.BLANK;

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		if (deleteExisting) {
			try {
				DLAppServiceUtil.deleteFolder(
					TestPropsValues.getGroupId(), parentFolderId, name);
			}
			catch (NoSuchFolderException nsfe) {
			}
		}

		return DLAppServiceUtil.addFolder(
			TestPropsValues.getGroupId(), parentFolderId, name, description,
			serviceContext);
	}

	protected FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String title)
		throws Exception {

		return updateFileEntry(fileEntryId, sourceFileName, title, false);
	}

	protected FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String title,
			boolean majorVersion)
		throws Exception {

		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;

		byte[] bytes = null;

		if (Validator.isNotNull(sourceFileName)) {
			bytes = CONTENT.getBytes();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		return DLAppServiceUtil.updateFileEntry(
			fileEntryId, sourceFileName, ContentTypes.TEXT_PLAIN, title,
			description, changeLog, majorVersion, bytes, serviceContext);
	}

	protected static final String CONTENT =
		"Content: Enterprise. Open Source. For Life.";

	protected Folder parentFolder;

}