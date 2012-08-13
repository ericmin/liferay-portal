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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.messaging.Message;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public interface ClusterLink {

	public List<Address> getLocalTransportAddresses();

	public List<Address> getTransportAddresses(Priority priority);

	public boolean isEnabled();

	public void sendMulticastMessage(Message message, Priority priority);

	public void sendUnicastMessage(
		Address address, Message message, Priority priority);

}