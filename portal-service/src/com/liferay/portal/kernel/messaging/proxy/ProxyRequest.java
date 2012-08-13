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

import com.liferay.portal.kernel.util.MethodHandler;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Micha Kiener
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class ProxyRequest implements Serializable {

	public ProxyRequest(Method method, Object[] arguments) throws Exception {
		_methodHandler = new MethodHandler(method, arguments);

		_hasReturnValue = false;

		if (method.getReturnType() != Void.TYPE) {
			_hasReturnValue = true;
		}

		MessagingProxy messagingProxy = method.getAnnotation(
			MessagingProxy.class);

		if (messagingProxy == null) {
			messagingProxy = method.getDeclaringClass().getAnnotation(
				MessagingProxy.class);
		}

		if ((messagingProxy != null) &&
			messagingProxy.mode().equals(ProxyMode.SYNC)) {

			_synchronous = true;
		}
	}

	public Object execute(Object object) throws Exception {
		try {
			return _methodHandler.invoke(object);
		}
		catch (InvocationTargetException ite) {
			Throwable t = ite.getCause();

			if (t instanceof Exception) {
				throw (Exception)t;
			}
			else {
				throw new Exception(t);
			}
		}
	}

	public Object[] getArguments() {
		return _methodHandler.getArguments();
	}

	public boolean hasReturnValue() {
		return _hasReturnValue;
	}

	public boolean isSynchronous() {
		return _synchronous;
	}

	private boolean _hasReturnValue;
	private MethodHandler _methodHandler;
	private boolean _synchronous;

}