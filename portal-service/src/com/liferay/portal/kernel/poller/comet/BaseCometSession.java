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

package com.liferay.portal.kernel.poller.comet;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseCometSession implements CometSession {

	public void close() throws CometException {
		try {
			doClose();

			_cometResponse.close();
		}
		catch (CometException ce) {
			throw ce;
		}
		catch (Exception e) {
			throw new CometException(e);
		}
	}

	public CometRequest getCometRequest() {
		return _cometRequest;
	}

	public CometResponse getCometResponse() {
		return _cometResponse;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setCometRequest(CometRequest cometRequest) {
		_cometRequest = cometRequest;
	}

	public void setCometResponse(CometResponse cometResponse) {
		_cometResponse = cometResponse;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	protected abstract void doClose() throws Exception;

	private CometRequest _cometRequest;
	private CometResponse _cometResponse;
	private String _sessionId;

}