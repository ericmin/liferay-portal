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

package com.liferay.portal.kernel.security.pacl.permission;

import java.security.BasicPermission;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalMessageBusPermission extends BasicPermission {

	public PortalMessageBusPermission(String name, String destinationName) {
		super(name);

		_destinationName = destinationName;
	}

	public String getDestinationName() {
		return _destinationName;
	}

	private String _destinationName;

}