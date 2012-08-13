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

package com.liferay.portlet.dynamicdatamapping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatamapping.ContentException;
import com.liferay.portlet.dynamicdatamapping.ContentNameException;
import com.liferay.portlet.dynamicdatamapping.ContentXmlException;
import com.liferay.portlet.dynamicdatamapping.model.DDMContent;
import com.liferay.portlet.dynamicdatamapping.service.base.DDMContentLocalServiceBaseImpl;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DDMContentLocalServiceImpl extends DDMContentLocalServiceBaseImpl {

	public DDMContent addContent(
			long userId, long groupId, String name, String description,
			String xml, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		try {
			xml = DDMXMLUtil.formatXML(xml);
		}
		catch (Exception e) {
			throw new ContentXmlException(e);
		}

		Date now = new Date();

		validate(name, xml);

		long contentId = counterLocalService.increment();

		DDMContent content = ddmContentPersistence.create(contentId);

		content.setUuid(serviceContext.getUuid());
		content.setGroupId(serviceContext.getScopeGroupId());
		content.setCompanyId(user.getCompanyId());
		content.setUserId(user.getUserId());
		content.setUserName(user.getFullName());
		content.setCreateDate(serviceContext.getCreateDate(now));
		content.setModifiedDate(serviceContext.getModifiedDate(now));
		content.setName(name);
		content.setDescription(description);
		content.setXml(xml);

		ddmContentPersistence.update(content, false);

		return content;
	}

	public void deleteContent(DDMContent content) throws SystemException {
		ddmContentPersistence.remove(content);
	}

	public void deleteContents(long groupId) throws SystemException {
		List<DDMContent> contents = ddmContentPersistence.findByGroupId(
			groupId);

		for (DDMContent content : contents) {
			deleteContent(content);
		}
	}

	public DDMContent getContent(long contentId)
		throws PortalException, SystemException {

		return ddmContentPersistence.findByPrimaryKey(contentId);
	}

	public List<DDMContent> getContents() throws SystemException {
		return ddmContentPersistence.findAll();
	}

	public List<DDMContent> getContents(long groupId) throws SystemException {
		return ddmContentPersistence.findByGroupId(groupId);
	}

	public List<DDMContent> getContents(long groupId, int start, int end)
		throws SystemException {

		return ddmContentPersistence.findByGroupId(groupId, start, end);
	}

	public int getContentsCount(long groupId) throws SystemException {
		return ddmContentPersistence.countByGroupId(groupId);
	}

	public DDMContent updateContent(
			long contentId, String name, String description, String xml,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			xml = DDMXMLUtil.formatXML(xml);
		}
		catch (Exception e) {
			throw new ContentXmlException();
		}

		validate(name, xml);

		DDMContent content = ddmContentPersistence.findByPrimaryKey(contentId);

		content.setModifiedDate(serviceContext.getModifiedDate(null));
		content.setName(name);
		content.setDescription(description);
		content.setXml(xml);

		ddmContentPersistence.update(content, false);

		return content;
	}

	protected void validate(String name, String xml) throws PortalException {
		if (Validator.isNull(name)) {
			throw new ContentNameException();
		}

		if (Validator.isNull(xml)) {
			throw new ContentException();
		}

		try {
			SAXReaderUtil.read(xml);
		}
		catch (DocumentException de) {
			throw new ContentException();
		}
	}

}