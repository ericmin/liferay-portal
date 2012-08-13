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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.concurrent.ConcurrentHashSet;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class DefaultAuthenticatedUserUUIDStoreImpl
	implements AuthenticatedUserUUIDStore {

	public boolean exists(String userUUID) {
		return _userUUIDStore.contains(userUUID);
	}

	public boolean register(String userUUID) {
		return _userUUIDStore.add(userUUID);
	}

	public boolean unregister(String userUUID) {
		return _userUUIDStore.remove(userUUID);
	}

	private Set<String> _userUUIDStore = new ConcurrentHashSet<String>();

}