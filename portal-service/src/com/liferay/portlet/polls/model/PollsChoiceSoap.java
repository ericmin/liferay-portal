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

package com.liferay.portlet.polls.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.polls.service.http.PollsChoiceServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portlet.polls.service.http.PollsChoiceServiceSoap
 * @generated
 */
public class PollsChoiceSoap implements Serializable {
	public static PollsChoiceSoap toSoapModel(PollsChoice model) {
		PollsChoiceSoap soapModel = new PollsChoiceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setChoiceId(model.getChoiceId());
		soapModel.setQuestionId(model.getQuestionId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static PollsChoiceSoap[] toSoapModels(PollsChoice[] models) {
		PollsChoiceSoap[] soapModels = new PollsChoiceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PollsChoiceSoap[][] toSoapModels(PollsChoice[][] models) {
		PollsChoiceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PollsChoiceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PollsChoiceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PollsChoiceSoap[] toSoapModels(List<PollsChoice> models) {
		List<PollsChoiceSoap> soapModels = new ArrayList<PollsChoiceSoap>(models.size());

		for (PollsChoice model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PollsChoiceSoap[soapModels.size()]);
	}

	public PollsChoiceSoap() {
	}

	public long getPrimaryKey() {
		return _choiceId;
	}

	public void setPrimaryKey(long pk) {
		setChoiceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getChoiceId() {
		return _choiceId;
	}

	public void setChoiceId(long choiceId) {
		_choiceId = choiceId;
	}

	public long getQuestionId() {
		return _questionId;
	}

	public void setQuestionId(long questionId) {
		_questionId = questionId;
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
	private long _choiceId;
	private long _questionId;
	private String _name;
	private String _description;
}