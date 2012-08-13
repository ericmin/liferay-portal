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

package com.liferay.portal.audit;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditMessageFactory;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class AuditMessageFactoryImpl implements AuditMessageFactory {

	public AuditMessage getAuditMessage(String message) throws JSONException {
		return new AuditMessage(message);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName) {

		return new AuditMessage(eventType, companyId, userId, userName);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK,
			message);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message, Date timestamp,
		JSONObject additionalInfo) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			timestamp, additionalInfo);
	}

	public AuditMessage getAuditMessage(
		String eventType, long companyId, long userId, String userName,
		String className, String classPK, String message,
		JSONObject additionalInfo) {

		return new AuditMessage(
			eventType, companyId, userId, userName, className, classPK, message,
			additionalInfo);
	}

}