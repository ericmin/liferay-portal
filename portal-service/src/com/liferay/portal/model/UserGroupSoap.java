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

package com.liferay.portal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.UserGroupServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portal.service.http.UserGroupServiceSoap
 * @generated
 */
public class UserGroupSoap implements Serializable {
	public static UserGroupSoap toSoapModel(UserGroup model) {
		UserGroupSoap soapModel = new UserGroupSoap();

		soapModel.setUserGroupId(model.getUserGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setParentUserGroupId(model.getParentUserGroupId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setAddedByLDAPImport(model.getAddedByLDAPImport());

		return soapModel;
	}

	public static UserGroupSoap[] toSoapModels(UserGroup[] models) {
		UserGroupSoap[] soapModels = new UserGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserGroupSoap[][] toSoapModels(UserGroup[][] models) {
		UserGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserGroupSoap[] toSoapModels(List<UserGroup> models) {
		List<UserGroupSoap> soapModels = new ArrayList<UserGroupSoap>(models.size());

		for (UserGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserGroupSoap[soapModels.size()]);
	}

	public UserGroupSoap() {
	}

	public long getPrimaryKey() {
		return _userGroupId;
	}

	public void setPrimaryKey(long pk) {
		setUserGroupId(pk);
	}

	public long getUserGroupId() {
		return _userGroupId;
	}

	public void setUserGroupId(long userGroupId) {
		_userGroupId = userGroupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getParentUserGroupId() {
		return _parentUserGroupId;
	}

	public void setParentUserGroupId(long parentUserGroupId) {
		_parentUserGroupId = parentUserGroupId;
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

	public boolean getAddedByLDAPImport() {
		return _addedByLDAPImport;
	}

	public boolean isAddedByLDAPImport() {
		return _addedByLDAPImport;
	}

	public void setAddedByLDAPImport(boolean addedByLDAPImport) {
		_addedByLDAPImport = addedByLDAPImport;
	}

	private long _userGroupId;
	private long _companyId;
	private long _parentUserGroupId;
	private String _name;
	private String _description;
	private boolean _addedByLDAPImport;
}