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

package com.liferay.portal.model.impl;

/**
 * Represents a permission to perform an action on a resource in permissions
 * versions &lt; 6.
 *
 * @author Brian Wing Shun Chan
 */
public class PermissionImpl extends PermissionBaseImpl {

	public PermissionImpl() {
	}

	public String getName() {
		return _name;
	}

	public String getPrimKey() {
		return _primKey;
	}

	public int getScope() {
		return _scope;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPrimKey(String primKey) {
		_primKey = primKey;
	}

	public void setScope(int scope) {
		_scope = scope;
	}

	private String _name;
	private String _primKey;
	private int _scope;

}