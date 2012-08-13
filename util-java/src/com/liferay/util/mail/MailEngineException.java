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

package com.liferay.util.mail;

import org.apache.commons.lang.exception.NestableException;

/**
 * @author Brian Wing Shun Chan
 */
public class MailEngineException extends NestableException {

	public MailEngineException() {
		super();
	}

	public MailEngineException(String msg) {
		super(msg);
	}

	public MailEngineException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MailEngineException(Throwable cause) {
		super(cause);
	}

}