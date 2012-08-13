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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PollerRequest {

	public PollerRequest(
		HttpServletRequest request, PollerHeader pollerHeader, String portletId,
		Map<String, String> parameterMap, String chunkId,
		boolean receiveRequest) {

		_request = request;
		_pollerHeader = pollerHeader;
		_portletId = portletId;
		_parameterMap = parameterMap;
		_chunkId = chunkId;
		_receiveRequest = receiveRequest;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PollerRequest)) {
			return false;
		}

		PollerRequest portletRequest = (PollerRequest)obj;

		if (Validator.equals(_portletId, portletRequest._portletId)) {
			return true;
		}

		return false;
	}

	public long getBrowserKey() {
		return _pollerHeader.getBrowserKey();
	}

	public String getChunkId() {
		return _chunkId;
	}

	public long getCompanyId() {
		return _pollerHeader.getCompanyId();
	}

	public Map<String, String> getParameterMap() {
		return _parameterMap;
	}

	public PollerHeader getPollerHeader() {
		return _pollerHeader;
	}

	public String getPortletId() {
		return _portletId;
	}

	public String[] getPortletIds() {
		return _pollerHeader.getPortletIds();
	}

	public HttpServletRequest getRequest() {
		return _request;
	}

	public long getTimestamp() {
		return _pollerHeader.getTimestamp();
	}

	public long getUserId() {
		return _pollerHeader.getUserId();
	}

	@Override
	public int hashCode() {
		if (_portletId != null) {
			return _portletId.hashCode();
		}
		else {
			return 0;
		}
	}

	public boolean isInitialRequest() {
		return _pollerHeader.isInitialRequest();
	}

	public boolean isReceiveRequest() {
		return _receiveRequest;
	}

	public boolean isStartPolling() {
		return _pollerHeader.isStartPolling();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{chunkId=");
		sb.append(_chunkId);
		sb.append(", parameterMap=");
		sb.append(_parameterMap);
		sb.append(", pollerHeader=");
		sb.append(_pollerHeader);
		sb.append(", portletId=");
		sb.append(_portletId);
		sb.append(", receiveRequest=");
		sb.append(_receiveRequest);
		sb.append("}");

		return sb.toString();
	}

	private String _chunkId;
	private Map<String, String> _parameterMap = Collections.emptyMap();
	private PollerHeader _pollerHeader;
	private String _portletId;
	private boolean _receiveRequest;
	private HttpServletRequest _request;

}