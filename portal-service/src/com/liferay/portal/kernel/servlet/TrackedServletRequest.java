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

package com.liferay.portal.kernel.servlet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class TrackedServletRequest extends HttpServletRequestWrapper {

	public TrackedServletRequest(HttpServletRequest request) {
		super(request);
	}

	public Set<String> getSetAttributes() {
		if (_setAttributes == null) {
			return Collections.emptySet();
		}
		else {
			return _setAttributes;
		}
	}

	@Override
	public void setAttribute(String name, Object obj) {
		if (_setAttributes == null) {
			_setAttributes = new HashSet<String>();
		}

		_setAttributes.add(name);

		super.setAttribute(name, obj);
	}

	private Set<String> _setAttributes;

}