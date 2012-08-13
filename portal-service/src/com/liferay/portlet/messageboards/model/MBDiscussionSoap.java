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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class MBDiscussionSoap implements Serializable {
	public static MBDiscussionSoap toSoapModel(MBDiscussion model) {
		MBDiscussionSoap soapModel = new MBDiscussionSoap();

		soapModel.setDiscussionId(model.getDiscussionId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setThreadId(model.getThreadId());

		return soapModel;
	}

	public static MBDiscussionSoap[] toSoapModels(MBDiscussion[] models) {
		MBDiscussionSoap[] soapModels = new MBDiscussionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MBDiscussionSoap[][] toSoapModels(MBDiscussion[][] models) {
		MBDiscussionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MBDiscussionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MBDiscussionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MBDiscussionSoap[] toSoapModels(List<MBDiscussion> models) {
		List<MBDiscussionSoap> soapModels = new ArrayList<MBDiscussionSoap>(models.size());

		for (MBDiscussion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MBDiscussionSoap[soapModels.size()]);
	}

	public MBDiscussionSoap() {
	}

	public long getPrimaryKey() {
		return _discussionId;
	}

	public void setPrimaryKey(long pk) {
		setDiscussionId(pk);
	}

	public long getDiscussionId() {
		return _discussionId;
	}

	public void setDiscussionId(long discussionId) {
		_discussionId = discussionId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public long getThreadId() {
		return _threadId;
	}

	public void setThreadId(long threadId) {
		_threadId = threadId;
	}

	private long _discussionId;
	private long _classNameId;
	private long _classPK;
	private long _threadId;
}