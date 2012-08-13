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

import java.security.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 */
public class JNDIChecker extends BaseChecker {

	public void afterPropertiesSet() {
		initNames();
	}

	public void checkPermission(Permission permission) {
		throw new UnsupportedOperationException();
	}

	public boolean hasJNDI(String name) {
		for (Pattern pattern : _patterns) {
			Matcher matcher = pattern.matcher(name);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	protected void initNames() {
		Set<String> names = getPropertySet("security-manager-jndi-names");

		_patterns = new ArrayList<Pattern>(names.size());

		for (String name : names) {
			Pattern pattern = Pattern.compile(name);

			_patterns.add(pattern);
		}
	}

	private List<Pattern> _patterns;

}