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
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.PermissionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portal.service.http.PermissionServiceSoap
 * @generated
 */
public class PermissionSoap implements Serializable {
	public static PermissionSoap toSoapModel(Permission model) {
		PermissionSoap soapModel = new PermissionSoap();

		soapModel.setPermissionId(model.getPermissionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setActionId(model.getActionId());
		soapModel.setResourceId(model.getResourceId());

		return soapModel;
	}

	public static PermissionSoap[] toSoapModels(Permission[] models) {
		PermissionSoap[] soapModels = new PermissionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PermissionSoap[][] toSoapModels(Permission[][] models) {
		PermissionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PermissionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PermissionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PermissionSoap[] toSoapModels(List<Permission> models) {
		List<PermissionSoap> soapModels = new ArrayList<PermissionSoap>(models.size());

		for (Permission model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PermissionSoap[soapModels.size()]);
	}

	public PermissionSoap() {
	}

	public long getPrimaryKey() {
		return _permissionId;
	}

	public void setPrimaryKey(long pk) {
		setPermissionId(pk);
	}

	public long getPermissionId() {
		return _permissionId;
	}

	public void setPermissionId(long permissionId) {
		_permissionId = permissionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getActionId() {
		return _actionId;
	}

	public void setActionId(String actionId) {
		_actionId = actionId;
	}

	public long getResourceId() {
		return _resourceId;
	}

	public void setResourceId(long resourceId) {
		_resourceId = resourceId;
	}

	private long _permissionId;
	private long _companyId;
	private String _actionId;
	private long _resourceId;
}