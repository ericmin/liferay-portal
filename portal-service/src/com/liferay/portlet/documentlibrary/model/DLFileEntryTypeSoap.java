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

package com.liferay.portlet.documentlibrary.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.documentlibrary.service.http.DLFileEntryTypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portlet.documentlibrary.service.http.DLFileEntryTypeServiceSoap
 * @generated
 */
public class DLFileEntryTypeSoap implements Serializable {
	public static DLFileEntryTypeSoap toSoapModel(DLFileEntryType model) {
		DLFileEntryTypeSoap soapModel = new DLFileEntryTypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setFileEntryTypeId(model.getFileEntryTypeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static DLFileEntryTypeSoap[] toSoapModels(DLFileEntryType[] models) {
		DLFileEntryTypeSoap[] soapModels = new DLFileEntryTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DLFileEntryTypeSoap[][] toSoapModels(
		DLFileEntryType[][] models) {
		DLFileEntryTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DLFileEntryTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DLFileEntryTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DLFileEntryTypeSoap[] toSoapModels(
		List<DLFileEntryType> models) {
		List<DLFileEntryTypeSoap> soapModels = new ArrayList<DLFileEntryTypeSoap>(models.size());

		for (DLFileEntryType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DLFileEntryTypeSoap[soapModels.size()]);
	}

	public DLFileEntryTypeSoap() {
	}

	public long getPrimaryKey() {
		return _fileEntryTypeId;
	}

	public void setPrimaryKey(long pk) {
		setFileEntryTypeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getFileEntryTypeId() {
		return _fileEntryTypeId;
	}

	public void setFileEntryTypeId(long fileEntryTypeId) {
		_fileEntryTypeId = fileEntryTypeId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _fileEntryTypeId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
}