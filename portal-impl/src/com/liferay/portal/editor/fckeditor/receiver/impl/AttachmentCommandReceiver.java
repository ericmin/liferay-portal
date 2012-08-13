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

package com.liferay.portal.editor.fckeditor.receiver.impl;

import com.liferay.portal.editor.fckeditor.command.CommandArgument;
import com.liferay.portal.editor.fckeditor.exception.FCKException;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.WikiPageServiceUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author Julio Camarero
 */
public class AttachmentCommandReceiver extends BaseCommandReceiver {

	@Override
	protected String createFolder(CommandArgument commandArgument) {
		return "0";
	}

	@Override
	protected String fileUpload(
		CommandArgument commandArgument, String fileName,
		InputStream inputStream, String extension, long size) {

		try {
			HttpServletRequest request =
				commandArgument.getHttpServletRequest();

			long resourcePK = ParamUtil.getLong(
				request, "wikiPageResourcePrimKey");

			WikiPage page = WikiPageLocalServiceUtil.getPage(resourcePK);

			String title = page.getTitle();

			long nodeId = page.getNodeId();

			List<ObjectValuePair<String, InputStream>> inputStreamOVPs =
				new ArrayList<ObjectValuePair<String, InputStream>>(1);

			ObjectValuePair<String, InputStream> inputStreamOVP =
				new ObjectValuePair<String, InputStream>(fileName, inputStream);

			inputStreamOVPs.add(inputStreamOVP);

			WikiPageServiceUtil.addPageAttachments(
				nodeId, title, inputStreamOVPs);
		}
		catch (Exception e) {
			throw new FCKException(e);
		}

		return "0";
	}

	@Override
	protected void getFolders(
		CommandArgument commandArgument, Document document, Node rootNode) {
	}

	@Override
	protected void getFoldersAndFiles(
		CommandArgument commandArgument, Document document, Node rootNode) {

		try {
			_getFiles(commandArgument, document, rootNode);
		}
		catch (Exception e) {
			throw new FCKException(e);
		}
	}

	@Override
	protected boolean isStagedData(Group group) {
		return group.isStagedPortlet(PortletKeys.WIKI);
	}

	private void _getFiles(
			CommandArgument commandArgument, Document document, Node rootNode)
		throws Exception {

		Element filesElement = document.createElement("Files");

		rootNode.appendChild(filesElement);

		HttpServletRequest request = commandArgument.getHttpServletRequest();

		long wikiPageResourcePrimKey = ParamUtil.getLong(
			request, "wikiPageResourcePrimKey");

		WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(
			wikiPageResourcePrimKey);

		long repositoryId = CompanyConstants.SYSTEM;

		String dirName = wikiPage.getAttachmentsDir();

		String[] fileNames = null;

		try {
			fileNames = DLStoreUtil.getFileNames(
				wikiPage.getCompanyId(), repositoryId, dirName);
		}
		catch (NoSuchDirectoryException nsde) {
			DLStoreUtil.addDirectory(
				wikiPage.getCompanyId(), repositoryId, dirName);

			fileNames = DLStoreUtil.getFileNames(
				wikiPage.getCompanyId(), repositoryId, dirName);
		}

		String attachmentURLPrefix = ParamUtil.getString(
			request, "attachmentURLPrefix");

		for (String fileName : fileNames) {
			byte[] fileEntry = DLStoreUtil.getFileAsBytes(
				wikiPage.getCompanyId(), repositoryId, fileName);

			String[] parts = StringUtil.split(fileName, StringPool.SLASH);

			fileName = parts[3];

			Element fileElement = document.createElement("File");

			filesElement.appendChild(fileElement);

			fileElement.setAttribute("name", fileName);
			fileElement.setAttribute("desc", fileName);
			fileElement.setAttribute("size", getSize(fileEntry.length));
			fileElement.setAttribute("url", attachmentURLPrefix + fileName);
		}
	}

}