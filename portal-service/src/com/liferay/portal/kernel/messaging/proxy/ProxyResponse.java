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

package com.liferay.portal.kernel.messaging.proxy;

import java.io.Serializable;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class ProxyResponse implements Serializable {

	public Exception getException() {
		return _exception;
	}

	public Object getResult() {
		return _result;
	}

	public boolean hasError() {
		if (_exception != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setException(Exception exception) {
		_exception = exception;
	}

	public void setResult(Object result) {
		_result = result;
	}

	private Exception _exception;
	private Object _result;

}