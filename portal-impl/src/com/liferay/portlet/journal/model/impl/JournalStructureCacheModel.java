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

import com.liferay.portlet.journal.model.JournalStructure;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing JournalStructure in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JournalStructure
 * @generated
 */
public class JournalStructureCacheModel implements CacheModel<JournalStructure>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

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
		sb.append(", structureId=");
		sb.append(structureId);
		sb.append(", parentStructureId=");
		sb.append(parentStructureId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", xsd=");
		sb.append(xsd);
		sb.append("}");

		return sb.toString();
	}

	public JournalStructure toEntityModel() {
		JournalStructureImpl journalStructureImpl = new JournalStructureImpl();

		if (uuid == null) {
			journalStructureImpl.setUuid(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setUuid(uuid);
		}

		journalStructureImpl.setId(id);
		journalStructureImpl.setGroupId(groupId);
		journalStructureImpl.setCompanyId(companyId);
		journalStructureImpl.setUserId(userId);

		if (userName == null) {
			journalStructureImpl.setUserName(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			journalStructureImpl.setCreateDate(null);
		}
		else {
			journalStructureImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			journalStructureImpl.setModifiedDate(null);
		}
		else {
			journalStructureImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (structureId == null) {
			journalStructureImpl.setStructureId(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setStructureId(structureId);
		}

		if (parentStructureId == null) {
			journalStructureImpl.setParentStructureId(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setParentStructureId(parentStructureId);
		}

		if (name == null) {
			journalStructureImpl.setName(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setName(name);
		}

		if (description == null) {
			journalStructureImpl.setDescription(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setDescription(description);
		}

		if (xsd == null) {
			journalStructureImpl.setXsd(StringPool.BLANK);
		}
		else {
			journalStructureImpl.setXsd(xsd);
		}

		journalStructureImpl.resetOriginalValues();

		return journalStructureImpl;
	}

	public String uuid;
	public long id;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String structureId;
	public String parentStructureId;
	public String name;
	public String description;
	public String xsd;
}