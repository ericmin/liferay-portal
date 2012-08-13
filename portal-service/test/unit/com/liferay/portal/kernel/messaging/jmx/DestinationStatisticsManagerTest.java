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

package com.liferay.portal.kernel.messaging.jmx;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.test.TestCase;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Michael C. Han
 */
@RunWith(PowerMockRunner.class)
public class DestinationStatisticsManagerTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		_mBeanServer = ManagementFactory.getPlatformMBeanServer();
	}

	@Test
	public void testRegisterMBean() throws Exception {
		_mBeanServer.registerMBean(
			new DestinationStatisticsManager(_destination),
			DestinationStatisticsManager.createObjectName("test"));

		assertTrue(
			_mBeanServer.isRegistered(
				DestinationStatisticsManager.createObjectName("test")));
	}

	@Mock
	private Destination _destination;

	private MBeanServer _mBeanServer;

}