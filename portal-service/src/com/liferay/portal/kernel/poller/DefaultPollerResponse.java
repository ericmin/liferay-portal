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

package com.liferay.portal.kernel.poller;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultPollerResponse implements PollerResponse {

	public DefaultPollerResponse(
		PollerHeader pollerHeader, String portletId, String chunkId) {

		_pollerHeader = pollerHeader;
		_portletId = portletId;
		_chunkId = chunkId;
	}

	public synchronized void close() {
		if (Validator.isNotNull(_responseMessage)) {
			MessageBusUtil.sendMessage(
				_responseMessage.getDestinationName(), _responseMessage);

			_responseMessage = null;
		}
	}

	public void createResponseMessage(Message message) {
		String responseDestinationName = message.getResponseDestinationName();

		if (Validator.isNull(responseDestinationName)) {
			return;
		}

		_responseMessage = MessageBusUtil.createResponseMessage(message);

		_responseMessage.setPayload(this);
	}

	public PollerHeader getPollerHeader() {
		return _pollerHeader;
	}

	public String getPortletId() {
		return _portletId;
	}

	public boolean isEmpty() {
		return _parameterMap.isEmpty();
	}

	public synchronized void setParameter(String name, JSONArray value)
		throws PollerResponseClosedException {

		if (_responseMessage == null) {
			throw new PollerResponseClosedException();
		}

		_parameterMap.put(name, value);
	}

	public synchronized void setParameter(String name, JSONObject value)
		throws PollerResponseClosedException {

		if (_responseMessage == null) {
			throw new PollerResponseClosedException();
		}

		_parameterMap.put(name, value);
	}

	public void setParameter(String name, String value)
		throws PollerResponseClosedException {

		synchronized (this) {
			if (_responseMessage == null) {
				throw new PollerResponseClosedException();
			}

			_parameterMap.put(name, value);
		}
	}

	public JSONObject toJSONObject() {
		JSONObject pollerResponseJSONObject =
			JSONFactoryUtil.createJSONObject();

		pollerResponseJSONObject.put("portletId", _portletId);

		if (Validator.isNotNull(_chunkId)) {
			pollerResponseJSONObject.put("chunkId", _chunkId);
		}

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		Iterator<Map.Entry<String, Object>> itr =
			_parameterMap.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();

			String name = entry.getKey();
			Object value = entry.getValue();

			if (value instanceof JSONArray) {
				dataJSONObject.put(name, (JSONArray)value);
			}
			else if (value instanceof JSONObject) {
				dataJSONObject.put(name, (JSONObject)value);
			}
			else {
				dataJSONObject.put(name, String.valueOf(value));
			}
		}

		pollerResponseJSONObject.put("data", dataJSONObject);

		return pollerResponseJSONObject;
	}

	private String _chunkId;
	private Map<String, Object> _parameterMap = new HashMap<String, Object>();
	private PollerHeader _pollerHeader;
	private String _portletId;
	private Message _responseMessage;

}