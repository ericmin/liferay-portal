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

package com.liferay.portal.security.pacl;

import java.lang.reflect.Method;

import java.security.Permission;

import java.util.Properties;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public interface PACLPolicy {

	public void checkPermission(Permission permission);

	public ClassLoader getClassLoader();

	public Properties getProperties();

	public String getProperty(String key);

	public String[] getPropertyArray(String key);

	public boolean getPropertyBoolean(String key);

	public Set<String> getPropertySet(String key);

	public String getServletContextName();

	public boolean hasJNDI(String name);

	public boolean hasPortalService(
		Object object, Method method, Object[] arguments);

	public boolean hasSQL(String sql);

	public boolean isActive();

	public boolean isCheckablePermission(Permission permission);

}