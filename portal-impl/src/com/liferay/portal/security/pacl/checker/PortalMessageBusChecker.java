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

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalMessageBusPermission;

import java.security.Permission;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalMessageBusChecker extends BaseChecker {

	public void afterPropertiesSet() {
		initListenDestinationNames();
		initSendDestinationNames();
	}

	public void checkPermission(Permission permission) {
		PortalMessageBusPermission portalMessageBusPermission =
			(PortalMessageBusPermission)permission;

		String name = portalMessageBusPermission.getName();
		String destinationName =
			portalMessageBusPermission.getDestinationName();

		if (name.equals(PORTAL_MESSAGE_BUS_PERMISSION_LISTEN)) {
			if (!_listenDestinationNames.contains(destinationName)) {
				throwSecurityException(
					_log,
					"Attempted to listen on destination " + destinationName);
			}
		}
		else if (name.equals(PORTAL_MESSAGE_BUS_PERMISSION_SEND)) {
			if (!_sendDestinationNames.contains(destinationName)) {
				throwSecurityException(
					_log, "Attempted to send to " + destinationName);
			}
		}
	}

	protected void initListenDestinationNames() {
		_listenDestinationNames = getPropertySet(
			"security-manager-message-bus-listen");

		if (_log.isDebugEnabled()) {
			Set<String> destinationNames = new TreeSet<String>(
				_listenDestinationNames);

			for (String destinationName : destinationNames) {
				_log.debug(
					"Allowing message listeners to listen on destination " +
						destinationName);
			}
		}
	}

	protected void initSendDestinationNames() {
		_sendDestinationNames = getPropertySet(
			"security-manager-message-bus-send");

		if (_log.isDebugEnabled()) {
			Set<String> destinationNames = new TreeSet<String>(
				_sendDestinationNames);

			for (String destinationName : destinationNames) {
				_log.debug(
					"Allowing the message bus to send to destination " +
						destinationName);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalMessageBusChecker.class);

	private Set<String> _listenDestinationNames;
	private Set<String> _sendDestinationNames;

}