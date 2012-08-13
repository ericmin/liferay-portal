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

package com.liferay.portlet.journal;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class ArticleTitleException extends PortalException {

	public ArticleTitleException() {
		super();
	}

	public ArticleTitleException(String msg) {
		super(msg);
	}

	public ArticleTitleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ArticleTitleException(Throwable cause) {
		super(cause);
	}

}