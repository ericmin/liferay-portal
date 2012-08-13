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

package com.liferay.portal.velocity;

import java.io.Serializable;

import org.apache.velocity.runtime.resource.util.StringResource;

/**
 * @author Michael C. Han
 */
public class SerializableStringResource implements Serializable {

	public SerializableStringResource(String body, String encoding) {
		_body = body;
		_encoding = encoding;
	}

	public String getBody() {
		return _body;
	}

	public String getEncoding() {
		return _encoding;
	}

	public StringResource toStringResource() {
		return new StringResource(_body, _encoding);
	}

	private String _body;
	private String _encoding;

}