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

package com.liferay.util.bridges.jsf.sun;

import java.util.AbstractMap;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Neil Griffin
 */
public class LiferayApplicationMap extends AbstractMap<String, Object> {

	public LiferayApplicationMap(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(Object key) {
		return _servletContext.getAttribute(key.toString());
	}

	@Override
	public Object put(String key, Object value) {
		Object previousValue = get(key);

		_servletContext.setAttribute(key.toString(), value);

		return previousValue;
	}

	@Override
	public Object remove(Object key) {
		Object value = null;

		if (key != null) {
			value = get(key);

			_servletContext.removeAttribute(key.toString());
		}

		return value;
	}

	private ServletContext _servletContext;

}