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

package com.liferay.portlet.dynamicdatalists.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.dynamicdatalists.service.http.DDLRecordSetServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portlet.dynamicdatalists.service.http.DDLRecordSetServiceSoap
 * @generated
 */
public class DDLRecordSetSoap implements Serializable {
	public static DDLRecordSetSoap toSoapModel(DDLRecordSet model) {
		DDLRecordSetSoap soapModel = new DDLRecordSetSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRecordSetId(model.getRecordSetId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDDMStructureId(model.getDDMStructureId());
		soapModel.setRecordSetKey(model.getRecordSetKey());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setMinDisplayRows(model.getMinDisplayRows());
		soapModel.setScope(model.getScope());

		return soapModel;
	}

	public static DDLRecordSetSoap[] toSoapModels(DDLRecordSet[] models) {
		DDLRecordSetSoap[] soapModels = new DDLRecordSetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DDLRecordSetSoap[][] toSoapModels(DDLRecordSet[][] models) {
		DDLRecordSetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DDLRecordSetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DDLRecordSetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DDLRecordSetSoap[] toSoapModels(List<DDLRecordSet> models) {
		List<DDLRecordSetSoap> soapModels = new ArrayList<DDLRecordSetSoap>(models.size());

		for (DDLRecordSet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DDLRecordSetSoap[soapModels.size()]);
	}

	public DDLRecordSetSoap() {
	}

	public long getPrimaryKey() {
		return _recordSetId;
	}

	public void setPrimaryKey(long pk) {
		setRecordSetId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRecordSetId() {
		return _recordSetId;
	}

	public void setRecordSetId(long recordSetId) {
		_recordSetId = recordSetId;
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

	public long getDDMStructureId() {
		return _DDMStructureId;
	}

	public void setDDMStructureId(long DDMStructureId) {
		_DDMStructureId = DDMStructureId;
	}

	public String getRecordSetKey() {
		return _recordSetKey;
	}

	public void setRecordSetKey(String recordSetKey) {
		_recordSetKey = recordSetKey;
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

	public int getMinDisplayRows() {
		return _minDisplayRows;
	}

	public void setMinDisplayRows(int minDisplayRows) {
		_minDisplayRows = minDisplayRows;
	}

	public int getScope() {
		return _scope;
	}

	public void setScope(int scope) {
		_scope = scope;
	}

	private String _uuid;
	private long _recordSetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _DDMStructureId;
	private String _recordSetKey;
	private String _name;
	private String _description;
	private int _minDisplayRows;
	private int _scope;
}