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

package com.liferay.portal.kernel.notifications;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

/**
 * @author Edward Han
 */
public class NotificationEvent implements Serializable {

	public NotificationEvent(
		long timestamp, String type, JSONObject payloadJSONObject) {

		_timestamp = timestamp;
		_type = type;
		_payloadJSONObject = payloadJSONObject;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof NotificationEvent)) {
			return false;
		}

		NotificationEvent notificationEvent = (NotificationEvent)obj;

		if (Validator.equals(_uuid, notificationEvent._uuid)) {
			return true;
		}

		return false;
	}

	public long getDeliverBy() {
		return _deliverBy;
	}

	public JSONObject getPayload() {
		return _payloadJSONObject;
	}

	public long getTimestamp() {
		return _timestamp;
	}

	public String getType() {
		return _type;
	}

	public String getUuid() {
		if (_uuid == null) {
			_uuid = PortalUUIDUtil.generate();
		}

		return _uuid;
	}

	@Override
	public int hashCode() {
		if (_uuid != null) {
			return _uuid.hashCode();
		}
		else {
			return 0;
		}
	}

	public boolean isArchived() {
		return _archived;
	}

	public boolean isDeliveryRequired() {
		return _deliveryRequired;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	public void setDeliverBy(long deliverBy) throws IllegalArgumentException {
		if ((deliverBy < 0) && _deliveryRequired) {
			throw new IllegalArgumentException(
				"Deliver by must be greater than or equal to 0 if delivery " +
					"is required");
		}

		_deliverBy = deliverBy;
	}

	public void setDeliveryRequired(long deliverBy)
		throws IllegalArgumentException {

		if (deliverBy < 0) {
			throw new IllegalArgumentException(
				"Deliver by must be greater than or equal to 0 if delivery " +
					"is required");
		}

		_deliverBy = deliverBy;
		_deliveryRequired = true;
	}

	public void setTimestamp(long timestamp) {
		_timestamp = timestamp;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(_KEY_DELIVERY_REQUIRED, _deliveryRequired);
		jsonObject.put(_KEY_PAYLOAD, _payloadJSONObject);
		jsonObject.put(_KEY_TIMESTAMP, _timestamp);
		jsonObject.put(_KEY_TYPE, _type);
		jsonObject.put(_KEY_UUID, _uuid);

		return jsonObject;
	}

	private static final String _KEY_DELIVERY_REQUIRED = "deliveryRequired";

	private static final String _KEY_PAYLOAD = "payload";

	private static final String _KEY_TIMESTAMP = "timestamp";

	private static final String _KEY_TYPE = "type";

	private static final String _KEY_UUID = "uuid";

	private boolean _archived;
	private long _deliverBy;
	private boolean _deliveryRequired;
	private JSONObject _payloadJSONObject;
	private long _timestamp;
	private String _type;
	private String _uuid;

}