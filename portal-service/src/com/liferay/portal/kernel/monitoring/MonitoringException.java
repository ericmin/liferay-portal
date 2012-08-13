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

package com.liferay.portal.kernel.monitoring;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class MonitoringException extends PortalException {

	public MonitoringException() {
		super();
	}

	public MonitoringException(String msg) {
		super(msg);
	}

	public MonitoringException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MonitoringException(Throwable cause) {
		super(cause);
	}

}