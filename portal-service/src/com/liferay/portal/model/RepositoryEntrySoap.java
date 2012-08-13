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
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class RepositoryEntrySoap implements Serializable {
	public static RepositoryEntrySoap toSoapModel(RepositoryEntry model) {
		RepositoryEntrySoap soapModel = new RepositoryEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRepositoryEntryId(model.getRepositoryEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setRepositoryId(model.getRepositoryId());
		soapModel.setMappedId(model.getMappedId());

		return soapModel;
	}

	public static RepositoryEntrySoap[] toSoapModels(RepositoryEntry[] models) {
		RepositoryEntrySoap[] soapModels = new RepositoryEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RepositoryEntrySoap[][] toSoapModels(
		RepositoryEntry[][] models) {
		RepositoryEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RepositoryEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new RepositoryEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RepositoryEntrySoap[] toSoapModels(
		List<RepositoryEntry> models) {
		List<RepositoryEntrySoap> soapModels = new ArrayList<RepositoryEntrySoap>(models.size());

		for (RepositoryEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RepositoryEntrySoap[soapModels.size()]);
	}

	public RepositoryEntrySoap() {
	}

	public long getPrimaryKey() {
		return _repositoryEntryId;
	}

	public void setPrimaryKey(long pk) {
		setRepositoryEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRepositoryEntryId() {
		return _repositoryEntryId;
	}

	public void setRepositoryEntryId(long repositoryEntryId) {
		_repositoryEntryId = repositoryEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	public String getMappedId() {
		return _mappedId;
	}

	public void setMappedId(String mappedId) {
		_mappedId = mappedId;
	}

	private String _uuid;
	private long _repositoryEntryId;
	private long _groupId;
	private long _repositoryId;
	private String _mappedId;
}