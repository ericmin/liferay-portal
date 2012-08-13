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

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public class ORMException extends RuntimeException {

	public ORMException() {
		super();
	}

	public ORMException(String msg) {
		super(msg);
	}

	public ORMException(Throwable cause) {
		super(cause);
	}

	public ORMException(String msg, Throwable cause) {
		super(msg, cause);
	}

}