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

package com.liferay.portlet.messageboards.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class MBThreadFlagSoap implements Serializable {
	public static MBThreadFlagSoap toSoapModel(MBThreadFlag model) {
		MBThreadFlagSoap soapModel = new MBThreadFlagSoap();

		soapModel.setThreadFlagId(model.getThreadFlagId());
		soapModel.setUserId(model.getUserId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setThreadId(model.getThreadId());

		return soapModel;
	}

	public static MBThreadFlagSoap[] toSoapModels(MBThreadFlag[] models) {
		MBThreadFlagSoap[] soapModels = new MBThreadFlagSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MBThreadFlagSoap[][] toSoapModels(MBThreadFlag[][] models) {
		MBThreadFlagSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MBThreadFlagSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MBThreadFlagSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MBThreadFlagSoap[] toSoapModels(List<MBThreadFlag> models) {
		List<MBThreadFlagSoap> soapModels = new ArrayList<MBThreadFlagSoap>(models.size());

		for (MBThreadFlag model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MBThreadFlagSoap[soapModels.size()]);
	}

	public MBThreadFlagSoap() {
	}

	public long getPrimaryKey() {
		return _threadFlagId;
	}

	public void setPrimaryKey(long pk) {
		setThreadFlagId(pk);
	}

	public long getThreadFlagId() {
		return _threadFlagId;
	}

	public void setThreadFlagId(long threadFlagId) {
		_threadFlagId = threadFlagId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getThreadId() {
		return _threadId;
	}

	public void setThreadId(long threadId) {
		_threadId = threadId;
	}

	private long _threadFlagId;
	private long _userId;
	private Date _modifiedDate;
	private long _threadId;
}