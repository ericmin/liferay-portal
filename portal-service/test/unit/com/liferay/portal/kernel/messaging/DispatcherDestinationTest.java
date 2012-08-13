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

import com.liferay.portal.kernel.test.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class DispatcherDestinationTest extends TestCase {

	public static final int LISTENER_COUNT = 10000;

	public static final int REGISTER_TASK_COUNT = 10;

	public static final int TASK_ITERATION_COUNT = 100;

	public static final int UNREGISTER_TASK_COUNT = 10;

	@Override
	public void setUp() throws Exception {
		_executorService = Executors.newFixedThreadPool(
			REGISTER_TASK_COUNT + UNREGISTER_TASK_COUNT);

		_destination = new DummyDispatcherDestination();

		_destination.setName(DispatcherDestinationTest.class.getName());

		_listeners = new MessageListener[LISTENER_COUNT];

		for (int i = 0; i < _listeners.length; i++) {
			_listeners[i] = new DummyMessageListener();
		}

		_tasks = new ArrayList<Callable<Object>>();

		DestinationRegistrationTask registerTask =
			new DestinationRegistrationTask(
					_destination, _listeners, TASK_ITERATION_COUNT, true);

		for (int i = 0; i < REGISTER_TASK_COUNT; i++) {
			_tasks.add(registerTask);
		}

		DestinationRegistrationTask unregisterTask =
			new DestinationRegistrationTask(
					_destination, _listeners, TASK_ITERATION_COUNT, false);

		for (int i = 0; i < UNREGISTER_TASK_COUNT; i++) {
			_tasks.add(unregisterTask);
		}

		_startTime = System.currentTimeMillis();
	}

	@Override
	public void tearDown() throws Exception {
		_executorService.shutdownNow();
		_executorService.awaitTermination(120, TimeUnit.SECONDS);

		long expectedTime = 100;
		long actualTime = System.currentTimeMillis() - _startTime;

		assertLessThan(expectedTime, actualTime);
	}

	public void testPerformance() throws Exception {
		_executorService.invokeAll(_tasks);
	}

	private BaseDestination _destination;
	private ExecutorService _executorService;
	private MessageListener[] _listeners;
	private long _startTime;
	private List<Callable<Object>> _tasks;

}