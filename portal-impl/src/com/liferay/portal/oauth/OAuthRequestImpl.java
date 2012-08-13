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

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.oauth.OAuthRequest;
import com.liferay.portal.kernel.oauth.OAuthResponse;
import com.liferay.portal.kernel.oauth.Verb;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthRequestImpl implements OAuthRequest {

	public OAuthRequestImpl(org.scribe.model.OAuthRequest oAuthRequest) {
		_oAuthRequest = oAuthRequest;
	}

	public void addBodyParameter(String key, String value) {
		_oAuthRequest.addBodyParameter(key, value);
	}

	public String getURL() {
		return _oAuthRequest.getUrl();
	}

	public Verb getVerb() {
		return VerbTranslator.translate(_oAuthRequest.getVerb());
	}

	public Object getWrappedOAuthRequest() {
		return _oAuthRequest;
	}

	public OAuthResponse send() throws OAuthException {
		try {
			return new OAuthResponseImpl(_oAuthRequest.send());
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	private org.scribe.model.OAuthRequest _oAuthRequest;

}