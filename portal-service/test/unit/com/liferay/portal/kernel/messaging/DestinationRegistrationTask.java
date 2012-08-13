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

package com.liferay.portal.kernel.messaging;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class DestinationRegistrationTask implements Callable<Object> {

	public DestinationRegistrationTask(
		Destination destination, MessageListener[] listeners,
		int taskIterationCount, boolean register) {

		_destination = destination;
		_listeners = listeners;
		_taskIterationCount = taskIterationCount;
		_register = register;
	}

	public Object call() {
		Random random = new Random();

		for (int i = 0; i < _taskIterationCount; i++) {
			MessageListener listener = _listeners[random.nextInt(
				_listeners.length)];

			if (_register) {
				_destination.register(listener);
			}
			else {
				_destination.unregister(listener);
			}
		}

		return null;
	}

	private Destination _destination;
	private MessageListener[] _listeners;
	private boolean _register;
	private int _taskIterationCount;

}