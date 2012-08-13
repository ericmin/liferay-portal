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

package com.liferay.portlet.dynamicdatamapping.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing DDMStructure in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see DDMStructure
 * @generated
 */
public class DDMStructureCacheModel implements CacheModel<DDMStructure>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", structureId=");
		sb.append(structureId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", structureKey=");
		sb.append(structureKey);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", xsd=");
		sb.append(xsd);
		sb.append(", storageType=");
		sb.append(storageType);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public DDMStructure toEntityModel() {
		DDMStructureImpl ddmStructureImpl = new DDMStructureImpl();

		if (uuid == null) {
			ddmStructureImpl.setUuid(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setUuid(uuid);
		}

		ddmStructureImpl.setStructureId(structureId);
		ddmStructureImpl.setGroupId(groupId);
		ddmStructureImpl.setCompanyId(companyId);
		ddmStructureImpl.setUserId(userId);

		if (userName == null) {
			ddmStructureImpl.setUserName(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ddmStructureImpl.setCreateDate(null);
		}
		else {
			ddmStructureImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddmStructureImpl.setModifiedDate(null);
		}
		else {
			ddmStructureImpl.setModifiedDate(new Date(modifiedDate));
		}

		ddmStructureImpl.setClassNameId(classNameId);

		if (structureKey == null) {
			ddmStructureImpl.setStructureKey(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setStructureKey(structureKey);
		}

		if (name == null) {
			ddmStructureImpl.setName(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setName(name);
		}

		if (description == null) {
			ddmStructureImpl.setDescription(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setDescription(description);
		}

		if (xsd == null) {
			ddmStructureImpl.setXsd(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setXsd(xsd);
		}

		if (storageType == null) {
			ddmStructureImpl.setStorageType(StringPool.BLANK);
		}
		else {
			ddmStructureImpl.setStorageType(storageType);
		}

		ddmStructureImpl.setType(type);

		ddmStructureImpl.resetOriginalValues();

		ddmStructureImpl.setDocument(_document);

		return ddmStructureImpl;
	}

	public String uuid;
	public long structureId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public String structureKey;
	public String name;
	public String description;
	public String xsd;
	public String storageType;
	public int type;
	public com.liferay.portal.kernel.xml.Document _document;
}