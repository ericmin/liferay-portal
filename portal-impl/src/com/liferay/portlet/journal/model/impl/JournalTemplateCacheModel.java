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

package com.liferay.portlet.journal.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.journal.model.JournalTemplate;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing JournalTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalTemplate
 * @generated
 */
public class JournalTemplateCacheModel implements CacheModel<JournalTemplate>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", templateId=");
		sb.append(templateId);
		sb.append(", structureId=");
		sb.append(structureId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", xsl=");
		sb.append(xsl);
		sb.append(", langType=");
		sb.append(langType);
		sb.append(", cacheable=");
		sb.append(cacheable);
		sb.append(", smallImage=");
		sb.append(smallImage);
		sb.append(", smallImageId=");
		sb.append(smallImageId);
		sb.append(", smallImageURL=");
		sb.append(smallImageURL);
		sb.append("}");

		return sb.toString();
	}

	public JournalTemplate toEntityModel() {
		JournalTemplateImpl journalTemplateImpl = new JournalTemplateImpl();

		if (uuid == null) {
			journalTemplateImpl.setUuid(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setUuid(uuid);
		}

		journalTemplateImpl.setId(id);
		journalTemplateImpl.setGroupId(groupId);
		journalTemplateImpl.setCompanyId(companyId);
		journalTemplateImpl.setUserId(userId);

		if (userName == null) {
			journalTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			journalTemplateImpl.setCreateDate(null);
		}
		else {
			journalTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			journalTemplateImpl.setModifiedDate(null);
		}
		else {
			journalTemplateImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (templateId == null) {
			journalTemplateImpl.setTemplateId(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setTemplateId(templateId);
		}

		if (structureId == null) {
			journalTemplateImpl.setStructureId(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setStructureId(structureId);
		}

		if (name == null) {
			journalTemplateImpl.setName(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setName(name);
		}

		if (description == null) {
			journalTemplateImpl.setDescription(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setDescription(description);
		}

		if (xsl == null) {
			journalTemplateImpl.setXsl(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setXsl(xsl);
		}

		if (langType == null) {
			journalTemplateImpl.setLangType(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setLangType(langType);
		}

		journalTemplateImpl.setCacheable(cacheable);
		journalTemplateImpl.setSmallImage(smallImage);
		journalTemplateImpl.setSmallImageId(smallImageId);

		if (smallImageURL == null) {
			journalTemplateImpl.setSmallImageURL(StringPool.BLANK);
		}
		else {
			journalTemplateImpl.setSmallImageURL(smallImageURL);
		}

		journalTemplateImpl.resetOriginalValues();

		return journalTemplateImpl;
	}

	public String uuid;
	public long id;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String templateId;
	public String structureId;
	public String name;
	public String description;
	public String xsl;
	public String langType;
	public boolean cacheable;
	public boolean smallImage;
	public long smallImageId;
	public String smallImageURL;
}