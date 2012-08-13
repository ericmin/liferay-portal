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

import com.liferay.portal.kernel.oauth.Token;

/**
 * @author Brian Wing Shun Chan
 */
public class TokenImpl implements Token {

	public TokenImpl(org.scribe.model.Token token) {
		_token = token;
	}

	public String getSecret() {
		return _token.getSecret();
	}

	public String getToken() {
		return _token.getToken();
	}

	public Object getWrappedToken() {
		return _token;
	}

	private org.scribe.model.Token _token;

}