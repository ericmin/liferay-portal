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
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.LayoutPrototypeServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portal.service.http.LayoutPrototypeServiceSoap
 * @generated
 */
public class LayoutPrototypeSoap implements Serializable {
	public static LayoutPrototypeSoap toSoapModel(LayoutPrototype model) {
		LayoutPrototypeSoap soapModel = new LayoutPrototypeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLayoutPrototypeId(model.getLayoutPrototypeId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setSettings(model.getSettings());
		soapModel.setActive(model.getActive());

		return soapModel;
	}

	public static LayoutPrototypeSoap[] toSoapModels(LayoutPrototype[] models) {
		LayoutPrototypeSoap[] soapModels = new LayoutPrototypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutPrototypeSoap[][] toSoapModels(
		LayoutPrototype[][] models) {
		LayoutPrototypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LayoutPrototypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutPrototypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutPrototypeSoap[] toSoapModels(
		List<LayoutPrototype> models) {
		List<LayoutPrototypeSoap> soapModels = new ArrayList<LayoutPrototypeSoap>(models.size());

		for (LayoutPrototype model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LayoutPrototypeSoap[soapModels.size()]);
	}

	public LayoutPrototypeSoap() {
	}

	public long getPrimaryKey() {
		return _layoutPrototypeId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutPrototypeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLayoutPrototypeId() {
		return _layoutPrototypeId;
	}

	public void setLayoutPrototypeId(long layoutPrototypeId) {
		_layoutPrototypeId = layoutPrototypeId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	private long _layoutPrototypeId;
	private long _companyId;
	private String _name;
	private String _description;
	private String _settings;
	private boolean _active;
}