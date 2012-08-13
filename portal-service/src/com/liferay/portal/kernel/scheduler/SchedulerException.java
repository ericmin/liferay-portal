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

package com.liferay.portal.kernel.scheduler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.spring.aop.Swallowable;

/**
 * @author Michael C. Han
 * @author Bruno Farache
 */
public class SchedulerException extends PortalException implements Swallowable {

	public SchedulerException() {
		super();
	}

	public SchedulerException(String msg) {
		super(msg);
	}

	public SchedulerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SchedulerException(Throwable cause) {
		super(cause);
	}

	public boolean isSwallowable() {
		return _swallowable;
	}

	public void setSwallowable(boolean swallowable) {
		_swallowable = swallowable;
	}

	private boolean _swallowable;

}