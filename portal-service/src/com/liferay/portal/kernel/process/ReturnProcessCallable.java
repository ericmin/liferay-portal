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

package com.liferay.portal.kernel.process;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class ReturnProcessCallable<T extends Serializable>
	implements ProcessCallable<T> {

	public ReturnProcessCallable(T returnValue) {
		_returnValue = returnValue;
	}

	public T call() {
		return _returnValue;
	}

	private final T _returnValue;

}