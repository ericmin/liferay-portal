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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalServicePermission;
import com.liferay.portal.security.pacl.checker.Checker;
import com.liferay.portal.security.pacl.checker.JNDIChecker;
import com.liferay.portal.security.pacl.checker.PortalServiceChecker;
import com.liferay.portal.security.pacl.checker.SQLChecker;

import java.lang.reflect.Method;

import java.security.Permission;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class ActivePACLPolicy extends BasePACLPolicy {

	public ActivePACLPolicy(
		String servletContextName, ClassLoader classLoader,
		Properties properties) {

		super(servletContextName, classLoader, properties);

		try {
			initJNDIChecker();
			initPortalServiceChecker();
			initSQLChecker();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void checkPermission(Permission permission) {
		Checker checker = getChecker(permission.getClass());

		checker.checkPermission(permission);
	}

	public boolean hasJNDI(String name) {
		return _jndiChecker.hasJNDI(name);
	}

	public boolean hasPortalService(
		Object object, Method method, Object[] arguments) {

		return _portalServiceChecker.hasService(object, method, arguments);
	}

	public boolean hasSQL(String sql) {
		return _sqlChecker.hasSQL(sql);
	}

	public boolean isActive() {
		return true;
	}

	protected void initJNDIChecker() {
		_jndiChecker = new JNDIChecker();

		initChecker(_jndiChecker);
	}

	protected void initPortalServiceChecker() {
		_portalServiceChecker = (PortalServiceChecker)getChecker(
			PortalServicePermission.class);

		if (_portalServiceChecker == null) {
			_portalServiceChecker = new PortalServiceChecker();

			initChecker(_portalServiceChecker);
		}
	}

	protected void initSQLChecker() {
		_sqlChecker = new SQLChecker();

		initChecker(_sqlChecker);
	}

	private static Log _log = LogFactoryUtil.getLog(ActivePACLPolicy.class);

	private JNDIChecker _jndiChecker;
	private PortalServiceChecker _portalServiceChecker;
	private SQLChecker _sqlChecker;

}