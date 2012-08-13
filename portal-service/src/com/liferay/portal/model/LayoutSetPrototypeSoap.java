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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.LayoutSetPrototypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portal.service.http.LayoutSetPrototypeServiceSoap
 * @generated
 */
public class LayoutSetPrototypeSoap implements Serializable {
	public static LayoutSetPrototypeSoap toSoapModel(LayoutSetPrototype model) {
		LayoutSetPrototypeSoap soapModel = new LayoutSetPrototypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLayoutSetPrototypeId(model.getLayoutSetPrototypeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setSettings(model.getSettings());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static LayoutSetPrototypeSoap[] toSoapModels(
		LayoutSetPrototype[] models) {
		LayoutSetPrototypeSoap[] soapModels = new LayoutSetPrototypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutSetPrototypeSoap[][] toSoapModels(
		LayoutSetPrototype[][] models) {
		LayoutSetPrototypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LayoutSetPrototypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutSetPrototypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutSetPrototypeSoap[] toSoapModels(
		List<LayoutSetPrototype> models) {
		List<LayoutSetPrototypeSoap> soapModels = new ArrayList<LayoutSetPrototypeSoap>(models.size());

		for (LayoutSetPrototype model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LayoutSetPrototypeSoap[soapModels.size()]);
	}

	public LayoutSetPrototypeSoap() {
	}

	public long getPrimaryKey() {
		return _layoutSetPrototypeId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutSetPrototypeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLayoutSetPrototypeId() {
		return _layoutSetPrototypeId;
	}

	public void setLayoutSetPrototypeId(long layoutSetPrototypeId) {
		_layoutSetPrototypeId = layoutSetPrototypeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public String getSettings() {
		return _settings;
	}

	public void setSettings(String settings) {
		_settings = settings;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private String _uuid;
	private long _layoutSetPrototypeId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _settings;
	private boolean _active;
}