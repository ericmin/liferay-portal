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

package com.liferay.portlet.documentlibrary.service.http;

import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.BaseJsonClientTestCase;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class DLAppServiceJsonTest extends BaseJsonClientTestCase {

	@Before
	public void setUp() throws Exception {
		String name = "Test Folder";
		String description = "This is a test folder.";

		HttpPost httpPost = new HttpPost(_URL_DELETE_FOLDER);

		MultipartEntity multipartEntity = getMultipartEntity(
			new String[] {"repositoryId", "parentFolderId", "name"},
			new Object[] {
				TestPropsValues.getGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name
			});

		httpPost.setEntity(multipartEntity);

		executeRequest(httpPost);

		httpPost = new HttpPost(_URL_ADD_FOLDER);

		multipartEntity = getMultipartEntity(
			new String[] {
				"repositoryId", "parentFolderId", "name", "description"
			},
			new Object[] {
				TestPropsValues.getGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, name, description
			});

		httpPost.setEntity(multipartEntity);

		String responseContent = executeRequest(httpPost);

		checkException(responseContent);

		_folderId = GetterUtil.getLong(
			parseResponseContent(responseContent, "folderId", false));

		Assert.assertNotSame(0, _folderId);
	}

	@After
	public void tearDown() throws Exception {
		if (_folderId != 0) {
			HttpPost httpPost = new HttpPost(_URL_DELETE_FOLDER);

			MultipartEntity multipartEntity = getMultipartEntity(
				new String[] {"folderId"}, new Object[] {_folderId});

			httpPost.setEntity(multipartEntity);

			executeRequest(httpPost);
		}
	}

	@Test
	public void testAddFileEntry() throws Exception {
		String responseContent = addFileEntry("Test Add.txt");

		checkException(responseContent);
	}

	@Test
	public void testDeleteFileEntry() throws Exception {
		String responseContent = addFileEntry("Test Delete.txt");

		checkException(responseContent);

		long fileEntryId = GetterUtil.getLong(
			parseResponseContent(responseContent, "fileEntryId", false));

		Assert.assertNotSame(0, fileEntryId);

		HttpPost httpPost = new HttpPost(_URL_DELETE_FILE_ENTRY);

		MultipartEntity multipartEntity = getMultipartEntity(
			new String[] { "fileEntryId" }, new Object[] { fileEntryId });

		httpPost.setEntity(multipartEntity);

		responseContent = executeRequest(httpPost);

		checkException(responseContent);
	}

	@Test
	public void testGetFileEntry() throws Exception {
		String responseContent = addFileEntry("Test Get.txt");

		checkException(responseContent);

		String uuid = parseResponseContent(responseContent, "uuid", true);
		String groupId = String.valueOf(TestPropsValues.getGroupId());

		String url = StringUtil.replace(
			_URL_GET_FILE_ENTRY_BY_UUID_AND_GROUP_ID,
			new String[] { _UUID, _GROUP_ID }, new String[] { uuid, groupId });

		HttpGet httpGet = new HttpGet(url);

		responseContent = executeRequest(httpGet);

		checkException(responseContent);
	}

	protected String addFileEntry(String title) throws Exception {
		long repositoryId = TestPropsValues.getGroupId();
		long folderId = _folderId;
		String mimeType = ContentTypes.TEXT_PLAIN;
		String description = StringPool.BLANK;
		String changeLog = StringPool.BLANK;
		byte[] bytes = _CONTENT.getBytes();

		HttpPost httpPost = new HttpPost(_URL_ADD_FILE_ENTRY);

		MultipartEntity multipartEntity = getMultipartEntity(
			new String[] {
				"repositoryId", "folderId", "sourceFileName", "mimeType",
				"title", "description", "changeLog"
			},
			new Object[] {
				repositoryId, folderId, title, mimeType, title, description,
				changeLog
			});

		multipartEntity.addPart(
			"file", getByteArrayBody(bytes, mimeType, title));

		httpPost.setEntity(multipartEntity);

		return executeRequest(httpPost);
	}

	private static final String _CONTENT =
		"Content: Enterprise. Open Source. For Life.";

	private static final String _GROUP_ID = "[$GROUP_ID$]";

	private static final String _URL_ADD_FILE_ENTRY =
		URL_JSONWS + "/dlapp/add-file-entry";

	private static final String _URL_ADD_FOLDER =
		URL_JSONWS + "/dlapp/add-folder";

	private static final String _URL_DELETE_FILE_ENTRY =
		URL_JSONWS + "/dlapp/delete-file-entry";

	private static final String _URL_DELETE_FOLDER =
		URL_JSONWS + "/dlapp/delete-folder";

	private static final String _URL_GET_FILE_ENTRY_BY_UUID_AND_GROUP_ID =
		URL_JSONWS + "/dlapp/get-file-entry-by-uuid-and-group-id/uuid/" +
			DLAppServiceJsonTest._UUID + "/group-id/" + _GROUP_ID;

	private static final String _UUID = "[$UUID$]";

	private long _folderId;

}