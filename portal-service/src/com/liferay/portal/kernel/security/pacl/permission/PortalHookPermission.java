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
 * @author Raymond Augé
 */
public class PortalHookPermission extends BasicPermission {

	public PortalHookPermission(
		String name, ClassLoader classLoader, Object subject) {

		super(name);

		_classLoader = classLoader;
		_subject = subject;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	public Object getSubject() {
		return _subject;
	}

	private ClassLoader _classLoader;
	private Object _subject;

}