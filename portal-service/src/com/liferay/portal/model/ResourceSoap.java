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
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.ResourceServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portal.service.http.ResourceServiceSoap
 * @generated
 */
public class ResourceSoap implements Serializable {
	public static ResourceSoap toSoapModel(Resource model) {
		ResourceSoap soapModel = new ResourceSoap();

		soapModel.setResourceId(model.getResourceId());
		soapModel.setCodeId(model.getCodeId());
		soapModel.setPrimKey(model.getPrimKey());

		return soapModel;
	}

	public static ResourceSoap[] toSoapModels(Resource[] models) {
		ResourceSoap[] soapModels = new ResourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ResourceSoap[][] toSoapModels(Resource[][] models) {
		ResourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ResourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ResourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ResourceSoap[] toSoapModels(List<Resource> models) {
		List<ResourceSoap> soapModels = new ArrayList<ResourceSoap>(models.size());

		for (Resource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ResourceSoap[soapModels.size()]);
	}

	public ResourceSoap() {
	}

	public long getPrimaryKey() {
		return _resourceId;
	}

	public void setPrimaryKey(long pk) {
		setResourceId(pk);
	}

	public long getResourceId() {
		return _resourceId;
	}

	public void setResourceId(long resourceId) {
		_resourceId = resourceId;
	}

	public long getCodeId() {
		return _codeId;
	}

	public void setCodeId(long codeId) {
		_codeId = codeId;
	}

	public String getPrimKey() {
		return _primKey;
	}

	public void setPrimKey(String primKey) {
		_primKey = primKey;
	}

	private long _resourceId;
	private long _codeId;
	private String _primKey;
}